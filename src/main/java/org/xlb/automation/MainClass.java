package org.xlb.automation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.processor.Processor;

/**
 * 
 * 主程序入口
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class MainClass {

	public static void main(String[] args) {
		try {
			Init.init();
			for(long i=0; i<Long.parseLong(ConfigBean.execute_count); i++){
				Processor.process();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
