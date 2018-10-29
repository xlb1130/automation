package org.xlb.automation.pub.handle;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 鼠标悬停操作
 * 
 * @author Lingbo Xie
 * @date 2018-0901
 * @version V1.1
 *
 */
public class AutomationHandleHover extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		 WebElement element = (WebElement) AutomationFind.find(oper.getFind(), oper.getFind_text(), driver);
		 String retStr = "";
		 if(StringUtil.isBlank(oper.getInput_type())){
			 Actions action = new Actions(driver);
			 action.moveToElement(element).perform();
		 }else if(Constants.C_AUTO_MATION_MOUSE_HOVER_JS_.equals(oper.getInput_type())){
			 try {
				  String mouseHoverjs = "var evObj = document.createEvent('MouseEvents');" +
                          "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                          "arguments[0].dispatchEvent(evObj);";
			      JavascriptExecutor js = (JavascriptExecutor)driver;
			      js.executeScript(mouseHoverjs, element);
			} catch (Exception e) {
				throw new Exception("Error occured when execute Mouse Hover by js!"+e.getMessage());
			}
		 }
		 
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw new Exception("Error occured in end ckeck!"+e.getMessage());
		}
		 return retStr;
	}

}
