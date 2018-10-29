package org.xlb.automation.pub.handle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.xlb.automation.pub.AutomationFind;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 下拉框操作
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleSelect extends AbstractAutomationHandle {

	@Override
	public String handle(Operator oper , WebDriver driver) throws Exception {
		Select  select = (Select) AutomationFind.find(oper.getFind(), oper.getFind_text(), driver);
		String retStr = "";
		if(StringUtil.isBlank(oper.getOper_type())){
			throw new Exception("Operator \""+oper.getName()+"\" oper type is null！");
		}
		
		if(StringUtil.isBlank(oper.getOper_text())){
			throw new Exception("Operator \""+oper.getName()+"\" oper text is null！");
		}
		
		if(!oper.getOper_type().equals("select") && !oper.getOper_type().equals("deselect")){
			throw new Exception("Operator \""+oper.getName()+"\" oper type is invalid！");
		}
		
		if(oper.getOper_type().equals("select")){
			if(oper.getOper_text().equals("_all")){
				//select.selectAll();
			}else{
				select.deselectAll();
				String type = oper.getOper_text().split(":")[0];
				String value = oper.getOper_text().split(":")[1];
				if(type.equals("visible_text")){
					select.selectByVisibleText(value);
				}else if(type.equals("value")){
					select.selectByValue(value);
				}else if(type.equals("index")){
					select.selectByIndex(Integer.parseInt(value));
				}
			}
		}else{
			if(oper.getOper_text().equals("_all")){
				select.deselectAll();
			}else{
				String type = oper.getOper_text().split(":")[0];
				String value = oper.getOper_text().split(":")[1];
				select.deselectAll();
				if(type.equals("visible_text")){
					select.deselectByVisibleText(value);
				}else if(type.equals("value")){
					select.deselectByValue(value);
				}else if(type.equals("index")){
					select.deselectByIndex(Integer.parseInt(value));
				}
			}
		}
		
		try{
			retStr = this.endCheck(oper, driver);
		}catch(Exception e){
			throw e;
		}
		 return retStr;
	}

}
