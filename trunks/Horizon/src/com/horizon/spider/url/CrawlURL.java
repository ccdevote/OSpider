package com.horizon.spider.url;



import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

public class CrawlURL implements Serializable {

	private static final long serialVersionUID = 4561213939476814841L;
	public CrawlURL() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @uml.property  name="criUrl"
	 * @uml.associationEnd  
	 */
	private String criUrl;//原始URL值，主机部分是域名
	/**
	 * @uml.property  name="url"
	 */
	private String url;   //URL值，主机部分是IP，为了防止重复主机的出现
	/**
	 * @uml.property  name="urlNO"
	 */
	private int urlNO;	  //URL 的编号
	/**
	 * @uml.property  name="statusCode"
	 */
	private int statusCode;//获取URL返回的结果码
	/**
	 * @uml.property  name="hitNum"
	 */
	private int hitNum;	  //此URL被其他文章引用的次数
	/**
	 * @uml.property  name="abstractText"
	 */
	private String abstractText;//文章摘要
	/**
	 * @uml.property  name="author"
	 */
	private String author;//作者
	/**
	 * @uml.property  name="wieght"
	 */
	private int wieght;	  //文章权重，包含的导向词的信息
	/**
	 * @uml.property  name="description"
	 */
	private String description;//文章描述
	/**
	 * @uml.property  name="fileSize"
	 */
	private int fileSize;//文章大小
	/**
	 * @uml.property  name="lastUpdateTime"
	 */
	private Timestamp lastUpdateTime;//最后修改时间
	/**
	 * @uml.property  name="timeToLive"
	 */
	private Date timeToLive;//过期时间
	/**
	 * @uml.property  name="title"
	 */
	private String title;//文章名称
	/**
	 * @uml.property  name="type"
	 */
	private String type;  //文章类型
	/**
	 * @uml.property  name="urlRefrences"
	 */
	private String[] urlRefrences;//引用的链接
	/**
	 * @uml.property  name="layer"
	 */
	private int layer;		//爬取的层次，从种子开始，依次为第0层，第1层……第n层
	/**
	 * @return
	 * @uml.property  name="criUrl"
	 */
	public String getCriUrl() {
		return criUrl;
	}
	/**
	 * @param criUrl
	 * @uml.property  name="criUrl"
	 */
	public void setCriUrl(String criUrl) {
		this.criUrl = criUrl;
	}
	/**
	 * @return
	 * @uml.property  name="url"
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 * @uml.property  name="url"
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return
	 * @uml.property  name="urlNO"
	 */
	public int getUrlNO() {
		return urlNO;
	}
	/**
	 * @param urlNO
	 * @uml.property  name="urlNO"
	 */
	public void setUrlNO(int urlNO) {
		this.urlNO = urlNO;
	}
	/**
	 * @return
	 * @uml.property  name="statusCode"
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode
	 * @uml.property  name="statusCode"
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return
	 * @uml.property  name="hitNum"
	 */
	public int getHitNum() {
		return hitNum;
	}
	/**
	 * @param hitNum
	 * @uml.property  name="hitNum"
	 */
	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}
	/**
	 * @return
	 * @uml.property  name="abstractText"
	 */
	public String getAbstractText() {
		return abstractText;
	}
	/**
	 * @param abstractText
	 * @uml.property  name="abstractText"
	 */
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	/**
	 * @return
	 * @uml.property  name="author"
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author
	 * @uml.property  name="author"
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return
	 * @uml.property  name="wieght"
	 */
	public int getWieght() {
		return wieght;
	}
	/**
	 * @param wieght
	 * @uml.property  name="wieght"
	 */
	public void setWieght(int wieght) {
		this.wieght = wieght;
	}
	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return
	 * @uml.property  name="fileSize"
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize
	 * @uml.property  name="fileSize"
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return
	 * @uml.property  name="lastUpdateTime"
	 */
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * @param lastUpdateTime
	 * @uml.property  name="lastUpdateTime"
	 */
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * @return
	 * @uml.property  name="timeToLive"
	 */
	public Date getTimeToLive() {
		return timeToLive;
	}
	/**
	 * @param timeToLive
	 * @uml.property  name="timeToLive"
	 */
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	/**
	 * @return
	 * @uml.property  name="title"
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title
	 * @uml.property  name="title"
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return
	 * @uml.property  name="urlRefrences"
	 */
	public String[] getUrlRefrences() {
		return urlRefrences;
	}
	/**
	 * @param urlRefrences
	 * @uml.property  name="urlRefrences"
	 */
	public void setUrlRefrences(String[] urlRefrences) {
		this.urlRefrences = urlRefrences;
	}
	/**
	 * @return
	 * @uml.property  name="layer"
	 */
	public int getLayer() {
		return layer;
	}
	/**
	 * @param layer
	 * @uml.property  name="layer"
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	

}
