package org.xlb.automation.pub.handle;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 对话框操作
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleDialog  extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		Alert  alert = (Alert ) AutomationFind.find(oper.getFind(), oper.getFind_text(), driver);
		String retStr = "";
		if(StringUtil.isBlank(oper.getOper_type())){
			throw new Exception("Operator \""+oper.getName()+"\" oper type is null！");
		}
		
		if(!oper.getOper_type().equals("accept") && !oper.getOper_type().equals("dismiss")){
			throw new Exception("Operator \""+oper.getName()+"\" oper type is invalid！");
		}
		 
		 if(oper.getOper_type().equals("accept")){
			 alert.accept();
		 }else{
			 alert.dismiss();
		 }
		 
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw e;
		}
		 return retStr;
	}

}
