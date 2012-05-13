package com.horizon.spider.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import my.mvc.annotation.Type;
import my.mvc.annotation.Control;

import org.apache.commons.beanutils.BeanUtils;

import com.horizon.spider.api.SpiderAPI;
import com.horizon.spider.tasker.Tasker;

public class SpiderAction {
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
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String startSpider() {
		System.out.println("SpiderAction start spider");
		sapi.startSpider();
		return "success";
	}
}
