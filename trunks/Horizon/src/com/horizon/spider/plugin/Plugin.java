package com.horizon.spider.plugin;

import java.util.Set;

import com.horizon.spider.util.StringSearch;

/**
 * 插件类:abstract
 * 用来约束用户自定义插件的规则
 * @author MZY
 * @version 1.0
 * @date 2012-05-06 23:44
 */
public abstract class Plugin {
	
	public static StringSearch stringSearch=null;
	
	
	/**
	 * 超类构造方法
	 * 初始化StringSearch
	 * @param urlSet
	 */
	public Plugin(Set<String> urlSet) {
		stringSearch = new StringSearch(urlSet);
	}
	

	/**
	 * 判断传入的url是否符合规则
	 * 规则部分由用户自定义
	 * @param url
	 * @return
	 */
	public abstract boolean accept(String url);
	
	/**
	 * 根据评分规则进行打分
	 * @param pageRankRule
	 */
	public abstract double pageRank(_PageRankRule pageRankRule);
	
	/**
	 * 由用户自定义的PageRank规则
	 * 用来替换默认的规则
	 * 默认情况下，在Plugin初始化的时候会定义一个默认PageRank规则
	 * @return
	 */
	public abstract _PageRankRule pageRankRules();
	
	/**
	 * URL评分规则内部类
	 * @author Horizon
	 * @version 1.0
	 * @date 2012-05-06 23:47
	 */
	class _PageRankRule{
		
	}
	
	/**
	 * 将用户自定义的plugin注册
	 * 供extractor或其他组件调用
	 */
	public void register(){
		
	}
}
