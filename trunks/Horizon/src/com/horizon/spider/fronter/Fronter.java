package com.horizon.spider.fronter;

/**
 * 链接制造工厂
 * 描述：为Fetcher-抓取模块提供待抓取链接
 * 		操作LinkQueue-链接队列：
 * 			预处理：从LinkQueue取一定数目的链接
 * 			分级  ：对预处理取得的链接按优先级分类，
 * 				.将优先级高的放入优先队列
 * 				.其次的放入普通队列
 * 				.优先级最低的放入待定队列，以根据实际时间限制以及深度限制决定是否进行抓取
 * 	        
 * @author MZY
 * @date 2012-05-08 23:18:00
 *
 */
public abstract class Fronter {

}
