package com.horizon.spider.proccesser;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.horizon.spider.connector.HttpClientManager;
import com.horizon.spider.fetcher.FetchURL;
import com.horizon.spider.fetcher.Fetcher;
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
	private static Log log = LogFactory.getLog(Spider.class);
	public Integer getDOWNLOAD_THREAD() {
		return DOWNLOAD_THREAD;
	}
	public void setDOWNLOAD_THREAD(Integer dOWNLOAD_THREAD) {
		DOWNLOAD_THREAD = dOWNLOAD_THREAD;
	}
	public Spider(Integer threadPoolSize, String firstURL, String dir) {
		setDOWNLOAD_THREAD(threadPoolSize);// 初始化线程池大小
		LinkQueue.addUnVisitedURL(firstURL);// 将种子添加到带抓取队列
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
		String fu=LinkQueue.getUnVisitedURL().getCriUrl();
		System.out.println("====开始抓取首页:"+fu+"======");
		String content=cm.doGet(fu);
		LinkQueue.addUnVisitedURL(FetchURL.getInstance(fu)
				.fetchUrls(content));
		// 将抓取过的信息放入VisitedURL列表进行记录
		LinkQueue.addVisitedUrl(fu);
		cm.release();
		System.out.println("首页结束，开始执行多线程抓取");
		BEGIN.countDown();
	}

}
