package org.xlb.automation.pub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 元素查找类
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class AutomationFind {
	public static Object find(String by_name,String  by_value, WebDriver driver){
		Object element = null;
		if(!StringUtil.isBlank(by_name) && !StringUtil.isBlank(by_value)
				&& driver != null){
			if(Constants.C_AUTO_MATION_FIND_TYPE_ID.equals(by_name)){
				element = driver.findElement(By.id(by_value));
			}else if(Constants.C_AUTO_MATION_FIND_TYPE_NAME.equals(by_name)){
				element = driver.findElement(By.name(by_value));
			}else if(Constants.C_AUTO_MATION_FIND_TYPE_CLASS.equals(by_name)){
				element = driver.findElement(By.className(by_value));
			}else if(Constants.C_AUTO_MATION_FIND_TYPE_CSS.equals(by_name)){
				element = driver.findElement(By.cssSelector(by_value));
			}else if(Constants.C_AUTO_MATION_FIND_TYPE_TEXTS.equals(by_name)){
				element = driver.findElement(By.linkText(by_value));
			}
		}
		return element;
	}
}
