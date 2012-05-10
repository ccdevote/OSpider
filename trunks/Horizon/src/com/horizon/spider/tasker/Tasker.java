package com.horizon.spider.tasker;

/**
 * 任务类，根据设置的参数初始化爬虫的任务
 * @author MZY
 * @date 2012-05-09 23:03
 * @version 1.0
 * 任务id：
 * 任务名：String
 * 任务描述：String
 * 入口url：String
 * 最大线程数量：0<int<100
 * 爬行深度：0<int
 * 主题模板:String
 * 分值限制:double
 * 最大下载数量:long
 * 最大下载流量:long
 * 最大下载时间:int
 *
 */
public class Tasker {
	private Integer taskid;
	private String taskName;
	private String taskDescription;
	private String firstURL;
	private Integer maxThreads;
	private Long maxDownloads;
	private Long maxDownloadSize;
	private Long maxDownloadTime;
	public Long getMaxDownloadTime() {
		return maxDownloadTime;
	}
	public void setMaxDownloadTime(Long maxDownloadTime) {
		this.maxDownloadTime = maxDownloadTime;
	}
	private Integer depth;
	private String template;
	private Double boost;
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getFirstURL() {
		return firstURL;
	}
	public void setFirstURL(String firstURL) {
		this.firstURL = firstURL;
	}
	public Integer getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}
	public Long getMaxDownloads() {
		return maxDownloads;
	}
	public void setMaxDownloads(Long maxDownloads) {
		this.maxDownloads = maxDownloads;
	}
	public Long getMaxDownloadSize() {
		return maxDownloadSize;
	}
	public void setMaxDownloadSize(Long maxDownloadSize) {
		this.maxDownloadSize = maxDownloadSize;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Double getBoost() {
		return boost;
	}
	public void setBoost(Double boost) {
		this.boost = boost;
	}
	
}
