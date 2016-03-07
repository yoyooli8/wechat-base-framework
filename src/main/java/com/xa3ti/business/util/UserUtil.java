package com.xa3ti.business.util;

 
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserUtil {
	String redirect;
	String[] uncheckUrl;

//	@Autowired
//	private SigmaUserService sigmaUserService;

	/**
	 * 获取openId
	 * 
	 * @param request
	 * @param response
	 * @param openId
	 * @return
	 */
	public static String getOpenId(HttpServletRequest request,
			HttpServletResponse response, String openId) {
		if (openId != null && openId.length() > 0) {
			Cookie cookie = new Cookie("openId_cookie", openId);
			// 设置Cookie有效期为一天
			cookie.setMaxAge(43200);
			response.addCookie(cookie);
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("openId_cookie".equals(cookies[i].getName())) {
						openId = cookies[i].getValue();
					}
				}
			}
		}
		return openId;
	}

 

	private boolean isUncheck(String servletPath) {
		boolean isUncheck = false;
		for (String url : uncheckUrl) {
			if (servletPath.contains(url)) {
				isUncheck = true;
				break;
			}
		}
		return isUncheck;
	}
}
