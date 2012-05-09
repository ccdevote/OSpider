package my.mvc.controler;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Log log=LogFactory.getLog(ActionException.class);
	private boolean isDebug=true;
	public ActionException(Exception e,String message){
		log.error(message+"\n"+e);
		if(isDebug) e.printStackTrace();
	}
}
