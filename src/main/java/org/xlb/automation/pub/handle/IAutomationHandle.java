package org.xlb.automation.pub.handle;

import org.openqa.selenium.WebDriver;
import org.xlb.automation.pub.bean.Operator;

/**
 * 
 * 操作处理类接口
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public interface IAutomationHandle {
	String handle(Operator oper, WebDriver driver) throws Exception;
}
