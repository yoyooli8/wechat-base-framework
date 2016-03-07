package com.xa3ti.business.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xa3ti.business.entity.wxPOJO.responsMessage.NewsMessage;
import com.xa3ti.business.entity.wxPOJO.responsMessage.TextMessage;

public class MessageUtil {
	/**
	 * dom解析 一级属性解析,属性名重复只会添加最后一个属性 解析服务器返回的xml
	 * 
	 * @param is
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, String> parseXML(InputStream is)
			throws DocumentException {
		Map<String, String> data = new HashMap<String, String>();
		// 获取xml解析对象
		SAXReader reader = new SAXReader();
		// 加载文档到内存
		Document document = reader.read(is);
		// 获取根节点
		Element root = document.getRootElement();
		// 获取所有节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 遍历
		for (Element item : elementList) {
			data.put(item.getName(), item.getText());
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 文本消息转换
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		String result = xstream.toXML(textMessage);
		return result;
	}

	/**
	 * 图文消息转换
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String newsToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", newsMessage.getArticles().get(0).getClass());
		String result = xstream.toXML(newsMessage);
		return result;
	}

	/**
	 * 对所有xml节点的转换都增加CDATA标记
	 */
	private static XStream xstream = new XStream(new XppDriver() {

		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			// TODO Auto-generated method stub
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String cutLongStr(String title) {
		if (title != null && title.length() >30) {
			title = title.substring(0, 30) + "...";
		}
		return title;
	}
	// XML封装测试
	/*
	 * public static void main(String[] args) { TextMessage textMessage = new
	 * TextMessage(); textMessage.setContent("da");
	 * textMessage.setCreateTime(new Date().getTime());
	 * textMessage.setFromUserName("dawd"); textMessage.setFuncFlag(1);
	 * textMessage.setMsgType("dawd"); textMessage.setToUserName("awd");
	 * System.out.println(textToXml(textMessage)); }
	 */
	public Hashtable<String, String> iniMsgs=new Hashtable<String, String>();
 
	/**
	 * 系统事件自动提示语
	 * @param key
	 * @return
	 */
	public  static String getEventNotice(String key)
	{
		Hashtable<String, String> hash=new Hashtable<String, String> ();
		hash.put("会员卡升级","恭喜您升级成为【银卡会员】，即日起您将享有【银卡会员】的专项特权和服务，您可点击“会员卡说明”进行查看。");
		hash.put("序列号审核通过","恭喜您已成功激活产品序列号，现在起您可享受相对应级别的服务与权利。 ");
		hash.put("序列号审核失败","您的序列号验证失败，请重新验证产品序列号，如有疑问，请垂询适马中国售后热线400-630-2000"); 
		return hash.get(key);
	}
}
