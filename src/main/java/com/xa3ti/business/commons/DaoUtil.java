package com.xa3ti.business.commons;

import java.util.Map;

public class DaoUtil {

	/**
	 * 根据map数据，组装sql语句
	 * map中未对应的数据将被替换为空
	 */
	public static String formatSql(String[] prm, Map<String, Object> map, String sql) {
		if(prm.length > 0 && map != null && map.size() > 0) {
			for (int i = 0; i < prm.length; i+=2) {
				if(map.get(prm[i]) == null) {
					sql = sql.replace(" @" + i/2, "");
				}else {
					sql = sql.replace("@" + i/2, prm[i+1]);
				}
			}
		}
		
		return sql;
	}
}
