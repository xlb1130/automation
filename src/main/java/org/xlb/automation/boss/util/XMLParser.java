package org.xlb.automation.boss.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xlb.automation.util.Constants;

/**
 * 
 * XMLUtil XML生成&解析类 客服专题项目3 新增
 * 
 * 
 * @author Jack Xie
 * @time 20141022
 */
public class XMLParser {

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

	/**
	 * 文档对象
	 */
	private Document document;

	/**
	 * 生成标志
	 */
	private boolean createFlag = false;

	/**
	 * 解析标志
	 */
	private boolean parseFlag = false;
	
	/**
	 * 输入流
	 */
	private InputStream is;

	/**
	 * 自定义XML文件生成
	 * 
	 * @param elementMap
	 * @param attrMap
	 * @param paramMap
	 * @param rootName
	 */
	public XMLParser(Map<String,String> elementMap, Map<String,String> attrMap, 
			Map<String,String> paramMap, String rootName) {
		this.elementMap = elementMap;
		this.attrMap = attrMap;
		this.paramMap = paramMap;
		this.rootName = rootName;
	}

	/**
	 * 根据XMLTemplet模板创建XML
	 * 
	 * @param createType
	 */
	public XMLParser(XMLTemplet templet) {
		this.rootName = templet.getRootName();
		this.elementMap = templet.getElementMap();
		this.paramMap = templet.getParamMap();
		this.attrMap = templet.getAttrMap();
	}

	/**
	 * 默认无参构造函数
	 */
	public XMLParser() {
		super();
		mapInit();
	}

	private void mapInit() {
		this.attrMap = new HashMap<String,String>();
		this.elementMap = new HashMap<String,String>();
		this.paramMap = new HashMap<String,String>();
	}
	
	/**
	 * 清空 在解析之前清空map以及document对象
	 * @return
	 */
	public boolean clear(){
		this.paramMap.clear();
		this.elementMap.clear();
		this.attrMap.clear();
		this.rootName = "";
		this.document = null;
		this.createFlag = false;
		this.parseFlag = false;
		return true;
	}

	/**
	 * 根据map中的节点关系创建xml的document对象
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void createXml() throws IOException, JDOMException {
		// 根节点元素为空 返回生成失败
		if (this.rootName == null || this.rootName.equals("")) {
			throw new JDOMException(
					"The root element name is null, please check!");
		}
		// 创建根节点 并设置它的属性 ;
		Element rootEle = new Element(rootName);
		rootEle = listMap2XMLAddSubEle(rootEle,
				Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + rootName);
		// 将根节点添加到文档中；
		this.document = new Document(rootEle);
		this.createFlag = true;
	}

	/**
	 * 子元素生成
	 * 
	 * @param ele
	 * @param elename
	 * @return
	 */
	private Element listMap2XMLAddSubEle(Element ele, String elename) {
		// 设置属性
		ele = listMap2XMLAddAttr(ele, elename);

		// System.out.print("elementMap.get(\"" + elename + "\") = ");
		// System.out.println(elementMap.get(elename));
		// System.out.println("***************************************");
		String subEleNamesStr = (String) elementMap.get(elename);
		if (subEleNamesStr == null || subEleNamesStr.equals(""))
			return ele;
		String[] subEleNames = subEleNamesStr
				.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE);
		String subEleName = "";
		for (int i = 0; i < subEleNames.length; i++) {
			subEleName = subEleNames[i];
			Element tempEle = new Element(subEleName);
			// 设置值
			tempEle.setText((String) paramMap.get(elename
					.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE)[1]
					+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
					+ subEleName));
			// 子元素
			tempEle = listMap2XMLAddSubEle(tempEle, elename
					.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE)[1]
					+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
					+ subEleName);
			ele.addContent(tempEle);
		}
		return ele;
	}

	/**
	 * 元素属性生成
	 * 
	 * @param ele
	 * @param elename
	 * @return
	 */
	private Element listMap2XMLAddAttr(Element ele, String elename) {
		// System.out.print("attrMap.get(\"" + elename + "\") = ");
		// System.out.println(attrMap.get(elename));
		// System.out.println("***************************************");
		String attrNameStr = (String) attrMap.get(elename);
		if (attrNameStr == null || attrNameStr.equals(""))
			return ele;
		String[] attrNames = attrNameStr
				.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE);
		String attrName = "";
		for (int i = 0; i < attrNames.length; i++) {
			attrName = attrNames[i];
			// System.out.println(elename+"@@"+attrName);
			ele.setAttribute(attrName, (String) paramMap.get(elename
					.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE)[1]
					+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
					+ attrName));
		}
		return ele;
	}

	/**
	 * 根据输入流解析XML文件
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void parseXml(InputStream is) throws JDOMException, IOException {
		this.clear();
		SAXBuilder builder = new SAXBuilder();
		this.document = builder.build(is);// 获得文档对象
		Element root = document.getRootElement();// 获得根节点
		this.rootName = root.getName();
		parseElement(null, root);
	}
	
	/**
	 * 解析XML文件
	 * @param is
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void parseXml() throws JDOMException, IOException {
		if (this.is == null){
			return;
		}
		this.parseXml(this.is);
	}
	
	/**
	 * 解析节点元素
	 * @param ppEle 父节点
	 * @param element 当前节点
	 */
	private void parseElement(Element ppEle ,Element element){
		//System.out.println(element.getName());
		@SuppressWarnings("unchecked")
		List<Element> list = element.getChildren();
		String ppName = "";
		String parentName = element.getName();
		String subsNames = "";
		String tempKey = "";
		
		if (ppEle != null){ //element不是根元素
			ppName = ppEle.getName();
		}
		
		tempKey = ppName + Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + parentName;
		
		for (Element e : list) {
			if (subsNames == null || subsNames.equals("")){
				subsNames += e.getName();
			}else{
				subsNames = subsNames
						+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
						+ e.getName();
			}
			this.parseElement(element, e);
		}
		this.parseAttribute(element);
		this.elementMap.put(tempKey, subsNames);
		this.paramMap.put(tempKey, element.getText());
	}
	
	/**
	 * 解析节点属性
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	private void parseAttribute(Element element){
		if (element == null){ //element不是根元素
			return;
		}
		
		String tempKey = "";
		List<Attribute> list = element.getAttributes();
		for (Attribute a : list) {
			tempKey = element.getName() + Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE +a.getName();
			this.attrMap.put(tempKey, a.getValue());
		}
	}
	
	/**
	 * 增加元素
	 * @param ppName 父元素的父元素名 可为空
	 * @param parentName 父元素名 不可为空
	 * @param name 新增元素名 不可为空
	 * @param value 节点值  可为空
	 * @return
	 */
	public boolean addElement(String ppName, String parentName, String name, String value) {
		boolean flag = false;
		String tempValue = "";
		String tempKey = ppName
			+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + parentName;
		if (name == null || name.equals("") || ppName == null || ppName.equals(""))
			return flag;
		tempValue = (String) this.elementMap.get(tempKey);
		if (tempValue == null || tempValue.equals("")){
			tempValue = name;
		}else{
			tempValue = tempValue + Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + name;
		}
		this.elementMap.put(tempKey, tempValue);
		
		tempKey = parentName
		+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + name;
		
		this.paramMap.put(tempKey, value);
		flag = true;
		return flag;
	}
	
	/**
	 * 删除元素
	 * @param ppName 父元素的父元素名 可为空
	 * @param parentName 父元素名 不可为空
	 * @param name 要删除的元素名 不可为空
	 * @return
	 */
	public boolean removeElement(String ppName, String parentName, String name){
		boolean flag = false;
		String tempValue = "";
		String tempKey = ppName
				+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + parentName;
		if (name == null || name.equals("") || ppName == null
				|| ppName.equals(""))
			return flag;
		tempValue = (String) this.elementMap.get(tempKey);
		if (tempValue == null
				|| tempValue
						.indexOf(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE) < 0) {
			this.elementMap.remove(tempKey);
		} else {
			tempValue = tempValue.replaceFirst(name, "");
			tempValue = tempValue.replaceFirst(
					Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
							+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE,
					Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE);
		}
		tempKey = parentName + Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE
				+ name;
		this.paramMap.remove(tempKey);
		
		//删除对应元素的属性 重复问题未解决
		
		flag = true;
		return flag;
	}
	
	/**
	 * 修改元素值
	 * @param pName
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean modifyEleText(String pName,String name, String value){
		String tempKey = pName
		+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + name;
		this.paramMap.put(tempKey, value);
		return true;
	}
	
	/**
	 * 为指定节点增加属性
	 * @param eleName
	 * @param attrName
	 * @param value
	 * @return
	 */
	public boolean addAttribute(String eleName, String attrName, String value){
		boolean flag = false;
		String tempKey = eleName
				+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + attrName;
		if (eleName == null || eleName.equals("") || attrName == null
				|| attrName.equals(""))
			return flag;
		this.attrMap.put(tempKey, value);
		flag = true;
		return flag;
	}
	
	/**
	 * 为指定节点删除属性
	 * @param eleName
	 * @param attrName
	 * @param value
	 * @return
	 */
	public boolean removeAttribute(String eleName, String attrName, String value){
		boolean flag = false;
		String tempKey = eleName
				+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + attrName;
		if (eleName == null || eleName.equals("") || attrName == null
				|| attrName.equals(""))
			return flag;
		this.attrMap.remove(tempKey);
		flag = true;
		return flag;
	}

	/**
	 * 将XML文件转化为字符串返回 默认使用 UTF-8编码方式
	 * @return
	 */
	public String getXmlText() {
		return getXmlText("UTF-8");
	}

	/**
	 * 将XML文件转化为字符串返回
	 * @param encoding 编码方式
	 * @return
	 */
	public String getXmlText(String encoding) {
		if (this.document == null)
			return null;
		Format format = Format.getPrettyFormat();
		format.setEncoding(encoding);
		XMLOutputter XMLOut = new XMLOutputter(format);
		return XMLOut.outputString(document);
	}
	
	/**
	 * 获取解析后的节点值 如果为根节点 则父节点传入 ""
	 * @param parentName 父节点名
	 * @param elementName 节点名
	 * @return
	 */
	public String getElementValue(String parentName, String elementName){
		String value = null;
		Object tempObj = null;
		String tempKey = parentName
		+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + elementName;
		if (parentName == null){
			tempObj = this.getElementValue(elementName); //不给定父节点 进行模糊查询
		}else{
			tempObj = this.paramMap.get(tempKey); //给定父节点 直接从Map中取
		}
		if (tempObj != null){
			value = (String) tempObj;
		}
		
		return value;
	}
	
	/**
	 * 获取解析后的节点值 不给定父节点需要模糊查询，对整个Map进行遍历
	 * @param elementName
	 * @return
	 */
	private Object getElementValue(String elementName){
		Object tempObj = null;
		String tempKey = "";
		Iterator<String> itor = this.paramMap.keySet().iterator();
		while(itor.hasNext()){
			tempKey =  itor.next();
			if (elementName.equals(tempKey.split(Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE)[1])){
				tempObj = this.paramMap.get(tempKey);
				break;
			}			
		}
		return tempObj;
	}
	
	/**
	 * 获取解析后的属性值
	 * @param elementName
	 * @param attrName
	 * @return
	 */
	public String getAttributeValue(String elementName, String attrName){
		if (this.attrMap == null) return null;
		String tempKey = elementName
				+ Constants.C_PUB_XMLUTIL_MAP_ELEMENT_SEPARATE + attrName;
		return (String) this.attrMap.get(tempKey);
	}

	/**
	 * 是否已执行解析
	 * 
	 * @return
	 */
	public boolean isParse() {
		return this.parseFlag;
	}

	/**
	 * 是否已执行生成
	 * 
	 * @return
	 */
	public boolean isCreate() {
		return this.createFlag;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Map<String,String> getElementMap() {
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

	public InputStream getInputStream() {
		return is;
	}

	public void setInputStream(InputStream is) {
		this.is = is;
	}
}