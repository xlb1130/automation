package org.xlb.automation.pub.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xlb.automation.groovy.Java2GroovyDynamic;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 输入操作
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleInput extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		 WebElement element = (WebElement) AutomationFind.find(oper.getFind(), oper.getFind_text(), driver);
		 String value = "";
		 String retStr = "";
		 if(StringUtil.isBlank(oper.getInput_type())){
			 value = oper.getValue();
		 }else if(Constants.C_AUTO_MATION_INPUT_TYPE_GROOVY.equals(oper.getInput_type())){
			 try {
				value= Java2GroovyDynamic.getInputFromGroovy(oper.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				return "Groovy <"+oper.getValue()+"> executed error!";
			}
		 }
		 element.clear();
		 element.sendKeys(value);
		 
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw e;
		}
		 return retStr;
	}

}
