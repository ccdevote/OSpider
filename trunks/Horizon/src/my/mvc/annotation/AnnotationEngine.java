package my.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;

import my.mvc.controler.ActionException;
import my.mvc.filter.ForeverContext;

public class AnnotationEngine {
	private static Method method;
	private boolean hasAfter;
	private boolean hasBefore;
	private boolean hasControl;
	private Before before;
	private Control control;
	private After after;
	private String uri=null;
	public static AnnotationEngine getEngine(Method med){
			return new AnnotationEngine(med);
	}
	public String beforeMethod(){
		String medName=null;
		if(hasBefore){
			medName=before.method();
		}
		return medName;
	}
	public String afterMethod(){
		String medName=null;
		if(hasAfter){
			after=method.getAnnotation(After.class);
			medName=after.method();
		}
		return medName;
	}
	private void sendRedirect(String type){
		try {
			if(type.equals("input")){
				uri=ForeverContext.serverAddress+"/"+control.input();
			}else if(type.equals("success")){
				uri=ForeverContext.serverAddress+"/"+control.success();
			}
			ForeverContext.response.sendRedirect(uri);
		} catch (IOException e) {
			throw new ActionException(e,"不能跳转到 "+uri);
		}
	}
	private void forward(String type){
		try {
			if(type.equals("input")){
				uri=ForeverContext.serverAddress+"/"+control.input();
			}else if(type.equals("success")){
				uri=ForeverContext.serverAddress+"/"+control.success();
			}
			System.out.println(uri);
			ForeverContext.request.getRequestDispatcher(uri).forward(ForeverContext.request,ForeverContext.response);
		} catch (IOException e) {
			throw new ActionException(e,"不能转发到 "+uri);
		} catch (ServletException e) {
			throw new ActionException(e,"不能转发到 "+uri);
		}
	}
	public void excuteControl(String result){
		if(!hasControl)return;
		if(result.equals("input")){
			if(control.itype().value()==1){
				forward(result);
			}else if(control.itype().value()==2){
				sendRedirect(result);
			}
		}else if(result.equals("success")){
			if(control.stype().value()==1){
				forward(result);
			}else if(control.stype().value()==2){
				sendRedirect(result);
			}
		}
	}
	public String[] params(String type){
		if(type.equals("before")){
			before=method.getAnnotation(Before.class);
			return before.param();
		}else if(type.equals("after")){
			after=method.getAnnotation(After.class);
			return after.param();
		}else return null;
	}
	public AnnotationEngine(Method med) {
		method=med;
		if(method.isAnnotationPresent(Before.class)){
			hasBefore=true;
			before=method.getAnnotation(Before.class);
		}
		if(method.isAnnotationPresent(After.class)){
			hasAfter=true;
		}
		if(method.isAnnotationPresent(Control.class)){
			hasControl=true;
			control=method.getAnnotation(Control.class);
		}
		
	}
}
