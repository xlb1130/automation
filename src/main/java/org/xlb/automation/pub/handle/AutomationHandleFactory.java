package org.xlb.automation.pub.handle;

/**
 * 
 * 操作处理工厂类
 * 
 * @author Lingbo Xie
 * @date 2018-08-23
 * @version V1.0
 *
 */
public class AutomationHandleFactory {

	// private static final Logger log =
	// LoggerFactory.getLogger(BodyFactory.class);

	private static final String REQUEST_BODY_CLASS = "org.xlb.automation.pub.handle.AutomationHandle#ins#";

	/**
	 * 防止实例化
	 */
	private AutomationHandleFactory() {

	}

	public static IAutomationHandle getHandleInstanceByTagName(String tagName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		
		IAutomationHandle handler = null;
		String clazz = REQUEST_BODY_CLASS.replace("#ins#", tagName);
		handler = (IAutomationHandle) Class.forName(clazz).newInstance();

		return handler;
	}
}
