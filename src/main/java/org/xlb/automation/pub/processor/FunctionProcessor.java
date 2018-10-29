package org.xlb.automation.pub.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.Function;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.pub.bean.Project;
import org.xlb.automation.pub.bean.ResultPubBean;
import org.xlb.automation.pub.handle.AutomationHandleFactory;
import org.xlb.automation.pub.handle.IAutomationHandle;
import org.xlb.automation.pub.writer.RMIWriter;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 功能处理类
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class FunctionProcessor {
	@SuppressWarnings("static-access")
	public static void process(WebDriver driver ,Function function ,Project project, RMIWriter log) throws Exception{
		Operator oper = null;
		IAutomationHandle handle= null;
		ResultPubBean logBean = null;
		if(driver == null){
			throw new Exception("WebDriver is null!");
		}
		
		if(function == null){
			throw new Exception("Function is null!");
		}
		
		if(project.getName() == null || project.getName().equals("")){
			throw new Exception("Invalid project name!");
		}
		
		if(function.getName() == null || function.getName().equals("")){
			throw new Exception("Invalid function name!");
		}
		
		if(function.getOperators() == null){
			System.out.println("No Operators in Project:"+project.getName()+"    Function:"+function.getName());
			return;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0; i<function.getOperators().size(); i++){
			oper = function.getOperators().get(i);
			logBean = new ResultPubBean();
			long startTime = System.currentTimeMillis();
			try {
				logBean.setProject_name(project.getName());
				logBean.setFunction_name(function.getName());
				logBean.setOper_name(oper.getName());
				logBean.setResult("success");
				logBean.setDeal_time(sf.format(new Date()));
				//frame 切换
				if(StringUtils.isNotBlank(oper.getFrame())){
					if("top".equals(oper.getFrame()))
						driver.switchTo().defaultContent();
					else{
						if(oper.getFrame().contains("/")){
							WebElement frame=driver.findElement(By.xpath(oper.getFrame()));
							driver.switchTo().frame(frame);
						}else
							driver.switchTo().frame(oper.getFrame());
					}
				}
				handle = AutomationHandleFactory.getHandleInstanceByTagName(StringUtil.toUpperCaseFirstOne(oper.getType()));
				logBean.setDescription(handle.handle(oper ,driver));		
			} catch (InstantiationException e) {
				logBean.setDescription("Error occured in operation \""+oper.getName()+"\" :"+e.getMessage());
				logBean.setResult("fail");
				e.printStackTrace();
				//throw e;
			} catch (IllegalAccessException e) {
				logBean.setDescription("Error occured in operation \""+oper.getName()+"\" :"+e.getMessage());
				logBean.setResult("fail");
				e.printStackTrace();
				//throw e;
			} catch (ClassNotFoundException e) {
				logBean.setDescription("Error occured in operation \""+oper.getName()+"\" :"+e.getMessage());
				logBean.setResult("fail");
				e.printStackTrace();
				//throw e;
			} catch(Exception e){
				logBean.setDescription("Error occured in operation \""+oper.getName()+"\" :"+e.getMessage());
				logBean.setResult("fail");
				e.printStackTrace();
				//throw new Exception("Error occured in operation \""+oper.getName()+"\" :"+e.getMessage());
			}
			logBean.setCost_time((System.currentTimeMillis() - startTime) + " ms");
			Thread.currentThread().sleep(FunctionProcessor.getOperateDelay(project, function, oper));
			
			if(log != null){
				log.writeLog(logBean);
			}		
			
			if("fail".equals(logBean.getResult())){
				//如果启用了告警功能，处理告警
				if("true".equals(ConfigBean.warning)){			
					WarningProcessor.process(logBean);
				}
				//如果配置了错误跳转页面，则跳转
				if(!StringUtil.isBlank(function.getError_to_page())){
					driver.get(function.getError_to_page());
				}			
				break;
			}
		}	
	}
	
	/**
	 * 获取操作延时
	 * @param project
	 * @param function
	 * @param oper
	 * @return
	 */
	private static long getOperateDelay(Project project ,Function function ,Operator oper){
		String time_delay = ConfigBean.operate_delay;
		if(project!=null && !StringUtil.isBlank(project.getOperate_delay())){
			time_delay = project.getOperate_delay();
		}
		if(function!=null && !StringUtil.isBlank(function.getOperate_delay())){
			time_delay = function.getOperate_delay();
		}
		if(oper!=null && !StringUtil.isBlank(oper.getOperate_delay())){
			time_delay = oper.getOperate_delay();
		}	
		return Long.parseLong(time_delay);
	}
	
}
