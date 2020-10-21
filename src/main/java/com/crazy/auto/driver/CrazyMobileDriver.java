package com.crazy.auto.driver;

import com.crazy.auto.exception.PlatformException;
import com.crazy.auto.utils.CrazyPath;
import com.crazy.auto.utils.Port;
import com.crazy.auto.utils.ProUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;



public class CrazyMobileDriver extends DriverBase {

	private static Logger log = Logger.getLogger(CrazyMobileDriver.class);

	/**
	 * 通过不同的platform来初始化不同的driver
	 * @param platform
	 * @param udid
	 * @param port
	 * @param chromedriverpath
	 */
	public CrazyMobileDriver(String platform, String udid, String port,String chromedriverpath) {
		super(platform);
		this.platform = platform;
		this.driver = this.getDriver(platform,udid, port,chromedriverpath);
		//this.swipe = new SwipeScreenOrElement(driver);
	}

	/**
	 *
	 * @param platform
	 * @param udid
	 * @param port
	 * @param chromedriverpath
	 * @return
	 */
	public AppiumDriver getDriver(String platform, String udid,String port,String chromedriverpath) {
		try {
			if (MobilePlatform.ANDROID.equalsIgnoreCase(platform)) {
				AppiumDriver driver = new AndroidDriver<AndroidElement>(
						new URL(pro.getKey("androidserver") + ":" + port + "/wd/hub"), getCaps(platform,udid,chromedriverpath));
				//http://127.0.0.1:4491/wd/hub
				return driver;
			} else if (MobilePlatform.IOS.equalsIgnoreCase(platform)) {
				AppiumDriver driver = new IOSDriver<IOSElement>(new URL(pro.getKey("iosserver") + ":" + port + "/wd/hub"),
						getCaps(platform,udid,chromedriverpath));
				//http://10.0.0.33:4491/wd/hub
				return driver;
			} else {
				log.error("platform is error, platform must is android or ios");
				throw new PlatformException("platform is error, platform must is android or ios");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * 通过指定paltform来获取不同平台的caps参数
	 * @param paltform
	 *            must is android or ios
	 * @return DesiredCapabilities
	 * @throws Exception
	 */
	public  DesiredCapabilities getCaps(String paltform, String udid,String chromedriverpath) {
		if (MobilePlatform.ANDROID.equalsIgnoreCase(paltform)) {
			ProUtil properties = new ProUtil(CrazyPath.androidCapsPath);
			HashMap<String, String> keyValues = properties.getAllKeyValue();
			DesiredCapabilities caps = new DesiredCapabilities(keyValues);
			caps.setCapability(MobileCapabilityType.UDID, udid);
			caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriverpath);
			caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Port.randomPort());
			return caps;
		} else if (MobilePlatform.IOS.equalsIgnoreCase(paltform)) {
			ProUtil properties = new ProUtil(CrazyPath.iosCapsPath);
			HashMap<String, String> keyValues = properties.getAllKeyValue();
			DesiredCapabilities caps = new DesiredCapabilities(keyValues);
			caps.setCapability(MobileCapabilityType.UDID, udid);
			caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Port.randomPort());
			return caps;
		} else {
			throw new PlatformException("paltform is error, platform must is android or ios");
		}
	}


	public static void main(String[] args) {
//		CrazyMobileDriver driver=new CrazyMobileDriver("android", "127.0.0.1:62001", "4723");
//		driver.implicitlyWaitDefault();
//		ElementBeans element=new ElementBeans("com.douban.frodo:id/left", 3, ElementBeans.ByType.id, "aaa");
//		//driver.findElement(element).click();;
//		ElementOperate action=new ElementOperate(driver);
//		action.longPress(element);
////		action.click(element);
////		action.swipe("up", 500);
////		action.swipe("up", 500);
//		driver.resetApp();
		
	}

}
