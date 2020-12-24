package com.crazy.auto.keyword;


import com.alibaba.fastjson.JSONObject;
import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementBeans;
import com.crazy.auto.element.ElementBeans.ByType;
import com.crazy.auto.element.ElementOperate;
import com.crazy.auto.test.Assertion;
import com.crazy.auto.utils.ImageUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class KeyWordsAction  {
	private static Logger log = Logger.getLogger(KeyWordsAction.class);

	public static boolean testResult; 
	public static Exception exception; 
    
	public DriverBase driver;
	public ElementOperate action;
	public Assertion assertion;
	Map<String,Object> param = new HashMap<String,Object>();

	public KeyWordsAction(DriverBase driver){
		this.driver = driver;
		this.action = new ElementOperate(driver);
		this.assertion = new Assertion(driver);
	}
	
	
	public static By getByLocatorKeyWord(String locatorKeyValue) {
		// 将配置对象中的定位类型存到 locatorType 变量，将定位表达式的值存入到 locatorValue 变量
		String type = locatorKeyValue.split(">")[0];// name
		String value = locatorKeyValue.split(">")[1];// 登录

		// 输出 locatorType 变量值和locatorValue 变量值，验证是否赋值正确
		//logger.info("Get element locator type by "+type+" with value : "+value );

		// 根据 locatorType 的变量值内容判断，返回何种定位方式的 By 对象
		if (type.equalsIgnoreCase("id"))
			return By.id(value); // By.id("com.zhihu.android:id/email_or_phone")
		else if (type.equalsIgnoreCase("xpath"))
			return By.xpath(value);
		else if ((type.toLowerCase().equalsIgnoreCase("classname")))
			return By.className(value);
		else if ((type.equalsIgnoreCase("linktext")))
			return By.linkText(value);
		else if (type.equalsIgnoreCase("partiallinktext"))
			return By.partialLinkText(value);
		else if ((type.equalsIgnoreCase("cssselector")))
			return By.cssSelector(value);
		else if ((type.equalsIgnoreCase("name")))
			return By.name(value);
		else if ((type.equalsIgnoreCase("tagname")))
			return By.tagName(value);
		else{
			throw new RuntimeException(" locator type not exist in this framework：" + type);
		}
	}

	//{"type":"id","timeout":"3","value":"name","elementName":"登录"}
	public ElementBeans getElement(String elementInfo) {
		JSONObject elementJson = JSONObject.parseObject(elementInfo);
		String type = elementJson.getString("type");
		String timeout = elementJson.getString("timeout");
		String value = elementJson.getString("value");
		String elementName = elementJson.getString("elementName");
		ElementBeans elementBeans = new ElementBeans(value, Integer.valueOf(timeout), getByType(type), elementName);
		return elementBeans;
	}
	
	/**
	 * @param type
	 */
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("linkText")) {
			byType = ByType.linkText;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
		} else if (type.equalsIgnoreCase("cssSelector")) {
			byType = ByType.cssSelector;
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			byType = ByType.partialLinkText;
		} else if (type.equalsIgnoreCase("tagName")) {
			byType = ByType.tagName;
		}else if (type.equalsIgnoreCase("accessibilityId")) {
			byType = ByType.accessibilityId;
		}else if (type.equalsIgnoreCase("iosnspredicatestring")) {
			byType = ByType.iosnspredicatestring;
		}else if (type.equalsIgnoreCase("uiautomator")) {
			byType = ByType.uiautomator;
		}
		return byType;
	}
	
	public void open(String url, String str) {
		try {
			driver.get(url);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("打开网站【" + url + "】失败", e);
			
		}
	}


	/**
	 * 此方法的名称对应 excel 文件中“关键字”列中的 input 关键字
	 * 读取 excel 文件中“操作值”列中的字符作为输入框的输入内容，
	 * 参数 locator 表示输入框的定位表达式。
	 */
	public  void sendkeys(String elementInfo, String value) {
		ElementBeans element = getElement(elementInfo);
		try {
			action.sendKeys(element, value);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			//e.printStackTrace();
			log.error("向元素【" + element.getElementName() + "】输入【" + value + "】失败", e);
		}
	}
	
	/**
	 * 此方法名称对应 excel 文件中“关键字”列中的 click 关键字，
	 * 实现点击操作，参数locator 代表被点击元素的定位表达式，
	 * 参数 String为无实际值传入的参数，仅为了通过反射机制统一地使用两个
	 * 函数参数来调用此函数。
	 */
	public  void click(String elementInfo, String value) {
		ElementBeans element = getElement(elementInfo);
		try {
			action.click(element);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("点击元素【" + element.getElementName() + "】失败", e);
		}
	}

	/**
	 * 此方法的名称对应 excel 文件中“关键字”列中的 sleep 关键字用于等待操作，
	 * 暂停几秒，函数参数是以毫秒为单位的等待时间。参数 sleepTime 表示暂停
	 * 的毫秒数，参数 String为无实际值传入的参数，仅为了通过反射机制统一地使用
	 * 两个函数参数来调用此函数。
	 */
	public  void sleep(String elementInfo, String value){
		try {
			driver.sleep(Integer.valueOf(value));
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
		}
	}

	/**
	 * 此方法的名称对应 excel 文件中“关键字”列中的 assertContainString 关键字，参数 assertString
	 * 为要断言的字符串内容，参数 string 为无实际值传入的参数，仅为了通过反射机制统一地使用两
	 * 个函数参数来调用此函数。
	 */
	public  void  assertContainString(String elementInfo, String value)  {
		assertion.assertContainsText(value, 10);
	}

	public  void  assertIsElementExist(String elementInfo, String value)  {
		ElementBeans element = getElement(elementInfo);
		try{ 
			boolean flag = action.isElementExist(element);
			assertion.assertTrue(flag);
		} catch (Exception|Error e) {
			KeyWordsAction.testResult = false;
			e.printStackTrace();
		}
	}

	public  void  assertIsElementExistTimeout(String elementInfo, String value)  {
		ElementBeans element = getElement(elementInfo);
		try{ 
			boolean flag = action.isElementExistTimeOut(element);
			Assert.assertTrue(flag);
		} catch (Exception|Error e) {
			KeyWordsAction.testResult = false;
			e.printStackTrace();
		}
	}

	/**
	 * 清除
	 * @param elementInfo
	 * @param value
	 */
	public void clear(String elementInfo, String value){
		ElementBeans element = getElement(elementInfo);
		try {
			action.clear(element);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("清除元素【" + element.getElementName() + "】失败", e);
		}
	}

	/**
	 * 获取元素的值，并保存到 param 对象中
	 * 将操作值字段的内容作为 param 的 key 保存
	 * @param elementInfo
	 * @param value
	 */
	public void getText(String elementInfo, String value){
		ElementBeans element = getElement(elementInfo);
		try {
			String text = action.getText(element);
			param.put(value, text);//将元素的text存入param对象，形成{"text1":"获取到的文本"}键值对
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
		}
	}

	/**
	 * 获取元素的属性值，并保存到 param 对象中
	 * 将操作值字段的内容作为 param 的 key 保存
	 * @param elementInfo
	 * @param value
	 */
	public void getAttribute(String elementInfo, String value){
		ElementBeans element = getElement(elementInfo);
		try {
			String attribute = action.getAttribute(element, value);
			param.put(value, attribute);//将元素的text存入param对象，形成{"valuexx":"attribute"}键值对
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
		}
	}

	/**
	 * 确认弹框
	 * 两个参数为占位，代码中没有使用，加参数是方便反射来调用
	 * @param locator
	 * @param value
	 */
	public void confirmAlert(String locator, String value) {
		try {
			driver.switchToAlert().accept();
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("确认弹框失败", e);
		}
	}

	/**
	 * 取消弹框
	 * @param locator
	 * @param value
	 */
	public void dismissAlert(String locator, String value) {
		try {
			driver.switchToAlert().dismiss();
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("取消弹框失败", e);
		}
	}

	/**
	 * 切换frame，通过元素切换
	 * @param elementInfo
	 * @param value
	 */
	public void swithFrame(String elementInfo, String value) {
		ElementBeans element = getElement(elementInfo);
		try {
			driver.switchToFrame(element);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("切换frame【" + element.getElementName() + "】失败", e);
		}
	}

	/**
	 * 切换window，通过索引切换
	 * @param elementInfo
	 * @param value
	 */
	public void swithWindow(String elementInfo, String value) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			driver.switchToWindow(windowHandles.toArray()[Integer.parseInt(value)].toString());
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("切换window到第【" + value + "】个失败", e);
		}
	}

	/**
	 * 判断两个字符串是否相等，只能被用来判断元素的某一个属性是否等于期望值
	 * @param actual
	 * @param expect
	 */
	public void assert_param(String actual, String expect){
		KeyWordsAction.testResult = param.get(actual).toString().equals(expect);
	}

	public void excuteJs(String elementInfo, String value) {
		try {
			driver.executeJS(value);
		} catch (Exception e) {
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("执行JS【" + value + "】失败", e);
		}
	}

	/**
	 * 下拉框随机选取值，只针对原生的select标签生效
	 * @param elementInfo
	 * @param value
	 */
	public void selectRandomOption(String elementInfo, String value) {
		ElementBeans element = getElement(elementInfo);
		try {
			action.selectRandomOption(element);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("执行随机选取下拉框【" + element.getElementName() + "】失败", e);
		}
	}

	/**
	 * 选择单选框
	 * @param elementInfo
	 * @param value
	 */
	public void selectRadio(String elementInfo, String value) {
		ElementBeans element = getElement(elementInfo);
		try {
			String selectRadio = action.selectRadio(element);
			param.put(value, selectRadio);//将元素的text存入param对象，形成{"valuexx":"attribute"}键值对
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("执行随机选取下拉框【" + element.getElementName() + "】失败", e);
		}
	}

	/**
	  * 滑动
	 * @param direction
	 * @param duration
	 */
	public void swipe(String direction, String duration) {
		try {
			action.swipe(direction, Integer.parseInt(duration));
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("向【" + direction + "】失败", e);
		}
	}

	/**
	 * 滑动
	 * @param elementInfo
	 * @param direction
	 */
	public void swipeOnElement(String elementInfo, String direction) {
		ElementBeans elementBeans = getElement(elementInfo);
		try {
			action.swipeOnElement(elementBeans, direction);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("向【" + direction + "】失败", e);
		}
	}

	/**
	 * 手势密码解锁
	 * @param elementInfo
	 * @param pwd
	 */
	public void wakeByGestures(String elementInfo, String pwd) {
		ElementBeans element = getElement(elementInfo);
		try {
			action.wakeByGestures(element, pwd);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("手势解锁失败", e);
		}
	}

	/**
	 * 图片对比的断言
	 * @param src
	 * @param dest
	 */
	public void imgCompareAssert(String src, String dest) {
		try {
			boolean compareImg = ImageUtil.compareImg(src, dest, 100f);
			assertion.assertTrue(compareImg);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("手势解锁失败", e);
		}
	}
	
	public void imgCompare(String imgs, String res) {
		try {
			boolean compareImg = ImageUtil.compareImg(imgs.split(">")[0], imgs.split(">")[1], 100f);
			//assertion.assertTrue(compareImg);
			param.put(res, compareImg);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("手势解锁失败", e);
		}
	}

	public void longPress(String elementInfo, String duration) {
		ElementBeans elementBeans=getElement(elementInfo);
		try {
			action.longPress(elementBeans, Integer.parseInt(duration));
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("手势解锁失败", e);
		}
	}

	public void longPressByCoordinate(String elementInfo, String coordinate) {
		//coordinate:  400=600
		int x = Integer.parseInt(coordinate.split("=")[0]);
		int y = Integer.parseInt(coordinate.split("=")[1]);
		try {
			action.longPress(x, y);
		} catch (Exception e) {
			// TODO: handle exception
			KeyWordsAction.testResult = false;
			KeyWordsAction.exception = e;
			e.printStackTrace();
			log.error("手势解锁失败", e);
		}
	}
}
