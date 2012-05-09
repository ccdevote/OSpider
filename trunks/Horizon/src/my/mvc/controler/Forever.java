package my.mvc.controler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import my.mvc.annotation.AnnotationEngine;


/**
 * 主控servlet，根据用户请求执行相应操作
 * @version 0.0.01
 * @author MzyAiLqq
 */
public class Forever extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Log log=LogFactory.getLog(Forever.class);
	private String[] packages;
	private String[] actions;
    private Map<String,Object> action=new HashMap<String,Object>();
    private Map<String,Method> method=new HashMap<String ,Method>();
    public Forever() {
        super();
    }

	/**
	 * servlet初始化
	 * 根据初始化传入的值解析出packages，actions
	 * #多个package，action包是用","隔开的
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		log.info("Starting init……");
		packages=config.getInitParameter("packages").split(",");
		actions=config.getInitParameter("actions").split(",");
		for(String str:actions){
				_LoadAction(str);
		}
		log.info("Ending init.");
	}
	/**
	 * 得到加载的类，如果找不到就根据全名重新加载
	 * @see Forever#_LoadAction(String)
	 * @author MzyAiLqq
	 * @param actionName
	 * @return
	 */
	public Object _LoadAction(String actionName){
		log.info("Load action-->"+actionName);
		Object act=action.get(actionName);
		if(act==null){
			for(String pck:packages){
				act=_LoadActionByFullName(pck+"."+actionName,actionName);
				if(act!=null){
					synchronized(action){
						action.put(actionName, act);
						break;
					}	
				}
			}
		}
		log.info("Load action-->"+actionName+" "+actionName==null?"fail":"success");
		return act;
	}
	/**
	 * 根据类的全部路径加载类
	 * @see #_LoadActionByFullName(String, String)
	 * @author MzyAiLqq
	 * @param fullName
	 * @param actionName
	 * @return
	 */
	public Object _LoadActionByFullName(String fullName,String actionName){
		Object act=null;
		try {
			act=Class.forName(fullName).newInstance();
		} catch (ClassNotFoundException e) {
			throw new ActionException(e,"ClassNotFoundException 没有找到需要加载的类");
		} catch (InstantiationException e) {
			throw new ActionException(e,"ClassNotFoundException 没有找到需要加载的类");
		} catch (IllegalAccessException e) {
			throw new ActionException(e,"ClassNotFoundException 没有找到需要加载的类");
		}
		return act;	
	}
	/**
	 * 根据action名和method名去加载方法
	 * @author MzyAiLqq
	 * @param act
	 * @param methodName
	 * @return
	 */
	public Method _GetActionMethod(Object act,String methodName){
		log.info("Get "+act.toString()+"'s method-->"+methodName);
		String key=act.getClass().getSimpleName()+"."+methodName;
		Method m=method.get(key);
		if(m!=null) return m;
		for(Method mm:act.getClass().getMethods()){
			if(mm.getModifiers()==Modifier.PUBLIC&&mm.getName().equals(methodName)){
				synchronized(method){
					method.put(key, mm);
				}
				return mm;
			}
		}
		return null;
	}
	/**
	 * 释放所有action,执行destory方法
	 */
	public void destory(){
		for(Object act:action.values()){
			try{
				Method m=act.getClass().getMethod("destory");
				if(m!=null){
					m.invoke(act);
					log.info("Destory action-->"+act.toString());
				}
			}catch(Exception e){
				throw new ActionException(e,"Action 销毁失败");
			}
		}
		super.destroy();
	}
	/**
	 * 根据传来的actionName methodName params执行用户的请求
	 * 
	 * @param actionName
	 * @param methodName
	 * @param params
	 * @return
	 */
	public Object execute(HttpServletRequest request,HttpServletResponse response,String[] actAndMod){
		Object result=null;
		//即将执行的action名
		String actionName=actAndMod[0];
		//即将执行的method名
		String methodName=actAndMod[1];
		//获得的action实体
		Object act=_LoadAction(actionName);
		//获得的方法
		Method med=_GetActionMethod(act, methodName);
		Object  a=null,b = null;
		if(act!=null&&med!=null){
			try {
				//获取参数
				Map<String,String[]> params=request.getParameterMap();
				Method before=_GetActionMethod(act,AnnotationEngine.getEngine(med).beforeMethod());
				if(before!=null){
					b =before.invoke(act,(Object[])AnnotationEngine.getEngine(med).params("before"));
					if(!b.equals("success"))return "input";
				}
				int length=med.getParameterTypes().length;
				switch(length){
				case 1:
					result=med.invoke(act, params);//new ProxyExcute(act,med,param).proxyObject().start();//med.invoke(act,realParam);//med.invoke(Proxy.newProxyInstance(act.getClass().getClassLoader(),act.getClass().getInterfaces(), new ProxyExcute(act,med)), realParam);//med.invoke(new ProxyExcute(act).proxyMethod(), realParam);
					break;
				case 2:
					result=med.invoke(act, params, request);
					break;
				case 3:
					result=med.invoke(act, params,request,response);
					break;
				}
				AnnotationEngine.getEngine(med).excuteControl((String) result);//基于注解的执行控制
				Method after=_GetActionMethod(act, AnnotationEngine.getEngine(med).afterMethod());
				if(after!=null){
					a=after.invoke(act,(Object[]) AnnotationEngine.getEngine(med).params("after"));
					if(!a.equals("success"))return "input";
				}
			} catch (Exception e) {
				throw new ActionException(e,"execute "+actionName+"."+methodName+"出错");
			} 
		}
		return result;
 }
	

	/**
	 * 根据用户请求的uri解析请求中的action,method以及参数params
	 * 调用 {@link #execute(String, String, Object...)}方法执行用户请求
	 * @param request
	 * @param response
	 * @return
	 */
	public String[] _DealURI(String uri){
		//http://www.ourhome.net/actions/action!method?
		int pos1,pos2;
		if(uri!=null&&!"".equals(uri)){
			pos1=uri.lastIndexOf("/");
			pos2=uri.indexOf("?");
			String[] actAndMod;
			try {
				String actionAndMethod=uri.substring(pos1+1, pos2>0?pos2:uri.length());
				actAndMod = actionAndMethod.split("!");
				//if(actAndMod[0]!=null&&actAndMod[1]!=null)
				return actAndMod;
			} catch (Exception e) {
				throw new ActionException(e,"访问的资源不存在");
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object result=execute(request, response,_DealURI(request.getRequestURI()));
		System.out.println(result);
	}

}
