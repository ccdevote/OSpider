package com.horizon.spider.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.horizon.spider.tasker.Tasker;
import com.horizon.spider.tasker.TaskerManager;

public class SpiderAction {
	public String buildOrder(Map<String, String[]> param) {
		try {
			Tasker task = new Tasker();
			BeanUtils.populate(task, param);
			TaskerManager.buildManager().buildTasker(task);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
