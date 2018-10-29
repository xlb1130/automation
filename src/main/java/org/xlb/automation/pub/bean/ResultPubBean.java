package org.xlb.automation.pub.bean;

/**
 * 
 * 日志对象
 * 
 * @author Lingbo Xie
 * @date 2018-08-24
 * @version V1.0
 *
 */
public class ResultPubBean {
	
	private String project_name;
	
	private String function_name;
	
	private String oper_name;
	
	private String deal_time;
	
	private String cost_time;
	
	private String result;
	
	private String description;

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getDeal_time() {
		return deal_time;
	}

	public void setDeal_time(String deal_time) {
		this.deal_time = deal_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCost_time() {
		return cost_time;
	}

	public void setCost_time(String cost_time) {
		this.cost_time = cost_time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
