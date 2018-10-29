package org.xlb.automation.pub.writer;

/**
 * 
 * 写日志工厂类
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class RMIWriterFactory {
	/**
	 * 私有构造函数，防止实例化
	 */
	private RMIWriterFactory(){
		
	}
	
	public static RMIWriter getInstance(String format){
		RMIWriter writer = null;
		try {
			Class<?> clazz = Class.forName("org.xlb.automation.pub.writer.RMIWriter"+format);
			writer = (RMIWriter) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return writer;
	}
}
