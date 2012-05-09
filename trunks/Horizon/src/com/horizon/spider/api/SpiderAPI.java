package com.horizon.spider.api;

import com.horizon.spider.spider.Spider;
import com.horizon.spider.spider.Tasker;

public class SpiderAPI {
	private Spider spider = null;

	public void initSpider(Tasker task) {
		spider = new Spider(task);
	}

	public void startSpider() {
		if (spider != null) {
			spider.start();
		}
	}
	
	public void shutDownSpider(){
		
	}
}
