package org.xlb.automation.pub.bean;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Lingbo Xie
 * @since 2018-08--23
 * @version V1.0
 *
 */
public class ConfigBean {
	
	/**
	 * 版本号
	 */
	public static String version;
	
	/**
	 * 数据文件格式
	 */
	public static String log_file_format;
	
	/**
	 * 相关文件保存路径
	 */
	public static String save_file_path;
	
	/**
	 * 告警短信接收号码
	 */
	public static String warning_mobile;
	
	/**
	 * 是否告警
	 */
	public static String warning;
	
	/**
	 * 告警频率
	 */
	public static String warning_rate;
	
	/**
	 * 告警清除频率
	 */
	public static String warning_clear;
	
	/**
	 * BOSS UAP接口地址
	 */
	public static String boss_uap_inter;
	
	/**
	 * 告警短信模板
	 */
	public static String warning_message_template;
	
	/**
	 * 是否需要写日志 true:需要 ，false:不需要
	 */
	public static String write_logs;
	
	/**
	 * 执行次数
	 */
	public static String execute_count;
	
	/**
	 * groovy脚本路径
	 */
	public static String groovy_file_path;
	
	/**
	 * 每次测试结束后是否关闭浏览器
	 */
	public static String close_browser_after_test;
	
	/**
	 * 操作延迟
	 */
	public static String operate_delay;
	
	/**
	 * 远程调用地址
	 */
	public static String remote_url;
	
	/**
	 * 远程调用超时时间设置
	 */
	public static String remote_timeout;
	
	/**
	 * 页面切换等待时间
	 */
	public static String multi_page_time;

	/**
	 * 日志文件路径
	 */
	public static String log_file_path;
	
	/**
	 * 告警列表
	 */
	public static List<WarningUserBean> warningUsers;
	
	/**
	 * 自动化测试list
	 */
	public static List<Project> projects;
	
	/**
	 * 浏览器定义
	 */
	public static List<Browser> browsers;
	
	/**
	 * 条件定义
	 */
	public static Map<String, Condition> conditionMap;
	
	private ConfigBean(){
		
	}
}
