package com.horizon.spider.spider;



public class SpiderTest {
	public static void main(String args[]){
		String firstUrl="http://mobile.pconline.com.cn";
		String dir = "/home/Horizon/spider";
		new Spider(4, firstUrl, dir).start();
	}
}
