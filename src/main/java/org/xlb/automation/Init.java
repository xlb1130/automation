package org.xlb.automation;

import groovy.util.GroovyScriptEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xlb.automation.pub.bean.Browser;
import org.xlb.automation.pub.bean.Condition;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.Function;
import org.xlb.automation.pub.bean.Operator;
import org.xlb.automation.pub.bean.Project;
import org.xlb.automation.pub.bean.WarningUserBean;
import org.xlb.automation.util.Constants;
import org.xlb.automation.util.StringUtil;

/**
 * 
 * 初始化类
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class Init {
	public static void init() throws Exception{
		initConfig();
		initXML();
		initGroovy();
		initWarning();
	}
	
	private static void initGroovy() {
	    try {
			@SuppressWarnings("unused")
			GroovyScriptEngine scriptEngine = 
					new GroovyScriptEngine(System.getProperty("user.dir") 
							+ ConfigBean.groovy_file_path, Class.forName("org.xlb.automation.Init").getClassLoader());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	/**
	 * 加载配置文件
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void initConfig() throws FileNotFoundException, IOException{
		Properties property = new Properties();
		// 打包发布时需要改为 config.properties
		// idea直接运行改为 src/main/resources/config.properties
		property.load(new FileInputStream("config.properties"));
		ConfigBean.version = property.getProperty("version");
		ConfigBean.save_file_path = property.getProperty("save_file_path");
		ConfigBean.log_file_format = property.getProperty("log_file_format");
		ConfigBean.warning_mobile = property.getProperty("warning_mobile");
		ConfigBean.warning_rate = property.getProperty("warning_rate");
		ConfigBean.warning_clear = property.getProperty("warning_clear");
		ConfigBean.warning = property.getProperty("warning");
		ConfigBean.write_logs = property.getProperty("write_logs");
		ConfigBean.execute_count = property.getProperty("execute_count");
		ConfigBean.groovy_file_path = property.getProperty("groovy_file_path");
		ConfigBean.close_browser_after_test = property.getProperty("close_browser_after_test");
		ConfigBean.operate_delay = property.getProperty("operate_delay");
		ConfigBean.remote_url = property.getProperty("remote_url");
		ConfigBean.multi_page_time = property.getProperty("multi_page_time");
		ConfigBean.remote_timeout = property.getProperty("remote_timeout");
		ConfigBean.log_file_path = property.getProperty("log_file_path");
		ConfigBean.boss_uap_inter = property.getProperty("boss_uap_inter");
		ConfigBean.warning_message_template = property.getProperty("warning_message_template");
		
		System.out.println("close_browser_after_test:"+ConfigBean.close_browser_after_test+"     multi_page_time:"+ConfigBean.multi_page_time);
	}

	/**
	 * 加载xml配置文件
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private static void initXML() throws Exception{
		SAXReader reader = new SAXReader(); 
		reader.setEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
		// 打包发布时需要改为 automation-config.xml
		// idea直接运行改为 src/main/resources/automation-config.xml
		Document document = reader.read(new FileInputStream("automation-config.xml"));
		document.setXMLEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
		Element conditionsEle = (Element) document.getRootElement().elements().get(2);
		Element projectsEle = (Element) document.getRootElement().elements().get(1);
		Element browsersEle = (Element) document.getRootElement().elements().get(0);
		Iterator<Element> itor = null;
		Iterator<Element> itorTemp = null;
		Iterator<Element> itorOper = null;
		List<Project> projects = new ArrayList<Project>();
		List<Browser> browsers = new ArrayList<Browser>();
		List<Function> functions = null;
		List<Operator> operators = null;
		Project project = null;
		Function function = null;
		Operator operator = null;
		Browser browser = null;
		Element tempEle = null;
		Element tempEleP = null;
		Element tempEleF = null;
		
		//写浏览器信息
		itor = browsersEle.elementIterator();
		while(itor.hasNext()){
			tempEle = itor.next();
			browser = new Browser();
			browser.setName(tempEle.getName());
			browser.setProp_name(tempEle.element("prop-name").getText());
			browser.setProp_value(tempEle.element("prop-value").getText());
			browsers.add(browser);
		}
		
		//写project信息
		itor = projectsEle.elementIterator();
		while(itor.hasNext()){
			//取project节点
			tempEleP = itor.next();
			if(tempEleP.getName().equals("project-ref")){
				SAXReader reader1 = new SAXReader(); 
				reader.setEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
				Document document1 = reader1.read(new FileInputStream(((Element)tempEleP.elements().get(0)).getText())); 
				document.setXMLEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
				tempEleP = document1.getRootElement();
			}
			project = new Project();
			functions = new ArrayList<Function>();
			project.setBrowser(((Element)tempEleP.elements().get(1)).getText());
			project.setName(tempEleP.attributeValue("name"));
			project.setCheck_flag(((Element)tempEleP.elements().get(0)).getText());
			project.setIndex_page(((Element)tempEleP.elements().get(3)).getText());
			project.setOperate_delay(tempEleP.attributeValue("operate-delay"));
			//function 遍历
			itorTemp = ((Element)tempEleP.elements().get(2)).elements().iterator();
			while(itorTemp.hasNext()){
				//取function节点
				tempEleF = itorTemp.next();
				function = new Function();
				function.setName(tempEleF.attributeValue("name"));
				function.setOperate_delay(tempEleF.attributeValue("operate-delay"));
				function.setError_to_page(tempEleF.attributeValue("error-to"));
				operators = new ArrayList<Operator>();
				itorOper = tempEleF.elementIterator();
				while(itorOper.hasNext()){
					tempEle = itorOper.next();
					operator = new Operator();
					operator.setName(tempEle.attributeValue("name"));
					operator.setFind(tempEle.attributeValue("find"));
					operator.setValue(tempEle.attributeValue("value"));
					operator.setFrame(tempEle.attributeValue("frame"));
					operator.setOper_text(tempEle.attributeValue("oper-text"));
					operator.setOper_type(tempEle.attributeValue("oper-type"));
					operator.setOperate_delay(tempEle.attributeValue("operate-delay"));
					operator.setSave(tempEle.attributeValue("save"));
					operator.setSave_text(tempEle.attributeValue("save-text"));
					operator.setInput_type(tempEle.attributeValue("input-type"));
					operator.setType(tempEle.getName());
					operator.setFind_text(tempEle.attributeValue("find-text"));
					operator.setPop_page(tempEle.attributeValue("pop-page"));
					operator.setCondition(tempEle.attributeValue("condition"));
					operators.add(operator);
				}
				function.setOperators(operators);
				functions.add(function);
			}
			project.setFunctions(functions);
			projects.add(project);
		}	
		
		//写condition信息
		Map<String,Condition> conditionMap = new HashMap<String, Condition>();
		Condition condition = null;
		String conditions_namespace = "";
		String map_name = "";
		itor = conditionsEle.elementIterator();
		while(itor.hasNext()){
			tempEle = itor.next();
			if(tempEle.getName().equals("conditions-ref")){
				SAXReader reader1 = new SAXReader(); 
				reader.setEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
				Document document1 = reader1.read(new FileInputStream(((Element)tempEle.elements().get(0)).getText())); 
				document.setXMLEncoding(Constants.C_PUB_PUBLIC_ENCODING_UTF8);
				tempEleP = document1.getRootElement();
				conditions_namespace = tempEleP.attributeValue("name");
				itorOper = tempEleP.elementIterator();
				while(itorOper.hasNext()){
					map_name = conditions_namespace+"."+tempEleF.attributeValue("name");
				    //condition名称重复
					if((condition =conditionMap.get(map_name)) != null ){
						throw new Exception("Condition \""+map_name+"\" is dupicated!");
					}
					condition = new Condition();
					//TODO -----------
					tempEleF = itorOper.next();
					//写信息
					conditionMap.put(map_name, condition);
				}
			}else{
				
			}
		}

		ConfigBean.browsers = browsers;
		ConfigBean.projects = projects;
		ConfigBean.conditionMap = conditionMap;
	}
	
	/**
	 * 告警初始化
	 */
	private static void initWarning(){
		ConfigBean.warningUsers = new ArrayList<WarningUserBean>();
		if(StringUtil.isBlank(ConfigBean.warning_mobile) || !"true".equals(ConfigBean.warning)){
			return;
		}
		WarningUserBean user = null;
		String mobiles[] = ConfigBean.warning_mobile.split(",");
		for(int i=0; i<mobiles.length; i++){
			user = new WarningUserBean();
			user.setCurrentLevel(Constants.C_AUTO_MATION_WARNING_LEVEL_FIRST);
			user.setMobile(mobiles[i]);
			ConfigBean.warningUsers.add(user);
		}
	}

}
