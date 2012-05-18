package com.horizon.spider.fetcher;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.horizon.spider.connector.HttpClientManager;
import com.horizon.spider.tasker.Tasker;
import com.horizon.spider.url.LinkQueue;

/**
 * 网页抓取器,获取网页内容
 * 
 * @author Horizon
 * @version 1.0
 */
public class Fetcher implements Callable<String> {
	/**
	 * @uml.property name="index"
	 */
	private int index;
	/**
	 * @uml.property name="end"
	 */
	private CountDownLatch end;
	/**
	 * @uml.property name="begin"
	 */
	private CountDownLatch begin;
	/**
	 * @uml.property name="log"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private Tasker task = null;
	private Log log = LogFactory.getLog(Fetcher.class.getName());

	/**
	 * 初始化抓取线程，信息
	 * 
	 * @param index
	 * @param BEGIN
	 * @param END
	 */
	public Fetcher(int INDEX, CountDownLatch BEGIN, CountDownLatch END,
			Tasker task) {
		this.index = INDEX;
		this.begin = BEGIN;
		this.end = END;
		this.task = task;
	}

	@Override
	public String call() {
		try {
			begin.await();
			log.info("线程 " + index + " 开始工作");
		} catch (InterruptedException e) {
			throw new FetcherException(e, "InterruptedException");
		}
		HttpClientManager cm = null;
		String content = null;
		long count = 0;
		while (LinkQueue.getUnvisitedCount() > 0 || count++ < 1000000L) {
			try {
				switch (task.getStatus()) {
				case -1:
					log.info("Pause Spider ...");
					TimeUnit.SECONDS.sleep(5);
					log.info("notify Spider ..."+task.getStatus());
					
					continue;
					//Thread.sleep(Integer.MAX_VALUE);
				case -2:
					log.info("shutDown Spider ...");
					end.countDown();
					log.info("工作结束");
					return null;
				default:
				}
				System.out.println(count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(task.getStatus());
			/*
			 * try{ String url = LinkQueue.getUnVisitedURL().getCriUrl(); cm =
			 * new HttpClientManager(true); if (url != null&&cm!=null) {
			 * log.info("线程 "+index+" 抓取 "+url); content =cm.doGet(url);
			 * //System.out.println(content.length());
			 * LinkQueue.addUnVisitedURL(FetchURL.getInstance(url)
			 * .fetchUrls(content)); // 将抓取过的信息放入VisitedURL列表进行记录
			 * LinkQueue.addVisitedUrl(url);
			 * log.info("待抓取链接数量 ："+LinkQueue.getUnvisitedCount()); } else {
			 * LinkQueue.addFailedURL(url); log.error("线程 " + index
			 * +"抓取"+" "+url+" 失败，添加到失败队列 失败数量："+LinkQueue.getFailedURLCount());
			 * } }catch(Exception e){ e.printStackTrace();
			 * log.error(e.getMessage()); }finally{ //cm.release(); }
			 */
		}
		end.countDown();
		log.info("工作结束");
		return content;
	}

}