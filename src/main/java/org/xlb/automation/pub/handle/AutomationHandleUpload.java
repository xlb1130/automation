package org.xlb.automation.pub.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 文件上传操作
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleUpload extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		WebElement element = (WebElement) AutomationFind.find(oper.getFind(), oper.getFind_text(), driver);
		String retStr = "";
		if(StringUtil.isBlank(oper.getOper_text())){
			throw new Exception("Operator \""+oper.getName()+"\" oper text is null！");
		}
		
		 element.sendKeys(oper.getOper_text());
		 
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw e;
		}
		 return retStr;
	}

}
