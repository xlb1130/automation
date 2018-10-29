package org.xlb.automation.boss.body.request;

import java.io.IOException;

import org.jdom.JDOMException;
import org.xlb.automation.boss.body.RequestBody;
import org.xlb.automation.boss.util.XMLParser;
import org.xlb.automation.boss.util.XMLUtil;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 短信下发
 * 
 * @author Jack Xie
 * @since 2018-02-09
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Request0044 implements RequestBody {

	// 用户手机号码
	private String mobile;

	// 内容
	private String content;
	
	public String getXmlInfo() {
		
		XMLParser xml = XMLUtil
				.getXmlUtilByTempletString(Constants.C_PUB_XMLUTIL_CREATE_TYPE_MESSAGEREQUEST);
		// 设置请求时间
		xml.modifyEleText("head", "req_time", StringUtil.getTimeSerial());
		// 设置受理号码
		xml.modifyEleText("route", "route_value", this.mobile);
		// 设置command
		xml.modifyEleText("head", "process_code", "SendSms");
		xml.addElement("message", "Body","SendSms","");
		xml.addElement("Body","SendSms","tagset", "");
		xml.addElement("SendSms","tagset","TELNUM", this.mobile);
		xml.addElement("SendSms","tagset","SMSCONT", this.content);
//		xml.modifyEleText("tagset", "TELNUM", this.mobile);
//		xml.modifyEleText("tagset", "SMSCONT", this.content);

		try {
			xml.createXml();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		//System.out.println(xml.getXmlText(Constants.C_PUB_PUBLIC_ENCODING_GBK));
		return xml.getXmlText(Constants.C_PUB_PUBLIC_ENCODING_GBK);

	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setParam(String param) {
		// TODO Auto-generated method stub
		
	}
	
}
