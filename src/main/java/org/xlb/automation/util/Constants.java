package org.xlb.automation.util;

public final class Constants {
	
	/**
	 * XMLUtil生成XML对应元素分隔符
	 */
	public static final String C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE = "@@";
	
	/**
	 * XMLUtil生成UAP请求报文模板
	 */
	public static final String C_PUB_XMLUTIL_CREATE_TYPE_MESSAGEREQUEST= "CommonRequestHead";
	
	/**
	 * utf-8编码
	 */
	public static final String C_PUB_PUBLIC_ENCODING_UTF8 = "utf-8";
	
	/**
	 * gbk编码
	 */
	public static final String C_PUB_PUBLIC_ENCODING_GBK = "gbk";
	
	/**
	 * 需要测试标识
	 */
	public static final String C_PUB_PUBLIC_PROJECT_CHECK_OPEN = "1";
	
	/**
	 * 不需要测试标识
	 */
	public static final String C_PUB_PUBLIC_PROJECT_CHECK_CLOSE= "0";
	
	/**
	 * 不打开浏览器
	 */
	public static final String C_AUTO_MATION_BROWSER_HTML="html";
	
	/**
	 * IE浏览器
	 */
	public static final String C_AUTO_MATION_BROWSER_IE="ie";
	
	/**
	 * firfox浏览器
	 */
	public static final String C_AUTO_MATION_BROWSER_FIRFOX="firfox";
	
	/**
	 * chrome浏览器
	 */
	public static final String C_AUTO_MATION_BROWSER_CHROME="chrome";
	
	/**
	 * input输入框
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_INPUT="input";
	
	/**
	 * select选择框
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_SELECT="select";
	
	/**
	 * radio单选框
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_RADIO="radio";
	
	/**
	 * checkbox多选框
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_CHECKBOX="checkbox";
	
	/**
	 * button按钮
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_BUTTON="button";
	
	/**
	 * dialog对话框
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_DIALOG="dialog";
	
	/**
	 * form表单
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_FORM="form";
	
	/**
	 * upload上传文件
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_UPLOAD="upload";
	
	/**
	 * drag拖拽操作
	 */
	public static final String C_AUTO_MATION_OPER_TYPE_DRAG="drag";
	
	/**
	 * 按照name查找元素
	 */
	public static final String C_AUTO_MATION_FIND_TYPE_NAME="name";
	
	/**
	 * 按照id查找元素
	 */
	public static final String C_AUTO_MATION_FIND_TYPE_ID="id";
	
	/**
	 * 按照class查找元素
	 */
	public static final String C_AUTO_MATION_FIND_TYPE_CLASS="class";
	
	/**
	 * 按照text查找元素
	 */
	public static final String C_AUTO_MATION_FIND_TYPE_TEXTS="text";
	
	/**
	 * 按照css查找元素
	 */
	public static final String C_AUTO_MATION_FIND_TYPE_CSS="css";
	
	/**
	 * 输入方式 调用groovy脚本
	 */
	public static final String C_AUTO_MATION_INPUT_TYPE_GROOVY="groovy";
	
	/**
	 * 鼠标悬停调用js脚本实现
	 */
	public static final String C_AUTO_MATION_MOUSE_HOVER_JS_="js";
	
	/**
	 * 鼠标悬停调用webdriver的action实现
	 */
	public static final String C_AUTO_MATION_MOUSE_HOVER_ACTION="action";
	
	/**
	 * 新页面处理方式   切换到新页面
	 */
	public static final String C_AUTO_MATION_POP_PAGE_SWITCH="switch";
	
	/**
	 * 新页面处理方式   关闭新页面
	 */
	public static final String C_AUTO_MATION_POP_PAGE_CLOSE="close";
	
	/**
	 * 错误通知级别1
	 */
	public static final String C_AUTO_MATION_WARNING_LEVEL_FIRST = "1";
	
	/**
	 * 错误通知级别2
	 */
	public static final String C_AUTO_MATION_WARNING_LEVEL_SECOND = "5";
	
	/**
	 * 错误通知级别3
	 */
	public static final String C_AUTO_MATION_WARNING_LEVEL_THIRD = "20";
	
	/**
	 * 错误通知级别4
	 */
	public static final String C_AUTO_MATION_WARNING_LEVEL_FOURTH = "100";
	
	/**
	 * 按照秒获取时间
	 */
	public static final long C_AUTO_MATION_DATE_CALCU_SECOND = 1000;
	
	/**
	 * 按照分钟获取时间
	 */
	public static final long C_AUTO_MATION_DATE_CALCU_MINUTE = 60000;
	
	/**
	 * 按照小时获取时间
	 */
	public static final long C_AUTO_MATION_DATE_CALCU_HOUR = 3600000;
}
