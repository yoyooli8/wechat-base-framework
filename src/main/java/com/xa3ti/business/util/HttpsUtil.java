package com.xa3ti.business.util;

import com.alibaba.fastjson.JSON;
import com.xa3ti.business.entity.Constant;
import com.xa3ti.business.entity.wxPOJO.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 网络请求工具
 * 
 * @author Haner
 * 
 */
public class HttpsUtil {
	private static HttpClient httpClient = null;
	private static HttpGet httpGet;
	private static HttpPost httpPost;
	public static String TIME_OUT = "请求超时";
	public static String SERVER_ERROR = "服务器异常";
	public static String NETWORK_ERROR = "网络异常";

	static {
		httpClient = getHttpClient();
	}

	/**
	 * Http Get请求 请求地址
	 * 
	 * @param url
	 *            Get参数
	 * @param params
	 *            编码
	 * @param encode
	 *            返回请求结果
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendGetRequest(String url, Map<String, String> params,
			String encode) {
		String result = null;
		try {
			if (null == params) {
				httpGet = new HttpGet(url);
			} else {
				httpGet = new HttpGet(url + dealGetParams(params, encode));
			}
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != null ? result : "";
	}

	public static String sendGetRequest(String url, Map<String, String> params)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, params, "UTF-8");
	}

	public static String sendGetRequest(String url)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, null, "UTF-8");
	}
    public static String sendGetRequestGB2312(String url)
            throws ClientProtocolException, IOException {
        return sendGetRequest(url, null, "GB2312");
    }

	/**
	 * POST请求 返回请求结果 HashMap键值参数
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static String sendPostRequest(String url, Object params,
			String encode) throws Exception {
		String resultStr = null;
		httpPost = new HttpPost(url);
		StringEntity entity = null;
		if (params instanceof Map) {
			entity = new StringEntity(dealPostParams(
					(HashMap<String, String>) params, encode));
		} else if (params instanceof String) {
			entity = new StringEntity((String) params, encode);
		} else if (params instanceof List) {
			entity = new UrlEncodedFormEntity(
					(List<? extends NameValuePair>) params, encode);
		} else {
			throw new Exception("参数有误!");
		}
		httpPost.setEntity(entity);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resultStr = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr != null ? resultStr : null;
	}

	/**
	 * 键值对请求 默认UTF-8编码
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, Map<String, String> params)
			throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}

	/**
	 * String 默认UTF-8编码
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, String params)
			throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}

	/**
	 * 键值对请求 默认UTF-8编码
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, List<NameValuePair> params)
			throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}

	/**
	 * 处理Get方式请求的URL
	 * 
	 * @param params
	 * @param enc
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String dealGetParams(Map<String, String> params, String enc)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		for (Entry<String, String> entry : params.entrySet()) {
			// 参数名=参数&参数名=参数
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue(), enc))
					.append("&");
		}
		// 删除最后一个&
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 处理POST请求URL
	 * 
	 * @param params
	 * @param enc
	 * @return
	 */
	private static String dealPostParams(Map<String, String> params, String enc) {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			try {
				sb.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), enc))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 获取HttpClient
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static synchronized HttpClient getHttpClient() {
		if (null == httpClient) {
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); // 设置http版本。
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);// 设置字符集
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
			ConnManagerParams.setTimeout(params, 1000); // 从连接池中取连接的超时时间
														// 超时报connectionPoolTimeoutException
			HttpConnectionParams.setConnectionTimeout(params, 2000);// 连接超时
																	// 超时报ConnectionTimeoutException
			HttpConnectionParams.setSoTimeout(params, 4000);// 请求超时//
															// 超时报SocketTimeoutException
			SchemeRegistry schReg = new SchemeRegistry(); // 设置我们的HttpClient支持HTTP和HTTPS两种模式
			schReg.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
					params, schReg);
			httpClient = new DefaultHttpClient(conMgr, params);

		}
		return httpClient;
	}

	public static AccessToken getAccessToken() throws ClientProtocolException,
			IOException {
		AccessToken accessToken = null;
		String url = Constant.TOKEN_URL.replace("APPID", Constant.APPID)
				.replace("APPSECRET", Constant.APPSECRET);
		accessToken = JSON.parseObject(HttpsUtil.sendGetRequest(url),
				AccessToken.class);
		return accessToken;
	}

	public static void main(String[] args) throws Exception {
		AccessToken accessToken = getAccessToken();
		System.out.println(accessToken.getAccess_token());
		ClickButton clickButton1 = new ClickButton();
		clickButton1.setKey("1");
		clickButton1.setName("杜晗");
		clickButton1.setType(ButtonType.CLICK);

		ClickButton clickButton2 = new ClickButton();
		clickButton2.setKey("1");
		clickButton2.setName("杜晗");
		clickButton2.setType(ButtonType.CLICK);

		ViewButton viewButton = new ViewButton();
		viewButton.setName("贼你妈");
		viewButton.setType(ButtonType.VIEW);
		viewButton.setUrl("http://www.baidu.com");

		BaseButton[] baseButtons = { clickButton1, clickButton2, viewButton };
		Menu menu = new Menu();
		menu.setButton(baseButtons);
		if (accessToken.getAccess_token() != "") {
			String str = HttpsUtil.sendPostRequest(
					Constant.MENU_CREAT_URL.replace("ACCESS_TOKEN",
							accessToken.getAccess_token()),
					JSON.toJSONString(menu));
			System.out.println(str);

		}
	}
}
