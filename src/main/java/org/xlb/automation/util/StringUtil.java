package org.xlb.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	/**
	 * 首字母转小写
	 * @param s
	 * @return
	 */
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
     * 
     * 字符串判断空
     */
    public static boolean isBlank(String input){
    	boolean ret = false;
    	if(input == null || input.equals("")){
    		ret = true;
    	}
    	return ret;
    }

	public static String getTimeSerial() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sf.format(new Date());
	}
}
