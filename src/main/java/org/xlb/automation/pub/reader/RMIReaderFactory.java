package org.xlb.automation.pub.reader;

public class RMIReaderFactory {
	/**
	 * 私有构造函数，防止实例化
	 */
	private RMIReaderFactory(){
		
	}
	
	public static RMIReader getInstance(String format){
		RMIReader reader = null;
		try {
			Class<?> clazz = Class.forName("org.xlb.rmi.reader.RMIReader"+format);
			reader = (RMIReader) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return reader;
	}
}
