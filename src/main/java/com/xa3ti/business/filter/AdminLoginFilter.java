package com.xa3ti.business.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Haner
 *
 */
public class AdminLoginFilter implements Filter{
	String redirect;
	String[] uncheckUrl;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		System.out.println("---------->"+request.getServletPath());
//		if(!isUncheck(request.getServletPath())){
//			Admin admin=(Admin) request.getSession().getAttribute("admin");
//			if(admin==null){
//				response.sendRedirect(request.getContextPath()+"/Admin/toLoginAdmin");
//				return;
//			}
//		}
//
		arg2.doFilter(arg0, arg1);

	}
	
	public void init(FilterConfig arg0) throws ServletException {
		String uncheck=arg0.getInitParameter("uncheck");
		uncheck=uncheck.trim();
		uncheck=uncheck.replaceAll("\n", "");
		uncheck=uncheck.replaceAll("\r", "");
		uncheck=uncheck.replaceAll("\t", "");
		uncheckUrl=uncheck.split(",");
		redirect=arg0.getInitParameter("redirect");
	}
	
	private boolean isUncheck(String servletPath){
		boolean isUncheck=false;
		for(String url:uncheckUrl){
			if(servletPath.contains(url)){
				isUncheck=true;
				break;
			}
		}
		return isUncheck;
	}
	
}
