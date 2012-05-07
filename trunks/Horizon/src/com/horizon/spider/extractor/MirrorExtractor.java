package com.horizon.spider.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

/**
 * @author Horizon
 *
 */
public abstract class MirrorExtractor {
	
	protected static final String NEWLINE = "<<\r\n>>";
	//结果输出路径
	private String outputPath = "";
	//当前被处理的路径
	private String inputFilePath = "";
	//所有抓取到的网页的镜像路径
	private String mirrorDir = "";
	//存放处理后的产品图片的目录
	private String imageDir = "";
	private Parser parser;
	private String NOIMAGE = "";
	//对图片路径进行散列的算法，这里默认用MD5算法
	protected static final String HASH_ALGORITHM = "md5";
	//分隔符
	public static final String SEPARATOR = "========================================";

	/**
	 * 装载网页文件
	 * @param path
	 */
	public void loadFile(String path) {
		try {
			parser = new Parser(path);
			inputFilePath = path;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 使用正则表达式获取网页中的字符串
	 * @param pattern
	 * @param match
	 * @param index
	 * @return
	 */
	public String getProp(String pattern, String match, int index) {
		Pattern sp = Pattern.compile(pattern);
		Matcher matcher= sp.matcher(match);
		while(matcher.find()){
			return matcher.group(index);
		}
		return null;
	}
	
	/**
	 * 抽象方法，用来供子类实现，其功能是解释网页文件，将产品信息处理后保存
	 */
	public abstract void extract();

	/**
	 * 从mirror目录下拷贝文件到所制定的文件目录
	 * 在需要时可以被重载
	 * @param image_url
	 * @param new_image_url
	 * @return
	 */
	protected boolean copyImage(String image_url, String new_image_url) {
		String dirs = image_url.substring(7);
		FileInputStream in=null;
		FileOutputStream out=null;
		try {
			File file_in = new File(new File(mirrorDir), dirs);
			if (file_in == null || !file_in.exists()) {
				file_in = new File(NOIMAGE);
			}
			File file_out = new File(new File(imageDir), new_image_url);
			in = new FileInputStream(file_in);
			out = new FileOutputStream(file_out);
			IOUtils.copy(in, out);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}
	//计数器，显示当前处理网页个数
	static int count = 0;
	/**
	 * 使用传入的子类实现Extractor，递归遍历一个目录下的所有网页
	 * @param extractor
	 * @param path
	 */
	public static void traverse(MirrorExtractor extractor,File path){
		if(path== null){
			return ;
		}
		if(path.isDirectory()){
			String[] files = path.list();
			for(String str:files){
				traverse(extractor,new File(path,str));
			}
		}else{
			if(path.getAbsolutePath().endsWith(".html")&&path.getAbsolutePath().indexOf("_")==-1){
				System.out.println(path);
				count++;
				extractor.loadFile(path.getAbsolutePath());
				extractor.extract();
			}
		}
	}
	public Parser getParser() {
		return parser;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public String getMirrorDir() {
		return mirrorDir;
	}

	public void setMirrorDir(String mirrorDir) {
		this.mirrorDir = mirrorDir;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getNOIMAGE() {
		return NOIMAGE;
	}

	public void setNOIMAGE(String nOIMAGE) {
		NOIMAGE = nOIMAGE;
	}
}
