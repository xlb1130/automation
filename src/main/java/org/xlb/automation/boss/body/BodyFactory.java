package org.xlb.automation.boss.body;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Lingbo Xie
 * @since 2018-02-09
 * @version V1.0
 * 
 */
public class BodyFactory {
	
	private static final Logger log = LoggerFactory.getLogger(BodyFactory.class);
	
	private static final String REQUEST_BODY_CLASS = "org.xlb.automation.boss.body.request.Request#ins#";

	private static final String RESPONSE_BODY_CLASS = "org.xlb.automation.boss.body.response.Response#ins#";

	/**
	 * 通过反射执行并获取返回结果
	 * @param cmdid
	 * @return
	 */
	public static ResponseBody getResponseBody(String cmdid){
		ResponseBody body = null;
		cmdid = cmdid.trim();
			try {
				String clazz = RESPONSE_BODY_CLASS.replace("#ins#", cmdid);
				body =
					(ResponseBody) Class
						.forName(clazz)
						.newInstance();
				log.debug("Boss Response Instance:{} created!",clazz);
			} catch (InstantiationException e) {
				log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				log.error(e.getMessage());
			} catch (ClassNotFoundException e) {
				log.error(e.getMessage());
			}


		return body;
	}
	
	public static RequestBody getRequestBody(String cmdid){
		RequestBody body = null;
		cmdid = cmdid.trim();
		try {
			String clazz = REQUEST_BODY_CLASS.replace("#ins#", cmdid);
			body =
					(RequestBody) Class
						.forName(clazz)
						.newInstance();
			log.debug("Boss Request Instance:{} created!",clazz);
		} catch (InstantiationException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		return body;
	}

}
