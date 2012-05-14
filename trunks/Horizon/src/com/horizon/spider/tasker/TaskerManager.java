package com.horizon.spider.tasker;

import java.util.HashMap;
import java.util.Map;

import com.horizon.spider.api.SpiderAPI;
import com.horizon.spider.spider.Spider;

/**
 * Spider的Tasker管理类
 * @author MZY
 * @date 2012-05-11 00:13:00
 * @version 1.0
 */
public class TaskerManager {
	/**
	 * @uml.property  name="taskers"
	 * @uml.associationEnd  qualifier="getTaskid:java.lang.Integer com.horizon.spider.tasker.Tasker"
	 */
	private Map<Integer,Tasker> taskers=new HashMap<Integer,Tasker>();
	private static int id=0;
	public TaskerManager(Tasker task) {
		buildTasker(task);
	}
	private void buildTasker(Tasker task){
		//id从0开始
		task.setTaskid(id++);
		taskers.put(task.getTaskid(), task);
	}
}
