package com.horizon.spider.extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;



import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;





public class PCOnlineMirrorExtractor extends MirrorExtractor {

	@Override
	public void extract() {
		BufferedWriter bw = null;
		//创建属性过滤器
		NodeFilter attributes_filter =  new AndFilter(
				new TagNameFilter("td"),
				new OrFilter(new HasAttributeFilter("class", "td1"),
						new HasAttributeFilter("class", "td2")));
		//创建标题过滤器
		NodeFilter title_filter = (NodeFilter) new TagNameFilter("h1");
		//创建图片过滤器
		NodeFilter image_filter=(NodeFilter) new AndFilter(new TagNameFilter("img"),new HasAttributeFilter("class","bigimg"));
	
		//提取标题信息
		try {
			NodeList title_nodes = this.getParser().parse((org.htmlparser.NodeFilter) title_filter);
			long num = title_nodes.size();
			for(int i=0;i<num;i++){
				Node node_title=title_nodes.elementAt(i);
				String[] names=node_title.toPlainTextString().split("");
				StringBuffer titles = new StringBuffer();
				for(int k=0;k<names.length;k++){
					titles.append(names[k]).append("-");
				}
				titles.append((new Date()).getTime());
				String path=this.getOutputPath();
				bw = new BufferedWriter(new FileWriter(new File(path+titles.toString()+".txt")));
				int startPos=this.getInputFilePath().indexOf("mirror")+6;
				String url_seg=this.getInputFilePath().substring(startPos);
				url_seg=url_seg.replaceAll("\\\\", "/");
				String url="http:/"+url_seg;
				System.out.println(url);
				bw.write(url+NEWLINE);
				for(int k = 0;k<names.length;k++){
					bw.write(names[k]+NEWLINE);
				}
				
			}
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getParser().reset();
		try {
			NodeList attributes_nodes=this.getParser().parse(attributes_filter);
			for(int i=0;i<attributes_nodes.size();i++){
				TableColumn node = (TableColumn) attributes_nodes.elementAt(i);
				String name = node.getAttribute("class");
				String result = node.toPlainTextString();
				if(name.equals(new String("td1"))){
					bw.write(StringUtils.trim(result));
				}else if(name.equals(new String("td2"))){
					bw.write(StringUtils.trim(result));
					bw.newLine();
				}
				//提取属性信息
				continue;
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getParser().reset();
		try {
			NodeList image_nodes = this.getParser().parse(image_filter);
			for(int i=0;i<image_nodes.size();i++){
				ImageTag node=(ImageTag) image_nodes.elementAt(i);
				String image_url=node.getAttribute("src");
				String fileType=image_url.substring(image_url.lastIndexOf(".")+1);
				String new_image_file=(new Date()).getTime()+""+(new Random()).nextInt(100)+fileType;
				copyImage(image_url, new_image_file);
				bw.write(image_url+NEWLINE);
				bw.write(SEPARATOR+NEWLINE);
				bw.write(new_image_file+NEWLINE);
				System.out.println(image_url);
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
