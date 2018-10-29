package org.xlb.automation.pub.bean;

/**
 * 
 * 操作对象
 * 
 * @author Lingbo Xie
 * @date 2018-08-24
 * @version V1.0
 *
 */
public class Operator {
	/**
	 * 操作名
	 */
	private String name;

	/**
	 * 所属frame ID
	 */
	private String frame;
	
	/**
	 * 输入值 input时有效
	 */
	private String value;
	
	/**
	 * 元素查找方式
	 */
	private String find;
	
	/**
	 * 标签名
	 */
	private String type;
	
	/**
	 * 需要保存的元素查找方式
	 */
	private String save;
	
	/**
	 * 元素查找字符串
	 */
	private String find_text;
	
	/**
	 * 需要保存的元素查找字符串
	 */
	private String save_text;
	
	/**
	 * 操作延时 单位 ms
	 */
	private String operate_delay;
	
	/**
	 * 当type为select/radio等时有效
	 */
	private String oper_type;
	
	/**
	 * 操作文本
	 */
	private String oper_text;
	
	/**
	 * 输入框输入类型
	 */
	private String input_type;
	
	/**
	 * 新页面处理
	 */
	private String pop_page;
	
	private String condition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	public String getOper_text() {
		return oper_text;
	}

	public void setOper_text(String oper_text) {
		this.oper_text = oper_text;
	}

	public String getFind_text() {
		return find_text;
	}

	public void setFind_text(String find_text) {
		this.find_text = find_text;
	}

	public String getOperate_delay() {
		return operate_delay;
	}

	public void setOperate_delay(String operate_delay) {
		this.operate_delay = operate_delay;
	}

	public String getInput_type() {
		return input_type;
	}

	public void setInput_type(String input_type) {
		this.input_type = input_type;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getSave_text() {
		return save_text;
	}

	public void setSave_text(String save_text) {
		this.save_text = save_text;
	}

	public String getPop_page() {
		return pop_page;
	}

	public void setPop_page(String pop_page) {
		this.pop_page = pop_page;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}
}
