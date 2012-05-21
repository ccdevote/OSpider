package my.mvc.controler;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="log"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log log=LogFactory.getLog(ActionException.class);
	/**
	 * @uml.property  name="isDebug"
	 */
	private boolean isDebug=true;
	public ActionException(Exception e,String message){
		log.error(message+"\n"+e);
		if(isDebug) e.printStackTrace();
	}
}
