package org.xlb.automation.pub.processor;

import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.Project;
import org.xlb.automation.pub.writer.RMIWriter;
import org.xlb.automation.pub.writer.RMIWriterFactory;

/**
 * 
 * 处理类入口
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class Processor {
	public static void process(){
		Project project = null;
		String errorMsg = "";
		RMIWriter log = null;
		if("true".equals(ConfigBean.write_logs)){
			log = RMIWriterFactory.getInstance(ConfigBean.log_file_format);
		}	
		
		for(int i=0 ;  i< ConfigBean.projects.size(); i++){
			project = ConfigBean.projects.get(i);
			try{
				ProjectProcessor.process(project,log);
			}catch(Exception e){
				errorMsg = "Exception occured in project \""+project.getName() + "\":" + e.getMessage();
				System.out.println("#######################################");
				System.out.println(errorMsg);
				System.out.println("#######################################");
				//e.printStackTrace();
			}
		}
		if("true".equals(ConfigBean.write_logs)){
			log.flush();
		}	
	}
}
