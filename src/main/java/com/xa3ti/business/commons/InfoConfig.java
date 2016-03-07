package com.xa3ti.business.commons;

import javax.annotation.PostConstruct;

/**
 * 初始化参数
 */
public class InfoConfig {
	
	public static String sys_url;
	public static String app_Id;
	public static String app_secret;
	
	private String sysUrl;
	private String appId;
	private String appSecret;
	
	/**
	 * 初始化参数数据，将参数值保存成静态资源
	 */
	@PostConstruct
	public void initParam() {
		InfoConfig.sys_url = getSysUrl();
		InfoConfig.app_Id = getAppId();
		InfoConfig.app_secret = getAppSecret();
	}

	public String getSysUrl() {
		return sysUrl;
	}
	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
