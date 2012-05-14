package com.horizon.spider.url;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.horizon.spider.util.StringSearch;

public class URLFilter {
	private static StringSearch ss;
	/**
	 * @uml.property  name="pconlineURL"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	private Set<String> pconlineURL=new HashSet<String>();

	public boolean isURL(String url){
		 Pattern abs_pattern=Pattern.compile("^[a-zA-z]+://[^\\s]*");
		 Pattern rela_pattern=Pattern.compile("![http://][0-9a-zA-Z]/*");
		 Matcher matcher=abs_pattern.matcher(url);
		 if(matcher.matches()){
			 return true;
		 }else{
			 matcher=rela_pattern.matcher(url);
			 if(matcher.matches()){
				 return true;
			 }
		 }
		 return false;
	}
	public boolean isPCOnlineURL(String url){
		pconlineURL.add("http://product.pconline.com.cn/mobile/");
		pconlineURL.add("http://product.pconline.com.cn/pdlib/");
		pconlineURL.add("http://pdlib.pconline.com.cn/product/");
		pconlineURL.add("http://img.pconline.com.cn/images/product/");
		ss=new StringSearch(pconlineURL);
		ss.findAll(url);
		long length=0;//=ss.findAll(url).length;
		//System.out.println(length);
		if(length>=1){
			return true;
		}
			
		return false;
	}
	public boolean isProperty(String url){
		String regx="http://product.pconline.com.cn/mobile/[\\w]+/[\\d]+\\.html";
		Pattern pattern=Pattern.compile(regx);
		Matcher matcher=pattern.matcher(url);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	public boolean accept(String url){
		if(isURL(url)&&isPCOnlineURL(url))
			return true;
		return false;
	}
	public Integer findProductId(String url){
		String sid = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		//System.out.println(sid);
		try{
			return Integer.parseInt(sid);
		}catch(Exception e){
			return -1;
		}
	}
	public static void main(String[] args){
		URLFilter uf = new URLFilter();
		String url="http://product.pconline.com.cn/mobile/nokia/506187.html";
		boolean flag=uf.isProperty(url);
		System.out.println(flag);
		uf.findProductId(url);
	}
}
