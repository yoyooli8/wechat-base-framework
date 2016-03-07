package com.xa3ti.business.commons;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import com.xa3ti.business.commons.bean.Message;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xa3ti.business.commons.bean.Page;

public class MessageReader {
	
	/**
	 * 获取实例对象
	 * @return
	 */
	public static MessageReader getInstance() {
		return new MessageReader();
	}
	
	
	/**
	 * 读取xml信息，并封装至Message对象
	 * @throws Exception
	 * 
	 */
	public Message read(HttpServletRequest request) throws Exception{
		SAXReader reader = new SAXReader();
		
		//根节点
		Element root = null;
		try {
			Document document = reader.read(request.getInputStream());
			root = document.getRootElement();
			
			//System.out.println(root.asXML());
		} catch (Exception e) {
		}
		
		/**
		 * 读取数据
		 */
		Class<Message> clazz = Message.class;
		
		//利用反射创建class对象
		Message target = clazz.newInstance();
		
		if(root != null) {
			//获得对象属性集合
			Field[] fs = clazz.getDeclaredFields();
			
			for (int i = 0; i < fs.length; i++) {
				String name = fs[i].getName();
				String value = root.elementText(name);
				
				//XML中有此数据则处理
				if(value != null && !"".equals(value)) {
					String set = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
					
					Method m = clazz.getMethod(set, new Class[] {fs[i].getType()});
					//方法调用
					m.invoke(target, getValue(value, fs[i].getGenericType()));
				}
			}
		}
		
		return target;
	}
	
	/**
	 * 数据类型转化
	 * @param value
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValue(String value, Type type) {
		if(String.class == type) {
			return (T) value;
		}else if(Integer.TYPE == type) {
			return (T) Integer.valueOf(value);
		}else if(Long.TYPE == type) {
			return (T) Long.valueOf(value);
		}else if(Boolean.TYPE == type) {
			return (T) Boolean.valueOf(value);
		}else if(Double.TYPE == type) {
			return (T) Double.valueOf(value);
		}else if(Float.TYPE == type) {
			return (T) Float.valueOf(value);
		}else if(Byte.TYPE == type) {
			return (T) Byte.valueOf(value);
		}else if(Short.TYPE == type) {
			return (T) Short.valueOf(value);
		}
		return (T) value;
	}
}
