package my.mvc.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

import com.horizon.spider.tasker.Tasker;

public class BuildTasker {
	
	public String buildOrder(Map<String, String[]> param) {
		try {
			Tasker order = new Tasker();
			BeanUtils.populate(order, param);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
