package org.xlb.automation.pub.writer;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.xlb.automation.pub.bean.ResultPubBean;

/**
 * 
 * 写日志类接口
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public interface RMIWriter {
	/**
	 * 修改文件，增加返回码返回消息
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public void writeLog(ResultPubBean bean) throws Exception;
	
	/**
	 * 
	 */
	public void flush();
	
	public void initSheet(String sheetName) throws Exception;
}
