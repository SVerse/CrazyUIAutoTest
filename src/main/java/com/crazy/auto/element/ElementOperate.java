package com.crazy.auto.element;

import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.driver.SwipeScreenOrElement;
import com.crazy.auto.utils.RandomUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 创建时间：2020年2月15日 下午7:01:32
 *
 */
public class ElementOperate {
	private static Logger log = Logger.getLogger(ElementOperate.class);
	DriverBase driver;
	Actions action;// web端手势操作
	TouchAction touch;// 移动端手势操作
	public SwipeScreenOrElement swipe;//移动端滑动
	public ElementOperate(DriverBase driver) {
		this.driver=driver;
		this.action = new Actions(driver.getDriver());
		if (driver.getDriver() instanceof AppiumDriver) {
			this.touch = new TouchAction((AppiumDriver) driver.getDriver());
			this.swipe=new SwipeScreenOrElement(driver);
		}
		
	}

	public void sendKeys(ElementBeans elementBeans, String str) {
		driver.findElement(elementBeans).sendKeys(str);
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	public void sendKeysUtilCorrect(ElementBeans elementBeans, String str) {
		driver.findElement(elementBeans).sendKeys(str);
		String attribute = this.getAttribute(elementBeans, "value");
		while (!attribute.equals(str)) {
			log.info("向【" + elementBeans.getElementName() + "】输入【" + attribute + "】成功");
			this.clear(elementBeans);
			driver.findElement(elementBeans).sendKeys(str);
			attribute = this.getAttribute(elementBeans, "value");
		}
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	public void sendKeysClear(ElementBeans elementBeans, String str) {
		this.clear(elementBeans);
		driver.findElement(elementBeans).sendKeys(str);
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	/**
	 * 该方法用于输入和原来输入框内容不一样的值
	 * @param elementBeans
	 * @param length
	 * @return
	 */
	public String sendKeysDiff(ElementBeans elementBeans, int length) {
		String currentName = this.getAttribute(elementBeans, "value");
		String newName = RandomUtil.getRndStrZhByLen(length);
		while (currentName.equals(newName)) {
			newName = RandomUtil.getRndStrZhByLen(length);
		}
		this.clear(elementBeans);
		this.sendKeys(elementBeans, newName);
		return newName;
	}

	public void click(ElementBeans elementBeans) {
		driver.findElement(elementBeans).click();
		log.info("点击【" + elementBeans.getElementName() + "】成功");
	}

	public void clickToBeClickable(ElementBeans elementBeans) {
		WebElement element = new WebDriverWait(driver.getDriver(), elementBeans.getTimout())
				.until(ExpectedConditions.elementToBeClickable(elementBeans.getBy()));
		element.click();
		log.info("在" + elementBeans.getTimout() + "秒内点击【" + elementBeans.getElementName() + "】成功");
	}

	public String getAttribute(ElementBeans elementBeans, String name) {
		String attributeValue = driver.findElement(elementBeans).getAttribute(name);
		log.info("获取【" + elementBeans.getElementName() + "】的属性【" + name + "】结果是：" + attributeValue);
		return attributeValue;
	}

	public void clear(ElementBeans elementBeans) {
		driver.findElement(elementBeans).clear();
		log.info("清除【" + elementBeans.getElementName() + "】成功");
	}

	/**
	 * 针对某一个元素截图
	 * @param elementBeans
	 * @param filename
	 */
	public void getScreenshotAs(ElementBeans elementBeans, String filename) {
		File file = driver.findElement(elementBeans).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(filename));
			log.info("针对元素【" + elementBeans.getElementName() + "】截图成功，图片是：" + filename);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("针对元素【" + elementBeans.getElementName() + "】截图失败", e);
		}
	}

	public String getText(ElementBeans elementBeans) {
		// TODO Auto-generated method stub
		String text = driver.findElement(elementBeans).getText();
		log.info("获取【" + elementBeans.getElementName() + "】的文本是：" + text);
		return text;
	}

	public void moveToElement(ElementBeans elementBeans) {
		WebElement element = driver.findElement(elementBeans);
		action.moveToElement(element).perform();
		log.info("鼠标悬浮到元素【" + elementBeans.getElementName() + "】完成");
	}

	public void clickTap() {
		action.sendKeys(Keys.TAB).perform();
		log.info("Tap键点击完成");
	}

	public void clickEnter() {
		action.sendKeys(Keys.ENTER).perform();
		log.info("Enter键点击完成");
	}

	/**
	 * web端上传文件操作，调用该方法前请先加几秒等待，等待文件管理器打开，否则会失败，适用windows
	 * @param file
	 * @throws Exception
	 */
	public void uploadFile(String file) throws Exception {
		// String file="C:\\Users\\LXG\\Desktop\\115.png";
		// 基于文件路径构建StringSelection对象
		StringSelection stringSelection = new StringSelection(file);
		// 通过TookKit将文件路径黏贴到剪贴板
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * 该方法用于随机选择下拉框里和当前选中的值不一样的值
	 * @param elementBeans
	 * @return
	 */
	public String selectRandomOption(ElementBeans elementBeans) {
		Select select = new Select(driver.findElement(elementBeans));
		String curName="";
		try {
			curName = select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(curName);
		log.info("下拉框【" + elementBeans.getElementName() + "】当前值是：" + curName);
		List<WebElement> allOptions = select.getOptions();
		int index = RandomUtil.randomInt(1, allOptions.size() - 1);
		// System.out.println(index+"index");
		select.selectByIndex(index);
		String newName = select.getFirstSelectedOption().getText();
		while (curName.equals(newName)) {
			index = RandomUtil.randomInt(1, allOptions.size() - 1);
			select.selectByIndex(index);
			newName = select.getFirstSelectedOption().getText();
		}
		log.info("下拉框【" + elementBeans.getElementName() + "】选择的新值是：" + newName);
		return newName;
	}

	public String getFirstSelected(ElementBeans elementBeans) {
		Select select = new Select(driver.findElement(elementBeans));
		String curName = select.getFirstSelectedOption().getText();
		log.info("获取下拉框【" + elementBeans.getElementName() + "】当前值是：" + curName);
		return curName;
	}

	public void selectOption(ElementBeans elementBeans, Object o) {
		Select select = new Select(driver.findElement(elementBeans));
		if (o instanceof Integer) {
			select.selectByIndex(((Integer) o).intValue());
			log.info("选择下拉框【" + elementBeans.getElementName() + "】索引：" + o.toString());

		} else if (o instanceof String) {
			try {
				select.selectByValue(o.toString());
				log.info("通过value值选择下拉框【" + elementBeans.getElementName() + "】值为：" + o.toString());
			} catch (Exception e) {
				// TODO: handle exception
				select.selectByVisibleText(o.toString());
				log.info("通过文本选择下拉框【" + elementBeans.getElementName() + "】文本为：" + o.toString());
			}
		}
	}

	public String selectRadio(ElementBeans elementBeans) {
		String radioValue = "";
		List<WebElement> radios = driver.findElements(elementBeans);
		for (WebElement we : radios) {
			if (!we.isSelected()) {
				we.click();
				radioValue = we.getAttribute("value");
				break;
			}
		}
		return radioValue;
	}

	/**
	 * 判断元素是否存在
	 * @param elementBeans
	 * @return boolean
	 */
	public boolean isElementExist(ElementBeans elementBeans) {
		driver.implicitlyWaitZero();
		try {
			driver.findElement(elementBeans).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("元素【" + elementBeans.getElementName() + "】不存在");
			return false;
		} finally {
			driver.implicitlyWaitDefault();
		}
	}

	/**
	 * 判断元素是否存在
	 * @param elementBeans
	 * @return boolean
	 */
	public boolean isElementExistTimeOut(ElementBeans elementBeans) {
		driver.implicitlyWaitZero();
		try {
			driver.findElementTimeOut(elementBeans).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("元素【" + elementBeans.getElementName() + "】在"+elementBeans.getTimout()+"秒内未找到");
			return false;
		} finally {
			driver.implicitlyWaitDefault();
		}
	}

	/**
	 * 获取元素的结束点坐标对象
	 * @param elementBeans
	 * @return
	 */
	public Point getElementEndCor(ElementBeans elementBeans){
		WebElement element = driver.findElement(elementBeans);
		Point start=element.getLocation();
		int startX=start.getX();//起始点x
		int startY=start.getY();//起始点y
		Dimension size=element.getSize();
		int width=size.getWidth();//元素的宽
		int height=size.getHeight();//元素的高
		int endX=startX+width;
		int endY=startY+height;
		log.info("元素【" + elementBeans.getElementName() + "】的结束点坐标是："+endX+","+endY);
		Point end=new Point(endX, endY);
		return end;
	}
	
	/**
	 * 滑动屏幕方法，通过参数实现各方向滑动
	 * @param direction 方向参数，值为"up"、"down"、"right"、"left"
	 * @param duration 滑动时间，单位毫秒
	 */
	public void swipe(String direction, int duration) {
		swipe.swipe(direction, duration);
	}
	public void swipe(int startx,int starty,int endx,int endy, int duration) {
		driver.sleep(1);
		Duration dur=Duration.ofMillis(duration);
		swipe.swipe(startx, starty, endx, endy, dur);
	}

	/**
	 * 在元素上滑动
	 * @param elementBeans 元素对象
	 * @param derction 方向参数，值为"up"、"down"、"right"、"left"
	 */
	public void swipeOnElement(ElementBeans elementBeans, String derction) {
		swipe.swipeOnElement(elementBeans, derction);
	}

	/**
	 * touchaction的方式点击元素
	 * @param elementBeans
	 */
	public void tap(ElementBeans elementBeans) {
		WebElement element = driver.findElement(elementBeans);
		try {
			touch.tap(new TapOptions().withElement(ElementOption.element(element))).release().perform();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("tap error ->" + e.getMessage());
			throw new RuntimeException("tap error ->" + e.getMessage());
		}
	}
	
	/**
	 * 点击某个坐标点
	 * @param x
	 * @param y
	 */
	public void clickByCoordinate(int x, int y) {
		try {
			driver.sleep(1);
			touch.tap(new TapOptions().withPosition(new PointOption<>().withCoordinates(x, y))).release().perform();
			log.info("clickByCoordinate Successed on " + x + "," + y);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("clickByCoordinate error ->" + e.getMessage());
			throw new RuntimeException("clickByCoordinate error ->" + e.getMessage());
		}
	}

	/**
	 * 元素长按
	 * @param elementBeans
	 */
	public void longPress(ElementBeans elementBeans) {
		WebElement element = driver.findElement(elementBeans);
		try {
			touch.longPress(new LongPressOptions().withElement(ElementOption.element(element))).release().perform();
			log.info("longPress Successed ");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("longPress error ->" + e.getMessage());
			throw new RuntimeException("longPress error ->" + e.getMessage());
		}
	}

	/**
	 * 长按某元素，指定长按时间
	 * @param elementBeans
	 * @param duration
	 */
	public void longPress(ElementBeans elementBeans, int duration) {
		WebElement element = driver.findElement(elementBeans);
		try {
			Duration dur=Duration.ofMillis(duration);
			touch.longPress(new LongPressOptions().withElement(ElementOption.element(element)).withDuration(dur)).release().perform();
			log.info("longPress Successed ");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("longPress error ->" + e.getMessage());
			throw new RuntimeException("longPress error ->" + e.getMessage());
		}
	}
	
	/**
	 * 在某一个坐标点上长按
	 * @param x
	 * @param y
	 */
	public void longPress(int x, int y) {
		try {
			touch.longPress(new LongPressOptions().withPosition(new PointOption<>().point(x, y))).release().perform();
			log.info("longPress Successed on " + x + "," + y);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("longPress error ->" + e.getMessage());
			throw new RuntimeException("longPress error ->" + e.getMessage());
		}
	}

	/**
	 * 长按坐标点，指定长按时间
	 * @param x
	 * @param y
	 * @param duration
	 */
	public void longPress(int x, int y, int duration) {
		try {
			Duration dur=Duration.ofMillis(duration);
			touch.longPress(new LongPressOptions().withPosition(new PointOption<>().point(x, y)).withDuration(dur)).release().perform();
			log.info("longPress Successed on " + x + "," + y);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("longPress error ->" + e.getMessage());
			throw new RuntimeException("longPress error ->" + e.getMessage());
		}
	}
	
	/**
	 * 某方向滑动直到边界，如底部，顶部(在没有边界标识的时候使用)
	 */
	public void swipeUntilBoundary(String direction) {
		boolean flag = false;
		while (!flag) {
			String pageSource = driver.getPageSource();//滑动前获取界面资源
			this.swipe(direction, 500);
			driver.sleep(1);
			String pageSource1 = driver.getPageSource();//滑动后获取界面资源
			flag = pageSource.equals(pageSource1);//滑动前后界面资源一致则表明无法再滑动
		}
	}
	
	/**
	 * 某方向滑动直到边界，如底部，顶部(在没有边界标识的时候使用)
	 */
	public void swipeUntilBoundary(String direction, ElementBeans elementBeans) {
		boolean flag = false;
		while (!flag) {
//			String pageSource = driver.getPageSource();//滑动前获取界面资源
			List<WebElement> titles = driver.findElements(elementBeans);
			List<String> textStrings=new ArrayList<String>();
			for (WebElement ele : titles) {
				textStrings.add(ele.getText());
			}
			this.swipe(direction, 500);
			driver.sleep(1);
//			String pageSource1 = driver.getPageSource();//滑动后获取界面资源
			List<WebElement> titles1 = driver.findElements(elementBeans);
			List<String> textStrings1=new ArrayList<String>();
			for (WebElement ele : titles1) {
				textStrings1.add(ele.getText());
			}
			flag=textStrings.equals(textStrings1);
			//flag = pageSource.equals(pageSource1);//滑动前后界面资源一致则表明无法再滑动
		}
	}
	
	/**
	 * 设备返回键操作，仅支持android
	 */
	public void pressBack() {
		if (MobilePlatform.ANDROID.equalsIgnoreCase(driver.platform)) {
			driver.sleep(1);
			((AndroidDriver) driver.getDriver()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
		} else {
			log.error("platform is " + driver.platform + ",so not support pressBack method");
			throw new RuntimeException("platform is " + driver.platform + ",so not support pressBack method");
		}
	}

	/**
	 * 清除文本框的内容，一个一个清除，仅支持android
	 * @param elementBeans
	 */
	public void clearOneByOne(ElementBeans elementBeans){
		
		if (MobilePlatform.ANDROID.equalsIgnoreCase(driver.platform)) {
			WebElement element = driver.findElement(elementBeans);
			element.click();
			int length=element.getText().length();
			((AndroidDriver) driver.getDriver()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.MOVE_END));
			for (int i = 0; i <length; i++) {
				((AndroidDriver) driver.getDriver()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.DEL));
			}
		} else {
			log.error("platform is " + driver.platform + ",so not support pressBack method");
			throw new RuntimeException("platform is " + driver.platform + ",so not support pressBackspace method");
		}
	}
	
	/**
	 * 唤醒屏幕，仅支持android
	 */
	public void wakeUp() {
		boolean flag;
		if (MobilePlatform.ANDROID.equalsIgnoreCase(driver.platform)) {
			flag = ((AndroidDriver)driver.getDriver()).isDeviceLocked();
			if (flag) {
				((AndroidDriver)driver.getDriver()).unlockDevice();
				log.info("unlockDevice complete");
			}
		} else {
			log.error("platform is " + driver.platform + ",so not support pressBack method");
			throw new RuntimeException("platform is " + driver.platform + ",so not support wakeUp method");
		}
	}

	/**
	 * 逐个输入数字，模拟的是键盘数字输入，15610112934，仅支持android
	 * @param text
	 */
	public void sendKeysOneByOne(String text) {
		if (MobilePlatform.ANDROID.equalsIgnoreCase(driver.platform)) {
			char[] chr = text.toCharArray();// {1,5,6,1,x,x,x,x}
			for (int i = 0; i < chr.length; i++) {
				int c = Integer.valueOf(String.valueOf(chr[i]));
				AndroidKey number;
				switch (c) {
				case 0:
					// driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
					number = AndroidKey.DIGIT_0;
					break;
				case 1:
					number = AndroidKey.DIGIT_1;
					break;
				case 2:
					number = AndroidKey.DIGIT_2;
					break;
				case 3:
					number = AndroidKey.DIGIT_3;
					break;
				case 4:
					number = AndroidKey.DIGIT_4;
					break;
				case 5:
					number = AndroidKey.DIGIT_5;
					break;
				case 6:
					number = AndroidKey.DIGIT_6;
					break;
				case 7:
					number = AndroidKey.DIGIT_7;
					break;
				case 8:
					number = AndroidKey.DIGIT_8;
					break;
				case 9:
					number = AndroidKey.DIGIT_9;
					break;
				default:
					throw new RuntimeException("mobile number param is error-->" + c);
				}
				((AndroidDriver)driver.getDriver()).pressKey(new io.appium.java_client.android.nativekey.KeyEvent(number));// driver.pressKeyCode(16)=9;
			}
		} else {
			log.error("platform is " + driver.platform + ",so not support pressBack method");
			throw new RuntimeException("platform is " + driver.platform + ",so not support wakeUp method");
		}

	}

	/**
	 * 针对有一些元素无法精确定位，但是能够定位到整体大框元素，而这个大框拆分成小框又是规律的，比如3*3,2*4,4*3等等
	 * 获取整体元素，将整体元素分为多行多列元素，取每一个子元素的中心坐标存入集合对象{1,2,3,4,5,6,7,8,9, ,0,x}
	 * @param elementBeans
	 * @param rows
	 * @param columns
	 * @return
	 */
	public List<Point> getElementByCoordinates(ElementBeans elementBeans, int rows, int columns) {
		// int[] coordinate=new int[2];
		WebElement element = driver.findElement(elementBeans);
		List<Point> elementResolve = new ArrayList<Point>();
		if (element != null) {
			int startx = element.getLocation().getX();// 起始点坐标x
			int starty = element.getLocation().getY();// 起始点坐标y
			int offsetx = element.getSize().getWidth();// 该元素的宽
			int offsety = element.getSize().getHeight();// 该元素的高
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					int x = startx + (offsetx / 2 * columns) * (2 * j + 1);
					int y = starty + (offsety / (2 * rows) * (2 * i + 1));
					Point p = new Point(x, y);
					elementResolve.add(p);
				}
			}
		}
		return elementResolve;
	}

	/**
	 * 手势密码九宫格分成9个数字1-9,1258
	 * @param elementBeans
	 * @param pwd
	 */
	public void wakeByGestures(ElementBeans elementBeans, String pwd) {
		WebElement element = driver.findElement(elementBeans);
		int x=element.getLocation().getX();
		int y=element.getLocation().getY();
		int w=element.getSize().getWidth();
		int h=element.getSize().getHeight();
		
		List<PointOption> locks=new ArrayList<PointOption>();
		
		for(int i=0;i<3;i++) {//行
			for(int j=0;j<3;j++) {//列
				int cur_x=x+(2*j+1)*w/6;
				int cur_y=y+(2*i+1)*h/6;
				locks.add(PointOption.point(cur_x,cur_y));
			}
		}
		
		char[] charArray = pwd.toCharArray();//5678
		
		touch.press(locks.get(Integer.parseInt(String.valueOf(charArray[0]))-1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)));
		
		for(int i=1;i<charArray.length;i++) {
			touch.moveTo(locks.get(Integer.parseInt(String.valueOf(charArray[i]))-1)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)));
		}
		touch.release().perform();
	}
	
	/**
	 * 密码从0到9，但是0的坐标点排布在第11个位置，所以索引是10,这是针对安全密码数字键盘的操作
	 * @param pwd
	 * @param password
	 */
	public void sendkeysPwd(List<Point> pwd, int[] password) {
		if (password.length > 0) {
			for (int i = 0; i < password.length; i++) {
				if (password[i] == 0) {
					this.clickByCoordinate(pwd.get(10).x, pwd.get(10).y);
				} else {
					this.clickByCoordinate(pwd.get(password[i] - 1).x, pwd.get(password[i] - 1).y);
				}
			}
		} else {
			log.error("param 'password' is empty");
			throw new RuntimeException("param 'password' is empty");
		}
	}
	
	/**
	 * 安全键盘密码锁时使用，按照规律拆分后的坐标进行点击输入
	 * @param elementBeans
	 * @param rows
	 * @param columns
	 * @param password
	 */
	public void sendkeysPwd(ElementBeans elementBeans, int rows, int columns, int[] password) {
		if (elementBeans != null) {
			List<Point> pwd = getElementByCoordinates(elementBeans, rows, columns);
			sendkeysPwd(pwd, password);
		} else {
			log.error("element is null");
			throw new RuntimeException("element is null");
		}

	}

	/**
	 * 隐藏键盘，仅支持ios
	 */
	public void hideKeyboard() {
		if(MobilePlatform.IOS.equalsIgnoreCase(driver.platform)) {
			((IOSDriver)driver.getDriver()).hideKeyboard();
		}else {
			log.error("platform is " + driver.platform + ",so not support hideKeyboard");
			throw new RuntimeException("platform is " + driver.platform + ",so not support hideKeyboard");
		}
	}

	/**
	 * 这是重新启动ios app
	 * @param bundleId
	 */
	public void activateApp(String bundleId) {
		if(MobilePlatform.IOS.equalsIgnoreCase(driver.platform)) {
			((IOSDriver)driver.getDriver()).activateApp(bundleId);
		}else {
			log.error("platform is " + driver.platform + ",so not support activateApp");
			throw new RuntimeException("platform is " + driver.platform + ",so not support activateApp");
		}
	}

	/**
	 * 关闭app
	 */
	public void closeApp() {
		((AppiumDriver)driver.getDriver()).closeApp();
	}

	/**
	 * 重新运行app
	 */
	public void launchApp() {
		((AppiumDriver)driver.getDriver()).launchApp();
	}

}
