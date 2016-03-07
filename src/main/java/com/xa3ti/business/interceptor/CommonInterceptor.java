package com.xa3ti.business.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xa3ti.business.session.UserSession;
import com.xa3ti.business.util.UserUtil;

public class CommonInterceptor implements HandlerInterceptor {
//
//	@Autowired
//	private SigmaUserService sigmaUserService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("我是拦截器！我是拦截器！我是拦截器！我是拦截器！我是拦截器！我是拦截器！我是拦截器！");
		boolean isok = false;
		String OpenId = request.getParameter("openId");
		// if (!isUncheck(request.getServletPath())) { }
		System.out.println("---------->" + request.getServletPath());
		// 加入到排除列表中

		OpenId = UserUtil.getOpenId(request, response, OpenId);
		HttpSession session = request.getSession();
//		SigmaUser loginUser = UserSession.getUser(session);
//		if (loginUser == null) {
//			if (loginUser != null || OpenId != null && OpenId != "") {
//				loginUser = sigmaUserService.existUser(OpenId);
//				if (loginUser != null) {
//					UserSession.addUser(loginUser, request.getSession());
//					isok = true;
//
//				} else {
//					isok = false;
//					// /跳转到注册页面
//					request.setAttribute("openId", OpenId);
//					request.getRequestDispatcher(
//							"/WEB-INF/page/mobile/rightsAndCumulative.jsp")
//							.forward(request, response);
//				}
//			} else {
//				isok = false;
//				// 跳转到非法访问页面
//				response.sendRedirect(request.getContextPath() + "/");
//			}
//		}else
//		{
			isok = true;
//		}

		return isok;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.err
				.println("postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.err
				.println("afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception");
	}

	public boolean isCheck(String selvertPath) {
		String actionName = selvertPath.substring(
				selvertPath.lastIndexOf("/") + 1, selvertPath.length());
		System.err.println(actionName);
		List<String> anctions = new ArrayList<String>();
//		anctions.add("regist");
//		anctions.add("regist4");
//
//		anctions.add("regist1");
//		anctions.add("validate");
//		anctions.add("regist3");
//		anctions.add("showSigmaUserInfo");
//		anctions.add("toUpdateSigmaUser");
//		anctions.add("updateSigmaUser");
		return anctions.contains(actionName);
	}
}
