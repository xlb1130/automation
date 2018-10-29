package org.xlb.automation.pub.reader;

import java.util.List;

public interface RMIReader {
	/**
	 * 读取行
	 * @return
	 */
	public List<List<String>> read();
	
	/**
	 * 修改文件，增加返回码返回消息
	 */
	public void writeLog();
}
