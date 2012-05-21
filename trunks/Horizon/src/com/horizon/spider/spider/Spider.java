package com.horizon.spider.spider;

import java.util.HashMap;
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
	/**
	 * @uml.property  name="dOWNLOAD_THREAD"
	 */
	private Integer DOWNLOAD_THREAD;
	/**
	 * @uml.property  name="es"
	 */
	private ExecutorService es;
	/**
	 * @uml.property  name="bEGIN"
	 */
	private CountDownLatch BEGIN;
	/**
	 * @uml.property  name="eND"
	 */
	private CountDownLatch END;
	/**
	 * @uml.property  name="future"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer java.util.concurrent.Future"
	 */
	private Map<Integer, Future<String>> future=new HashMap<Integer,Future<String>>();
	/**
	 * @uml.property  name="task"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	@SuppressWarnings("unused")
	private Tasker task;
	private static Log log = LogFactory.getLog(Spider.class);

	/**
	 * @return
	 * @uml.property  name="dOWNLOAD_THREAD"
	 */
	public Integer getDOWNLOAD_THREAD() {
		return DOWNLOAD_THREAD;
	}

	/**
	 * @param dOWNLOAD_THREAD
	 * @uml.property  name="dOWNLOAD_THREAD"
	 */
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
			future.put(i,es.submit(new Fetcher(i, BEGIN, END,task)));
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
		task.setStatus(-2);
		if (!es.isShutdown()) {
			es.shutdownNow();
		}
	}

	/**
	 * 任务暂停，等待restart
	 */
	public void pause() {
		task.setStatus(-1);
		/*for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			Future<String> f = future.get(i);
			f.cancel(true);
			if (!(f.isCancelled() && f.isDone())) {
				try {
					f.wait(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					log.error("暂停任务失败>>>" + e.getMessage());
				}
			}

		}*/
	}

	/**
	 * 暂停 指定的时长
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
		System.out.println("restart Spider");
		task.setStatus(1);
	//	BEGIN.notifyAll();
		/*for (int i = 1; i <= DOWNLOAD_THREAD; i++) {
			log.info("初始化线程：" + i);
			Future<String> f = future.get(i);
			if (!(f.isCancelled() && f.isDone())) {
				f.notify();
			}

		}*/
	}

}
