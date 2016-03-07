package com.xa3ti.base.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xa3ti.base.security.XaSecurityMetadataSourceService;
import com.xa3ti.base.security.XaUserDetails;

@Controller
@RequestMapping("/CmsManager")
public class XaCmsUserController {

	private void reset() {
		XaSecurityMetadataSourceService.reset();
	}

	@RequestMapping(value = "updateAuthentication")
	@ResponseBody
	public String updateAuthentication() {
		reset();
		String rep = "OK";
		return JSON.toJSONString(rep);
	}

	@RequestMapping(value = "getUserInfo")
	@ResponseBody
	public String getUserInfo() {
		Object userDetails = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userName="";
		if( userDetails instanceof String)
		{
			userName = (String)userDetails;
		}
		else if(userDetails instanceof XaUserDetails)
		{
			userName = ((XaUserDetails)userDetails).getUsername();
		}
		return JSON.toJSONString(userName);
	}

}
