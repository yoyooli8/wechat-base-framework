package com.xa3ti.business.commons;

import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class UserInfoUtil {

	public static String getNameByOpenId(String openId) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + TokenUtil.getToken() + "&openid=" + openId;
		
		String nickname = "";
		try {
			String json = HttpsUtils.https(url, null, HttpsUtils.GET);
			
			JsonReader reader = new JsonReader(new StringReader(json));
			
			reader.beginObject();
			while(reader.hasNext()) {
				String name = reader.nextName();
				
				if("nickname".equals(name)) {
					nickname = reader.nextString();
					break;
				}else {
					reader.skipValue();//忽略值,跳过break
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nickname;
	}
	
	public static int getGroupByOpenId(String openId) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=" + TokenUtil.getToken();
		
		String data = "{\"openid\":\""+ openId + "\"}";
		int groupid = 0;
		try {
			String json = HttpsUtils.https(url, data, HttpsUtils.GET);
			
			JsonReader reader = new JsonReader(new StringReader(json));
			
			reader.beginObject();
			while(reader.hasNext()) {
				String name = reader.nextName();
				
				if("groupid".equals(name)) {
					groupid = reader.nextInt();
					break;
				}else {
					reader.skipValue();//忽略值,跳过break
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groupid;
	}
}
