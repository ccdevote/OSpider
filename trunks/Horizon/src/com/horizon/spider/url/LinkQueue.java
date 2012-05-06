package com.horizon.spider.url;



import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.horizon.spider.util.SimpleBloomFilter;

/**
 * URL队列，用来存放URL
 * @method 
 * @author root
 * @version 0.0.1
 */
public class LinkQueue {
	private static SimpleBloomFilter visitedURL = new SimpleBloomFilter();// 用来判断以访问的集合
	private static Queue<CrawlURL> unVisitedURL = new LinkedBlockingQueue<CrawlURL>();// 待访问集合
	private static Queue<CrawlURL> failedURL = new LinkedBlockingQueue<CrawlURL>();//抓取失败的URL集合
	/**
	 * 获取访问失败的队列
	 * @return failedURL:Qeueu<CrawURL>
	 */
	public static Queue<CrawlURL> getFailedURL() {
		return failedURL;
	}

	/**
	 * 获取带访问的URL队列
	 * 
	 * @return Queue
	 */
	public synchronized static Queue<CrawlURL> getUnVisitedURLQueue() {
		return unVisitedURL;
	}
	
	/**
	 * 获取未访问队列的大小，确定当前任务量
	 * @return Integer
	 */
	public static Integer getUnvisitedCount(){
		return unVisitedURL.size();
	}

	/**
	 * 将访问过的URL添加到visitedURL集合
	 * 
	 * @param url
	 */
	public synchronized static void addVisitedUrl(String url) {
		visitedURL.add(url);
	}

	/**
	 * 未访问的URL出列（从未访问队列中得到一个待访问的URL）
	 * 
	 * @return url:CrawURL
	 */
	public synchronized static CrawlURL getUnVisitedURL() {
		return unVisitedURL.poll();
	}

	/**
	 * URL去重后添加到待访问队列
	 * @param url:String
	 */
	public synchronized static void addUnVisitedURL(String url) {
		if (url != null && !url.trim().equals("") && !visitedURL.contains(url)
				&& !unVisitedURL.contains(url)) {
			CrawlURL cu = new CrawlURL();
			cu.setCriUrl(url);
			unVisitedURL.add(cu);
		}
	}
	
	/**
	 * URL去重后添加到待访问队列
	 * @param urls:Queue<String>
	 */
	public synchronized static void addUnVisitedURL(Queue<String> urls){
		CrawlURL cu;
		String url=null;
		if(urls.isEmpty())return;
		Iterator<String> iterator = urls.iterator();
		while(iterator.hasNext()){
			url=iterator.next();
			if (url != null && !url.trim().equals("") && !visitedURL.contains(url)
					&& !unVisitedURL.contains(url)) {
				cu = new CrawlURL();
				cu.setCriUrl(url);
				unVisitedURL.add(cu);
			}
		}
	}
	/**
	 *取得访问成功的URL 数量
	 * @return visitedURL.getCount():int
	 */
	public static int getVisitedURLNum(){
		return visitedURL.getCount();
	}
	/**
	 * 判断未抓取的链接队列是否为空
	 * @return
	 */
	public synchronized static boolean unVisitedURLISEmpty(){
		return unVisitedURL.isEmpty();
	}
	/**
	 * 当抓取任务出现异常时，
	 * 向抓取失败队列添加URL
	 * @param url
	 */
	public synchronized static void addFailedURL(String url){
		CrawlURL cri=new CrawlURL();
		cri.setUrl(url);
		failedURL.add(cri);
	}
	/**
	 * 获取抓取失败队列的大小
	 * @return
	 */
	public synchronized static Integer getFailedURLCount(){
		return failedURL.size();
	} 
}
