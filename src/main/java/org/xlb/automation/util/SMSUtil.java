package org.xlb.automation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlb.automation.boss.body.request.Request0044;
import org.xlb.automation.boss.util.RMIUtil;
import org.xlb.automation.pub.bean.ConfigBean;

public class SMSUtil {
	
	private static Logger log = LoggerFactory.getLogger(SMSUtil.class);
	
	public static String sendSms(String phone ,String content){
		System.out.println("phone:"+phone+"  content:"+content);
		if(!StringUtil.isBlank(content)){
			if(content.getBytes().length > 500){
				content = content.substring(0, 250)+"...";
			}
		}
		String responseXml = "";
		Request0044 bossRequest0044 = new Request0044();
        bossRequest0044.setMobile(phone);
        bossRequest0044.setContent(content);
        try {
			responseXml = RMIUtil.invokeHttp(bossRequest0044.getXmlInfo());
		} catch (Exception e) {
			log.error("Send SMS Error Phone is {}, Message is {}, Error Message is {}.!",phone,content, e.getMessage());
		}
        return responseXml;
	}
	
	public static String sendSms(String content){
		if(StringUtil.isBlank(ConfigBean.warning_mobile)){
			return "";
		}else{
			String[] mobiles = ConfigBean.warning_mobile.split(",");
			for(int i=0; i<mobiles.length; i++){
				sendSms(mobiles[i] , content);
			}
		}
		return "";
	}
}
