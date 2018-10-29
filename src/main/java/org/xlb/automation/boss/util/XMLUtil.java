package org.xlb.automation.boss.util;

import java.io.IOException;
import java.util.Map;

import org.jdom.JDOMException;
import org.xlb.automation.util.Constants;

public class XMLUtil {
	/**
	 * 请求报文生成：合并报文头报文体
	 * 
	 * @param xml1
	 * @param xml2
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static String xmlCombine(XMLParser xml1, XMLParser xml2) {
		String xml = "";

		try {
			xml1.createXml();

			if (xml1.isCreate()) {
				xml = xml1
						.getXmlText(Constants.C_PUB_PUBLIC_ENCODING_GBK);
			}

			xml2.createXml();
			StringBuilder sb = new StringBuilder();

			if (xml2.isCreate() && xml != null && !"".equals(xml)) {
				sb.append("<message_body>\n\r")
						.append("<![CDATA[")
						.append(xml2
								.getXmlText(Constants.C_PUB_PUBLIC_ENCODING_GBK))
						.append("]]>\n\r").append("</message_body>\n\r");
				// System.out.println(sb);
				xml = xml.replace("<message_body />", sb.toString());
			}
			return xml;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 请求报文生成：根据报文模板字符串生成报文
	 * 
	 * @param templet
	 * @return
	 */
	public static XMLParser getXmlUtilByTempletString(String templet) {
		return new XMLParser(XMLTemplet.getInstance(templet));
	}

	/**
	 * 请求报文生成：根据报文模板字符串生成报文
	 * 
	 * @param templet
	 * @return
	 */
	public static XMLParser getXmlUtilByTempletString(Map<String,String> elementMap,
			Map<String,String> attrMap, Map<String,String> paramMap, String rootName) {
		return new XMLParser(XMLTemplet.getInstance(elementMap, attrMap,
				paramMap, rootName));
	}

}
