package org.xlb.automation.pub.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarningUserBean {
		/**
		 * 电话号码
		 */
		private String mobile;
		
		/**
		 * 上次短信发送时间
		 */
		private Date lastNotify;
		
		/**
		 * 错误通知级别
		 */
		private String currentLevel;
		
		/**
		 * 错误list
		 */
		private List<ResultPubBean> failedList = new ArrayList<ResultPubBean>();

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public Date getLastNotify() {
			return lastNotify;
		}

		public void setLastNotify(Date lastNotify) {
			this.lastNotify = lastNotify;
		}

		public String getCurrentLevel() {
			return currentLevel;
		}

		public void setCurrentLevel(String currentLevel) {
			this.currentLevel = currentLevel;
		}

		public List<ResultPubBean> getFailedList() {
			return failedList;
		}

		public void setFailedList(List<ResultPubBean> failedList) {
			this.failedList = failedList;
		}
		
}
