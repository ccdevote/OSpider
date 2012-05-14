package com.horizon.spider.api;

import com.horizon.spider.spider.Spider;
import com.horizon.spider.tasker.Tasker;
import com.horizon.spider.tasker.TaskerManager;

/**
 * Spider模块的API接口类 initSpider(Tasker tasker):void 初始化爬虫 startSpider():void 启动爬虫
 * shutDownSpider(boolean force):void 停止爬虫，参数force为true则为立刻停止，
 * 反之则普通停止（等待正在进行的线程将结束后停止）；
 * 
 * @author MZY
 * @date 2012-05-11 00:02
 * @version 1.0
 */
public class SpiderAPI {
	// 爬虫模块
	/**
	 * @uml.property  name="spider"
	 * @uml.associationEnd  
	 */
	private Spider spider = null;
	// 任务管理者
	/**
	 * @uml.property  name="tm"
	 * @uml.associationEnd  
	 */
	private TaskerManager tm = null;

	/**
	 * 初始化爬虫
	 * 
	 * @param task
	 */
	private void initSpider(Tasker task) {
		spider = new Spider(task);
		tm = new TaskerManager(task);
	}

	/**
	 * SpiderA
	 * 
	 * @param task
	 * @return
	 */
	public static SpiderAPI getInstance(Tasker task) {
		SpiderAPI sapi = new SpiderAPI();
		sapi.initSpider(task);
		return sapi;
	}

	/**
	 * 启动爬虫
	 */
	public void startSpider() {
		if (spider != null) {
			spider.start();
		}
	}

	/**
	 * 停止爬虫 若force为true则立刻停止，若force为false则等待当前正在进行的线程完成后停止
	 * 
	 * @param force
	 */
	public void shutDownSpider(boolean force) {

	}
}
