package com.xa3ti.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorMessageController {
	@RequestMapping("permission")
	public ModelAndView goErrorPage(HttpServletRequest request){
		return new ModelAndView("error","error",(String)request.getAttribute("errorMsg"));
	}
}
