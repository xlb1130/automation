package org.xlb.automation.util;

import java.util.Date;

public class DateUtil {
		public static long dateDiff(Date start, Date end, long parttern){	
		    long diff = end.getTime() - start.getTime();
			return  diff / parttern;
		}
}
