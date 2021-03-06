package com.horizon.spider.fetcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FetcherException extends RuntimeException {

	private static final long serialVersionUID = -6480092591232868024L;
	/**
	 * @uml.property  name="log"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log log = LogFactory.getLog(getClass());
	public FetcherException(Exception e,String message) {
		log.error(e.getCause()+"   "+message);
	}
}
