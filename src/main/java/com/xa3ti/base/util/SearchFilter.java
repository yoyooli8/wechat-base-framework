package com.xa3ti.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class SearchFilter {

	public enum Operator {
		EQ, IN, ISNULL, LIKE, GT, LT, GTE, LTE, NE
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public static Map<String, SearchFilter> parse(Map<String, Object> filterParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : filterParams.entrySet()) {
			String[] names = StringUtils.split(entry.getKey(), "_");
			Object value = entry.getValue();
			if (names.length < 2) {
				throw new IllegalArgumentException(entry.getKey() + " is not a valid search filter name");
			}
			if(names.length == 3){
				if("SHORTDATE".equals(names[2])){
					String fieldValue = (String)value;
					if(XaUtil.isNotEmpty(fieldValue)){
						value = fieldValue.replace("T", "").replace(":", "").replace("-", "").subSequence(0, 8);
					}
				}
				if("DATE".equals(names[2])){
					String fieldValue = (String)value;
					if(XaUtil.isNotEmpty(fieldValue)){
						fieldValue = fieldValue.replace("T", "").replace(":", "").replace("-", "");
						if("LTE".equals(names[0])){
							value = fieldValue.substring(0, 8) + "235959";
						}else{
							value = fieldValue;
						}
					}
				}
			}
			SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), value);
			if(XaUtil.isNotEmpty(entry.getValue())){
				filters.put(filter.fieldName + Identities.uuid2(), filter);
			}
		}
		return filters;
	}
}
