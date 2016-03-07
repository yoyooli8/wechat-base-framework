package com.xa3ti.business.commons;

import com.xa3ti.business.entity.Menu;

import java.util.List;


public class MenuJson {

	/**
	 * 菜单Json数据组装
	 * @param ms
	 * @return
	 */
	public static String process(List<Menu> ms) {
		StringBuffer json = new StringBuffer();
		
		if(ms != null) {
			json.append("{\"button\":[");
			
			for (int i = 0; i < ms.size(); i++) {
				Menu m = ms.get(i);
				
				//菜单是按钮
				if(m.getSubMenu() == null) {
					op(json, m);
				}else {
					json.append("{\"name\":\"" + m.getMenuName() + "\",\"sub_button\":[");
					
					int c = m.getSubMenu().size();
					for (int j = 0; j < c; j++) {
						op(json, m.getSubMenu().get(j));
						
						//不是最后一条数据则加“,”
						if(c - j > 1) {
							json.append(",");
						}
					}
					
					json.append("]}");
				}
				
				//不是最后一条数据则加“,”
				if(ms.size() - i > 1) {
					json.append(",");
				}
			}
			
			json.append("]}");
		}
		
		return json.toString();
	}
	
	/**
	 * 
	 * @param json
	 */
	private static void op(StringBuffer json, Menu m) {
		json.append("{\"type\":\"" + m.getType() + "\",");
		json.append("\"name\":\"" + m.getMenuName() + "\",");
		
		if("view".equals(m.getType())) {
			json.append("\"url\":\"" + m.getUrl() + "\"");
		}else if("click".equals(m.getType())) {
			json.append("\"key\":\"" + m.getKeyVal() + "\"");
		}
		
		json.append("}");
	}
}