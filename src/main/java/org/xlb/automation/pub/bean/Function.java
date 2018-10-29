package org.xlb.automation.pub.bean;

import java.util.List;

/**
 * 
 * 功能对象
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class Function {
	private String name;
	
	private String operate_delay;
	
	private String error_to_page;
	
	public String getError_to_page() {
		return error_to_page;
	}

	public void setError_to_page(String error_to_page) {
		this.error_to_page = error_to_page;
	}

	private List<Operator> operators;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	public String getOperate_delay() {
		return operate_delay;
	}

	public void setOperate_delay(String operate_delay) {
		this.operate_delay = operate_delay;
	}

}
