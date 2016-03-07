package com.xa3ti.business.commons;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * https请求处理对象
 */
public class HttpsUtils {
	
	public static final String POST = "POST";
	public static final String GET = "GET";

	public static String https(String reqUrl, String outData, String method) throws Exception {
		StringBuffer sb = new StringBuffer();
		
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
		TrustManager[] tm = {new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		}};
		
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		
		//从上述SSLContext对象中得到SSLSocketFactory对象  
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		URL url = new URL(reqUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);
		
		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		
		// 设置请求方式（GET/POST）
		httpUrlConn.setRequestMethod(method);
		
		if (GET.equalsIgnoreCase(method)) {
			httpUrlConn.connect();
		}
		
		// 当有数据需要提交时
		if (null != outData) {  
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(outData.getBytes("UTF-8"));
			outputStream.close();
        }
		
		// 将返回的输入流转换成字符串  
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        
        // 释放资源
        inputStream.close();
        inputStream = null;
        httpUrlConn.disconnect();
        
        return sb.toString();
	}
}
