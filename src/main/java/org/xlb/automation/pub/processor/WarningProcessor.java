package org.xlb.automation.pub.processor;

import java.util.Date;

import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.ResultPubBean;
import org.xlb.automation.pub.bean.WarningUserBean;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.DateUtil;
import org.xlb.automation.util.SMSUtil;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 告警处理类
 * 
 * @author Lingbo Xie
 * @date 2018-0921
 * @version V1.0
 *
 */
public class WarningProcessor {
	
	/**
	 *  告警处理
	 * @param logBean
	 * @throws Exception
	 */
	public static void process(ResultPubBean logBean) throws Exception{
			for(int i=0; i<ConfigBean.warningUsers.size(); i++){
				innerProcess(logBean,ConfigBean.warningUsers.get(i));
			}
	}
	
	/**
	 * 告警处理
	 * @param logBean
	 * @param user
	 */
	private static void innerProcess(ResultPubBean logBean, WarningUserBean user){
		String message = "Project \""+logBean.getProject_name()+"\" ,"+
				"Function \""+logBean.getFunction_name()+"\" ,+"+
				"Operation \""+logBean.getOper_name()+
				"\" :"+logBean.getDescription();		
		 user.getFailedList().add(logBean);
		 warningLevelCheck(message ,user );
	}
	
	private static void preSendSMS(String message ,WarningUserBean user ){
		String content = message;
		if(!StringUtil.isBlank(ConfigBean.warning_message_template)){
			content = ConfigBean.warning_message_template;
			content = content.replace("#{content}", message);
			content = content.replace("#{count}", user.getFailedList().size()+"");
		}
		SMSUtil.sendSms(content);
		user.getFailedList().clear();
	}
	
	/**
	 * 告警级别处理
	 * @param message
	 * @param user
	 */
	private static void warningLevelCheck(String message ,WarningUserBean user ){
		if(user.getLastNotify() == null){
			user.setCurrentLevel(Constants.C_AUTO_MATION_WARNING_LEVEL_FIRST);
			user.setLastNotify(new Date());
		}
		try{
			//告警清除
			if(DateUtil.dateDiff(user.getLastNotify(),new Date(), Constants.C_AUTO_MATION_DATE_CALCU_MINUTE)
					> Long.parseLong(ConfigBean.warning_clear)){
				user.setLastNotify(new Date());
				user.setCurrentLevel(Constants.C_AUTO_MATION_WARNING_LEVEL_FIRST);
				preSendSMS(message,user);
			}else{
				//可继续告警
				if(Integer.parseInt(user.getCurrentLevel()) <= user.getFailedList().size()){
					user.setLastNotify(new Date());
					//提升告警级别
					user.setCurrentLevel(addWarningLevel(user.getCurrentLevel()));
					preSendSMS(message,user);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//告警升级
//		if(DateUtil.dateDiff(user.getLastNotify(),new Date(), Constants.C_AUTO_MATION_DATE_CALCU_MINUTE)
//				> Long.parseLong(ConfigBean.warning_rate)){
//			
//		}
	}
	
	/**
	 * 获取告警级别
	 * @param level
	 * @return
	 */
	private static String addWarningLevel(String level){
		if(Constants.C_AUTO_MATION_WARNING_LEVEL_FIRST.equals(level)){
			return Constants.C_AUTO_MATION_WARNING_LEVEL_SECOND;
		}else if(Constants.C_AUTO_MATION_WARNING_LEVEL_SECOND.equals(level)){
			return Constants.C_AUTO_MATION_WARNING_LEVEL_THIRD;
		}else if(Constants.C_AUTO_MATION_WARNING_LEVEL_THIRD.equals(level)){
			return Constants.C_AUTO_MATION_WARNING_LEVEL_FOURTH;
		}else{
			return Constants.C_AUTO_MATION_WARNING_LEVEL_FOURTH;
		}
	}
}
