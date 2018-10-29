package org.xlb.automation.pub.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Condition;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 操作处理抽象类
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public abstract class AbstractAutomationHandle implements IAutomationHandle {
	
	public void beforeCheck(Operator oper , WebDriver driver){
		
	}

	public abstract String handle(Operator oper , WebDriver driver) throws Exception ;
	
	/**
	 *  操作执行结束之后
	 *  进行的相关检查以及信息保存
	 * @param oper
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String endCheck(Operator oper , WebDriver driver) throws Exception{
		String ret = "";
		ret = saveCheck(oper,driver);
		try {
			popCheck(oper , driver);
		} catch (Exception e) {
			throw e;
			//throw new Exception(e.getMessage());
		}
		 
		 if(!conditionHandle(oper,driver)){
			 throw new Exception("Condition check failed!");
		 }
		 return ret;
	}
	
	/**
	 *  条件判断
	 *  检查本次测试是否成功
	 *  成功返回true
	 * @param oper
	 * @param driver
	 * @return
	 */
	private boolean conditionHandle(Operator oper , WebDriver driver){
		Condition condition = ConfigBean.conditionMap.get(oper.getCondition());
		if(condition != null){
			
		}
		return true;
	}
	
	/**
	 * 信息保存相关处理
	 * @param oper
	 * @param driver
	 * @return
	 */
	private String saveCheck(Operator oper , WebDriver driver){
		 if(!StringUtil.isBlank(oper.getSave()) && !StringUtil.isBlank(oper.getSave_text()) ){
			 WebElement  element = (WebElement) AutomationFind.find(oper.getSave(), oper.getSave_text(), driver);
			 return element.getText();
		 }
		 return "";
	}
	
	/**
	 * 本次操作如果会弹出新页面
	 * 根据配置决定是否切换到新页面继续后面的操作
	 * 以及是关闭新页面或者关闭旧页面
	 * @param oper
	 * @param driver
	 * @throws Exception
	 */
	private void popCheck(Operator oper , WebDriver driver) throws Exception{
		 if(!StringUtil.isBlank(oper.getPop_page())){
			 String currentHandle = driver.getWindowHandle(); 
			
			 //配置为切换新页面
			 if(Constants.C_AUTO_MATION_POP_PAGE_SWITCH.equals(oper.getPop_page())){
				 Thread.sleep(Long.parseLong(ConfigBean.multi_page_time));
				 if(driver.getWindowHandles().remove(currentHandle)){
					 String[] handles=new String[driver.getWindowHandles().size()];
					 driver.getWindowHandles().toArray(handles);
					 //关闭当前页面
					 driver.close();
					 //切换到新弹出页面
					 driver.switchTo().window(handles[1]);
				 }else{
					 throw new Exception("Window swith failed!");
				 }
			 //如果配置为在当前页面继续执行
			 }else if(Constants.C_AUTO_MATION_POP_PAGE_CLOSE.equals(oper.getPop_page())){
				 Thread.sleep(Long.parseLong(ConfigBean.multi_page_time));
				 String[] handles=new String[driver.getWindowHandles().size()];			 
				 driver.getWindowHandles().toArray(handles);
				 //切换到新页面
				 driver.switchTo().window(handles[1]);
				 driver.getWindowHandles().remove(handles[1]);
				 //关闭新页面
				 driver.close();
				 //切换到旧页面
				 driver.switchTo().window(currentHandle);
			 }
		 }
	}

}
