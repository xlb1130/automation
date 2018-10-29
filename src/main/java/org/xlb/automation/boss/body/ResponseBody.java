package org.xlb.automation.boss.body;

/**
 *
 * @author Jack Xie
 * @since 2018-02-09
 * @version V1.0
 * 
 */
public abstract class  ResponseBody implements Body{
	private static final long serialVersionUID = -5325129092941820455L;
	private String retCode;
	private String retMsg;
	public abstract void setMobile(String mobile);
	public abstract void format(String data) throws Exception;
	public abstract String toString();
	public abstract String getRecordTempString();
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
