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
 * 任务状态：int
 *
 */
public class Tasker {
	/**
	 * @uml.property  name="taskid"
	 */
	private Integer taskid;
	/**
	 * @uml.property  name="taskName"
	 */
	private String taskName;
	/**
	 * @uml.property  name="taskDescription"
	 */
	private String taskDescription;
	/**
	 * @uml.property  name="firstURL"
	 */
	private String firstURL;
	/**
	 * @uml.property  name="maxThreads"
	 */
	private Integer maxThreads;
	/**
	 * @uml.property  name="maxDownloads"
	 */
	private Long maxDownloads;
	/**
	 * @uml.property  name="maxDownloadSize"
	 */
	private Long maxDownloadSize;
	/**
	 * @uml.property  name="maxDownloadTime"
	 */
	private Long maxDownloadTime;
	private Integer status=0;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return
	 * @uml.property  name="maxDownloadTime"
	 */
	public Long getMaxDownloadTime() {
		return maxDownloadTime;
	}
	/**
	 * @param maxDownloadTime
	 * @uml.property  name="maxDownloadTime"
	 */
	public void setMaxDownloadTime(Long maxDownloadTime) {
		this.maxDownloadTime = maxDownloadTime;
	}
	/**
	 * @uml.property  name="depth"
	 */
	private Integer depth;
	/**
	 * @uml.property  name="template"
	 */
	private String template;
	/**
	 * @uml.property  name="boost"
	 */
	private Double boost;
	/**
	 * @return
	 * @uml.property  name="taskid"
	 */
	public Integer getTaskid() {
		return taskid;
	}
	/**
	 * @param taskid
	 * @uml.property  name="taskid"
	 */
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	/**
	 * @return
	 * @uml.property  name="taskName"
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName
	 * @uml.property  name="taskName"
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return
	 * @uml.property  name="taskDescription"
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * @param taskDescription
	 * @uml.property  name="taskDescription"
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	/**
	 * @return
	 * @uml.property  name="firstURL"
	 */
	public String getFirstURL() {
		return firstURL;
	}
	/**
	 * @param firstURL
	 * @uml.property  name="firstURL"
	 */
	public void setFirstURL(String firstURL) {
		this.firstURL = firstURL;
	}
	/**
	 * @return
	 * @uml.property  name="maxThreads"
	 */
	public Integer getMaxThreads() {
		return maxThreads;
	}
	/**
	 * @param maxThreads
	 * @uml.property  name="maxThreads"
	 */
	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}
	/**
	 * @return
	 * @uml.property  name="maxDownloads"
	 */
	public Long getMaxDownloads() {
		return maxDownloads;
	}
	/**
	 * @param maxDownloads
	 * @uml.property  name="maxDownloads"
	 */
	public void setMaxDownloads(Long maxDownloads) {
		this.maxDownloads = maxDownloads;
	}
	/**
	 * @return
	 * @uml.property  name="maxDownloadSize"
	 */
	public Long getMaxDownloadSize() {
		return maxDownloadSize;
	}
	/**
	 * @param maxDownloadSize
	 * @uml.property  name="maxDownloadSize"
	 */
	public void setMaxDownloadSize(Long maxDownloadSize) {
		this.maxDownloadSize = maxDownloadSize;
	}
	/**
	 * @return
	 * @uml.property  name="depth"
	 */
	public Integer getDepth() {
		return depth;
	}
	/**
	 * @param depth
	 * @uml.property  name="depth"
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	/**
	 * @return
	 * @uml.property  name="template"
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template
	 * @uml.property  name="template"
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return
	 * @uml.property  name="boost"
	 */
	public Double getBoost() {
		return boost;
	}
	/**
	 * @param boost
	 * @uml.property  name="boost"
	 */
	public void setBoost(Double boost) {
		this.boost = boost;
	}
	
}
