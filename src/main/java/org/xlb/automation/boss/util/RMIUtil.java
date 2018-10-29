package org.xlb.automation.boss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.util.Constants;

/**
 * 
 * RMIUtil远程调用工具类 
 * 
 * @author Jack Xie
 * @time 2018-0209
 */
public class RMIUtil {
	
	private static Logger log = LoggerFactory.getLogger(RMIUtil.class);
	/**
	 * 创建http连接
	 * 
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	private static HttpURLConnection getConnection(String urlStr)
			throws IOException {

		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Pragma:", "no-cache");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Content-Type", "text/xml");
		return con;
	}

	public static String invokeHttp(String xmlInfo) throws Exception{
		try {
			return invokeHttp(xmlInfo,ConfigBean.boss_uap_inter);
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e1) {
			throw e1;
		} catch (Exception e2) {
			throw e2;
		}
	}
	
	/**
	 * HTTP接口调用
	 * 
	 * @param xmlInfo 请求报文
	 * @param url 请求URL
	 * @return
	 * @throws MalformedURLException
	 */
	public static String invokeHttp(String xmlInfo, String url)
			throws MalformedURLException, IOException, Exception {
		HttpURLConnection con = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		String rspMsg = null;
		try {
			// url = "http://10.223.219.17:8086/fcgi-bin/Weixin";//
//			url = SystemParamUtil.getParameterById("9999301050909");
			// 获取连接实例
			con = getConnection(url);
			// 设置连接主机超时（单位：毫秒）
			con.setConnectTimeout(100000);
			// 设置从主机读取数据超时（单位：毫秒）
			con.setReadTimeout(100000);
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			out = new OutputStreamWriter(con.getOutputStream(),Constants.C_PUB_PUBLIC_ENCODING_GBK);
			// 接口调用
			//out.write(new String(xmlInfo.getBytes(PublicConstants.C_PUB_PUBLIC_ENCODING_GBK)));
			out.write(xmlInfo);
			out.flush();
			out.close();
			// 获取接口响应
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),Constants.C_PUB_PUBLIC_ENCODING_GBK));
			String line = "";
			StringBuffer rspXML = new StringBuffer();
			for (line = br.readLine(); line != null; line = br.readLine()) {
				rspXML.append(line);
			}

			rspMsg = rspXML.toString();

		} catch (MalformedURLException e) {
			log.error("Open connection failed!URL {}",url);
			throw e;
		} catch (IOException e1) {
			log.error("IOException!URL {}",url);
			throw e1;
		} catch (Exception e2) {
			log.error("UnKnownException!URL {}",url);
			throw e2;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
//				System.out.println(e);
				e.printStackTrace();
			}

			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
//				System.out.println(e);
				e.printStackTrace();
			}

			if (con != null) {
				con.disconnect();
			}
		}
		return rspMsg;
	}
}
