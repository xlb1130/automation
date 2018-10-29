package org.xlb.automation.pub.processor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.xlb.automation.pub.bean.Browser;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.Function;
import org.xlb.automation.pub.bean.Project;
import org.xlb.automation.pub.writer.RMIWriter;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 工程处理类
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class ProjectProcessor {
	public static void process(Project project, RMIWriter log) throws Exception{
		String browserStr = project.getBrowser();
		Browser browser = null;
		WebDriver driver =null;
		Function function = null;

		//检查测试标识是否合法
		if(!checkFlagCheck(project.getCheck_flag())){
			throw new Exception("Invalid check flag !");
		}
		
		//判断是否需要测试
		if(!project.getCheck_flag().equals(Constants.C_PUB_PUBLIC_PROJECT_CHECK_OPEN)){
			System.out.println("Project "+project.getName() + " not need check!");
			return;
		}
		
		//获取浏览器对象
		for(int i=0; i<ConfigBean.browsers.size(); i++){
			browser = ConfigBean.browsers.get(i);
			if(browser != null && browser.getName()!=null 
					&& browser.getName().equals(browserStr)){
					driver = getWebDriver(browser);
			}
		}
		
		if(driver == null){
			throw new Exception("init WebDriver fail !");
		}
		
		if(log != null){
			log.initSheet(project.getName());
		}
		
		driver.manage().window().maximize();
		driver.get(project.getIndex_page());
		
		//project.getFunctions();
		for(int i=0; i<project.getFunctions().size(); i++){
			function = project.getFunctions().get(i);
			try{
				FunctionProcessor.process(driver, function, project,log);
			}catch(Exception e){
				throw new Exception("Error occured in function\""+function.getName() +"\":"+ e.getMessage());
			}
		}	
		
		if("true".equals(ConfigBean.close_browser_after_test)){
			driver.close();
		}
	}
	
	private static WebDriver getWebDriver(Browser browser) throws MalformedURLException{
		WebDriver driver =null;
		if(browser != null && browser.getName() != null){
			if(!StringUtil.isBlank(browser.getProp_name()) 
					&& !StringUtil.isBlank( browser.getProp_value())){
				System.setProperty(browser.getProp_name(), browser.getProp_value());
			}
			if(StringUtil.isBlank(ConfigBean.remote_url )){
				if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_IE)){
					driver = new InternetExplorerDriver();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_CHROME)){
					driver = new ChromeDriver();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_FIRFOX)){				
					driver = new FirefoxDriver();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_HTML)){
					driver = new HtmlUnitDriver();
				}
			}else{
				DesiredCapabilities dc = null; 
				if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_IE)){
					dc = DesiredCapabilities.internetExplorer();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_CHROME)){
					dc = DesiredCapabilities.chrome();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_FIRFOX)){				
					dc = DesiredCapabilities.firefox();
				}else if(browser.getName().equals(Constants.C_AUTO_MATION_BROWSER_HTML)){
					dc = DesiredCapabilities.htmlUnit();
				}
				driver = new RemoteWebDriver(new URL(ConfigBean.remote_url), dc); // 这个URL
			}
			driver.manage().timeouts().pageLoadTimeout(
					Long.parseLong(ConfigBean.remote_timeout), TimeUnit.SECONDS); // 设置页面加载超时的最大时长
		}
		return driver;
	}
	
	private static boolean checkFlagCheck(String checkFlag){
		boolean ret = false;
		if(checkFlag != null){
			if(checkFlag.equals(Constants.C_PUB_PUBLIC_PROJECT_CHECK_CLOSE)
					|| checkFlag.equals(Constants.C_PUB_PUBLIC_PROJECT_CHECK_OPEN)){
				ret = true;
			}
		}		
		return ret;
	}
}
