package my.mvc.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ForeverContext
 */
public class ForeverContext implements Filter {

	public static HttpServletRequest request;
	public static HttpServletResponse response;
	public static String serverAddress;
	public static String uploadDir;
    /**
     * Default constructor. 
     */
    public ForeverContext() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		request=(HttpServletRequest) req;
		response=(HttpServletResponse) res;
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		uploadDir=fConfig.getInitParameter("uploadDir");
		serverAddress=fConfig.getInitParameter("serverAddress");
	}

}
