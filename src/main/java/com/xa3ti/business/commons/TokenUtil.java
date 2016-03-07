package com.xa3ti.business.commons;

import com.google.gson.stream.JsonReader;
import com.xa3ti.business.entity.Constant;

import java.io.StringReader;
import java.util.Date;

public class TokenUtil {
	
	private static long then = 0;
	private static String token;
	
	public static String getToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constant.APPID+ "&secret=" + Constant.APPSECRET;
		long now = new Date().getTime();
		
		System.out.println("========"+token);
		//如果token的获取时间间隔小于7100秒，则直接使用，不重新获取
		if((now - then) < 7100000) {
			return token;
		}
		try {
			String json = HttpsUtils.https(url, null, HttpsUtils.GET);
			
			JsonReader reader = new JsonReader(new StringReader(json));
			
			reader.beginObject();
			while(reader.hasNext()) {
				String name = reader.nextName();
				
				if("access_token".equals(name)) {
					token = reader.nextString();
					then = now;
					break;
				}
			}
			//reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}



}
