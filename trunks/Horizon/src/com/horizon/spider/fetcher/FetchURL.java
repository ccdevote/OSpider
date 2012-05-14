package com.horizon.spider.fetcher;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.horizon.spider.url.URLFilter;

/**
 * 从爬行到的结果中抽取URL
 * 
 * @author MZY
 * @0.0.1
 * @date 2012-04-24
 */
public class FetchURL {
	/**
	 * @uml.property  name="urlMap"
	 * @uml.associationEnd  qualifier="linkHref:java.lang.String java.lang.String"
	 */
	private Map<String, String> urlMap = new HashMap<String, String>();
	/**
	 * @uml.property  name="urlQueue"
	 */
	private Queue<String> urlQueue=null;
	/**
	 * @uml.property  name="urlFilter"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private URLFilter urlFilter;
	/**
	 * @uml.property  name="baseURL"
	 */
	private String baseURL;

	
	private FetchURL(String baseURL) {
		new LinkedList<String>();
		urlQueue=new LinkedList<String>();
		urlFilter=new URLFilter();
		this.baseURL=baseURL;
	}

	/**
	 * 从爬取的网页信息中抽取URLS
	 * 
	 * @param content
	 *            :爬取的网页信息
	 * @return Queue<String>
	 */
	public Queue<String> fetchUrls(String content) {
		if (content.isEmpty() || content.equals(null) || (content == null))
			return null;
		baseURL = baseURL.substring(baseURL.lastIndexOf("/") + 1,
				baseURL.length()).contains(".") ? baseURL.substring(0,
				baseURL.lastIndexOf("/")) : baseURL;
		Document doc = Jsoup.parse(content, baseURL);
		Elements links = doc.getElementsByTag("a");
		if (!links.isEmpty()) {
			for (Element link : links) {
				String linkHref = link.absUrl("href");
				String linkText = link.text();
				if (urlFilter.accept(linkHref)) {
					urlMap.put(linkHref, linkText);
					// System.out.println(linkText + ":" + linkHref);
				}
			}
			urlQueue.addAll(urlMap.keySet());
			urlMap.clear();
			// System.out.println(urlQueue.size());
			return urlQueue;
		}
		return urlQueue;
	}

	/**
	 * 得到FetchURL的实例
	 * 
	 * @param baseURL
	 * @return
	 */
	public static FetchURL getInstance(String baseURL) {
		return new FetchURL(baseURL);
	}
}
