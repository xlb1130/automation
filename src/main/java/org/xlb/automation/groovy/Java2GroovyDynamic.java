package org.xlb.automation.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

import org.xlb.automation.pub.bean.ConfigBean;

public class Java2GroovyDynamic {

	public static String getInputFromGroovy(String file) throws Exception {

		ClassLoader parent = ClassLoader.getSystemClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class<?> groovyClass = loader
				.parseClass(new File(ConfigBean.groovy_file_path+file));
		System.out.println(System.getProperty("user.dir"));
		GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
		Object[] param = {  };
		String retStr =  (String) groovyObject.invokeMethod("getInputString", param);
		return retStr;
	}

}