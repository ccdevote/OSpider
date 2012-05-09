package my.mvc.action;

import java.util.Map;

public class BuildOrder {
	public String buildOrder(Map<String, String[]> param) {
		String[] str=param.get("url");
		System.out.println(str[0]);
		return null;
	}
}
