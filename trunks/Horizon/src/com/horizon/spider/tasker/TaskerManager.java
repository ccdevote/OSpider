package com.horizon.spider.tasker;

import java.util.HashMap;
import java.util.Map;

/**
 * Spider的Tasker管理类
 * @author MZY
 * @date 2012-05-11 00:13:00
 * @version 1.0
 */
public class TaskerManager {
	private Map<Integer,Tasker> taskers=new HashMap<Integer,Tasker>();
	private static int id=0;
	public void buildTasker(Tasker task){
		//id从0开始
		task.setTaskid(id++);
		taskers.put(task.getTaskid(), task);
	}
	public static TaskerManager buildManager(){
		return new TaskerManager();
	}
}
