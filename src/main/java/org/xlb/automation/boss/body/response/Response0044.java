package org.xlb.automation.boss.body.response;

import org.xlb.automation.boss.body.ResponseBody;
/**
 * 
 * 短信下发
 * @author Jack Xie
 * @author 2018-02-09
 * @version 1.0
 *
 */
public class Response0044 extends ResponseBody{
	private static final long serialVersionUID = 8736423906930669338L;
	//private static final Log log = LogFactory.getLog(Response0044.class);
	//结果 1为成功
	private String returnValue;
	
	//信息
	private String message;
		
	//private byte[] data;
	
	//private String mobile;
	
	public void format(String data) {
//		try {
//			String temp = new String(data);
//			String[] body = temp.split(";");
//			returnValue = body[0];
//			
//			//fix boss bug  2008-12-31
//			if(returnValue.indexOf("~")>-1){
//				String arr[] = body[0].split("~");
//				returnValue = arr[0];
//				if(arr.length>1) message = arr[1];
//				if(null==message) message = "";
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//
//		}
	}
	
	public String toString(){
		return getReturnValue();
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getXmlInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getRecordTempString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMobile(String mobile){
		//this.mobile = mobile;
	}
}
