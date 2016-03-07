package com.xa3ti.business.session;

import javax.servlet.http.HttpSession;

public class UserSession {
	private static String SESSION_KEY = "com.xa3ti.sigma_mobile_"; 
	
//	public static void addUser(SigmaUser user,HttpSession session){
//		session.setAttribute(SESSION_KEY, user);
//	}
//
//	public static SigmaUser getUser(HttpSession session){
//		return (SigmaUser) session.getAttribute(SESSION_KEY);
//	}
	
	public static void removeUser(HttpSession session){
		session.removeAttribute(SESSION_KEY);
	}
}
