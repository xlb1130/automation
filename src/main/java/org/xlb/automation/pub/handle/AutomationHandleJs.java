package org.xlb.automation.pub.handle;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.xlb.automation.pub.bean.Operator;

/**
 * 
 * 文件上传操作
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleJs extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		 String retStr = "";
		 JavascriptExecutor js = (JavascriptExecutor) driver; 
		 js.executeScript(oper.getOper_text()); 
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw e;
		}
		 return retStr;
	}

}
