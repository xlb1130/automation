package org.xlb.automation.boss.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * XMLTemplet --XMLUtil进行XML生成&解析的模板类
 * 电子渠道 新增
 * 
 * @author Jack Xie
 * @time 20141022
 */
public class XMLTemplet {
	/**
	 * 元素关系Map key:父元素名@@元素名 value：子元素名1@@子元素名2@@子元素名3
	 */
	private Map<String,String> elementMap;

	/**
	 * 元素属性关系Map key:父元素名@@元素名 value：属性名1@@属性名2@@属性名3
	 */
	private Map<String,String> attrMap;

	/**
	 * 元素或属性对应值Map key:父元素名@@元素名/元素名@@属性名 value：值
	 */
	private Map<String,String> paramMap;

	/**
	 * 根节点元素名
	 */
	private String rootName;
	
	private XMLTemplet(){
		mapInit();
		initRequestXml();
	}
	
	private XMLTemplet(Map<String,String> elementMap, Map<String,String> attrMap, 
			Map<String,String> paramMap, String rootName){
		this.elementMap = elementMap;
		this.attrMap = attrMap;
		this.paramMap = paramMap;
		this.rootName = rootName;
	}
	
	/**
	 * 获取XML报文头模板实例
	 * @param templetType
	 * @return
	 */
	public static XMLTemplet getInstance(String templetType){
			return new XMLTemplet();
	}
	
	/**
	 * 根据传入参数获取XML模板实例，一般用于获取报文体
	 * @param elementMap
	 * @param attrMap
	 * @param paramMap
	 * @param rootName
	 */
	public static XMLTemplet getInstance(Map<String,String> elementMap, Map<String,String> attrMap, 
			Map<String,String> paramMap, String rootName){
		return new XMLTemplet(elementMap,attrMap,paramMap,rootName);
	}
	
	private void mapInit() {
		this.attrMap = new HashMap<String,String>();
		this.elementMap = new HashMap<String,String>();
		this.paramMap = new HashMap<String,String>();
	}
	
	/**
	 * PublicConstants.C_PUB_XMLUTIL_CREATE_TYPE_MESSAGEREQUEST 对应类型XML生成
	 */
	private void initRequestXml() {
		rootName = "message";
		// elementMap = new HashMap();
		elementMap.put("@@message", "head@@Body");
		elementMap
				.put(
						"message@@head",
						"menuid@@process_code@@verify_code@@req_time@@req_seq@@unicontact@@testflag@@route@@channelinfo");
		elementMap.put("head@@route", "route_type@@route_value");
		elementMap.put("head@@channelinfo",
				"operatorid@@channelid@@unitid");

		// attrMap = new HashMap();
		attrMap.put("message@@head", "version");

		// paramMap = new HashMap();
		paramMap.put("@@message_request", "");
		paramMap.put("message@@head", "");
		paramMap.put("message@@Body", "");
		paramMap.put("head@@version", "1.0");
		paramMap.put("head@@menuid", "qryMuster");
		paramMap.put("head@@process_code", "");
		paramMap.put("head@@verify_code", " 1");
		paramMap.put("head@@req_time", "20121106142032");
		paramMap.put("head@@req_seq", "253868");
		paramMap.put("head@@unicontact", "47");
		paramMap.put("head@@testflag", "1");
		paramMap.put("head@@route", "");
		paramMap.put("head@@channelinfo", "");
		paramMap.put("route@@route_type", "1");
		paramMap.put("route@@route_value", "18688950454");
		paramMap.put("channelinfo@@operatorid", "101");
		//暂时没有微厅渠道，先使用其他的bsacSms
		//paramMap.put("channelinfo@@channelid", "bsacWap");
		paramMap.put("channelinfo@@channelid", "Weixin");
		paramMap.put("channelinfo@@unitid", "HUAWEI");

	}

	public Map<String, String> getElementMap() {
		return elementMap;
	}

	public void setElementMap(Map<String,String> elementMap) {
		this.elementMap = elementMap;
	}

	public Map<String,String> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(Map<String,String> attrMap) {
		this.attrMap = attrMap;
	}

	public Map<String,String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String,String> paramMap) {
		this.paramMap = paramMap;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
}
