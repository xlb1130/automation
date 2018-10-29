package org.xlb.automation.pub.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xlb.automation.pub.bean.ConfigBean;

/**
 *  文本文件读取类
 * @author Lingbo Xie
 *
 */
public class RMIReaderText implements RMIReader {

	@Override
	public List<List<String>> read() {
		List<List<String>> retList = new ArrayList<List<String>>();
		List<String> rcolList = null;
		List<String> rowList = readTxtFile(ConfigBean.log_file_path);
		Iterator<String> rowItor = rowList.iterator();
		String rowStr = "";
		while (rowItor.hasNext()){
			rowStr = rowItor.next();
			rcolList = new ArrayList<String>();
			for (int i =0; i< rowStr.split("\\s").length; i++){
				rcolList.add(i,rowStr.split("\\s")[i]);
			}
			retList.add(rcolList);
		}	
		return retList;
	}

	/**
	 * 功能：Java读取txt文件的内容
	 * 
	 * @param filePath
	 */
	public List<String> readTxtFile(String filePath) {
		System.out.println("Start parse text file......");
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					list.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void writeLog() {
		// TODO Auto-generated method stub
		
	}
}
