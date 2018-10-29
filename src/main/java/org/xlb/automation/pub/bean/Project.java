package org.xlb.automation.pub.bean;

import java.util.List;

/**
 * 
 * 工程对象
 * 
 * @author Lingbo Xie
 * @date 2018-08-24
 * @version V1.0
 *
 */
public class Project {
	
	private String name;
	
	private String browser;
	
	private String index_page;
	
	private String check_flag;
	
	private String operate_delay;
	
	private List<Function> functions;

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public String getIndex_page() {
		return index_page;
	}

	public void setIndex_page(String index_page) {
		this.index_page = index_page;
	}

	public String getCheck_flag() {
		return check_flag;
	}

	public void setCheck_flag(String check_flag) {
		this.check_flag = check_flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperate_delay() {
		return operate_delay;
	}

	public void setOperate_delay(String operate_delay) {
		this.operate_delay = operate_delay;
	}
	
}
