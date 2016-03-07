package com.xa3ti.business.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Haner
 * 资源过滤
 */
public class ResourseFilter implements Filter {
	private String[] uncheckUrl;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		if(!isUncheck(request.getServletPath())){
			request.setAttribute("errorMsg", "亲,你没有权限访问这里呦!~");
			request.getRequestDispatcher("/error/permission").forward(arg0, arg1);
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		String uncheck=arg0.getInitParameter("uncheck");
		uncheck=uncheck.trim();
		uncheck=uncheck.replaceAll("\n", "");
		uncheck=uncheck.replaceAll("\r", "");
		uncheck=uncheck.replaceAll("\t", "");
		uncheckUrl=uncheck.split(",");
	}
	
	private boolean isUncheck(String servletPath){
		boolean isUncheck=false;
		for(String url:uncheckUrl){
			if(url.contains(servletPath)){
				isUncheck=true;
				break;
			}
		}
		return isUncheck;
	}

}
