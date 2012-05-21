package com.horizon.spider.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import my.mvc.annotation.Type;
import my.mvc.annotation.Control;

import org.apache.commons.beanutils.BeanUtils;

import com.horizon.spider.api.SpiderAPI;
import com.horizon.spider.tasker.Tasker;

public class SpiderAction {
	/**
	 * @uml.property  name="sapi"
	 * @uml.associationEnd  
	 */
	SpiderAPI sapi;

	@Control(stype = @Type(2), success = "control.html", input = "user/register.html")
	public String buildOrder(Map<String, String[]> param) {
		try {
			Tasker task = new Tasker();
			BeanUtils.populate(task, param);
			sapi = SpiderAPI.getInstance(task);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "input";
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "input";
		}
		return "success";
	}

	public String startSpider() {
		sapi.startSpider();
		return "success";
	}
	public String pauseSpider(){
		sapi.pauseSpider();
		return "success";
	}
	public String shutDownSpider(){
		//System.out.println("shutdown spider ...");
		sapi.shutDownSpider(true);
		return "success";
	}
	public String restartSpider(){
		sapi.restartSpider();
		return "success";
	}
}
