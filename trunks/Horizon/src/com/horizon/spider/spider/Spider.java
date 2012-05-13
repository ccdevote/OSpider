package com.horizon.spider.spider;

import java.util.Map;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.horizon.spider.connector.HttpClientManager;
import com.horizon.spider.fetcher.Fetcher;
import com.horizon.spider.tasker.Tasker;
import com.horizon.spider.url.LinkQueue;

/**
 * 爬虫主类
 * 
 * @author MZY
 * 
 */
public class Spider {
	private Integer DOWNLOAD_THREAD;
	private ExecutorService es;
	private CountDownLatch BEGIN;
	private CountDownLatch END;
	private Map<Integer, Future<String>> future;
	@SuppressWarnings("unused")
	private Tasker task;
	private static Log log = LogFactory.getLog(Spider.class);

	public Integer getDOWNLOAD_THREAD() {
		return DOWNLOAD_THREAD;
	}

	public void setDOWNLOAD_THREAD(Integer dOWNLOAD_THREAD) {
		DOWNLOAD_THREAD = dOWNLOAD_THREAD;
	}

	public Spider(Tasker task) {
		this.task = task;
		log.info("init Spider" + task.getTaskid());
		setDOWNLOAD_THREAD(task.getMaxThreads());// 初始化线程池大小
		LinkQueue.addUnVisitedURL(task.getFirstURL());// 将种子添加到带抓取队列
		// 创建线程池
		es = Executors.newFixedThreadPool(DOWNLOAD_THREAD);
		// 设置开始位
		BEGIN = new CountDownLatch(1);
		// 设置结束位
		END = new CountDownLatch(DOWNLOAD_THREAD);
	}

	public void start() {
		for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			es.submit(new Fetcher(i, BEGIN, END));
		}
		HttpClientManager cm = new HttpClientManager(true);
		String fu = LinkQueue.getUnVisitedURL().getCriUrl();
		System.out.println("====开始抓取首页:" + fu + "======");
		// String content=cm.doGet(fu);
		/*
		 * LinkQueue.addUnVisitedURL(FetchURL.getInstance(fu)
		 * .fetchUrls(content));
		 */
		// 将抓取过的信息放入VisitedURL列表进行记录
		LinkQueue.addVisitedUrl(fu);
		cm.release();
		System.out.println("首页结束，开始执行多线程抓取");
		BEGIN.countDown();
	}

	/**
	 * 等待当前正在执行的任务运行完成后终止
	 */
	public void shutDown() {
		if (!es.isShutdown()) {
			es.shutdown();
		}
	}

	/**
	 * 任务立刻终止
	 */
	public void shutDownNow() {
		if (!es.isShutdown()) {
			es.shutdownNow();
		}
	}

	/**
	 * 任务暂停，等待restart
	 */
	public void pause() {
		for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			Future<String> f = future.get(i);
			if (!(f.isCancelled() && f.isDone())) {
				try {
					f.wait(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					log.error("暂停任务失败>>>" + e.getMessage());
				}
			}

		}
	}

	/**
	 * 暂停 制定的时长
	 * 
	 * @param time
	 */
	public void pause(long time) {
		for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			Future<String> f = future.get(i);
			if (!(f.isCancelled() && f.isDone())) {
				try {
					f.wait(time);
				} catch (InterruptedException e) {
					log.error("暂停任务失败>>>" + e.getMessage());
				}
			}

		}
	}

	/**
	 * 重启暂停的线程
	 */
	public void restart() {
		for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			Future<String> f = future.get(i);
			if (!(f.isCancelled() && f.isDone())) {
				f.notify();
			}

		}
	}

}
