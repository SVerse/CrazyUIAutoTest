package com.crazy.auto.driver;

import com.crazy.auto.element.ElementBeans;
import com.crazy.auto.element.ElementOperate;
import com.crazy.auto.utils.Command;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/** 

* @version 创建时间：2020年2月15日 下午4:26:46 

*/
public class CrazyWebDriver extends DriverBase {
	private static Logger log = Logger.getLogger(CrazyWebDriver.class);

	/**
	 * 通过platform参数决定创建哪个浏览器的driver对象
	 * @param platform 浏览器名称
	 */
	public CrazyWebDriver(String platform) {
		super(platform);
		switch (platform.toLowerCase()) {
	      case "chrome":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	    	  }else if(Command.isMac()){
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac");
	    	  }else {
	    		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux");
	    	  }
		      this.driver = new ChromeDriver();
		      break;
	      case "ff":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
	    	  }else if(Command.isMac()){
	    		  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_mac");
	    	  }else {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_linux");
	    	  }
	          this.driver = new FirefoxDriver();
	          break;
	      case "ie":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/IEDriverServer.exe");
	    	  }else {
	    		  log.error("ie浏览器仅仅支持windows系统");
	    		  throw new RuntimeException("ie浏览器仅仅支持windows系统");
	    	  }
	         this.driver = new InternetExplorerDriver();
	         break;
	      case "edge":
	    	  if(Command.isWin()) {
	    		  System.setProperty("webdriver.gecko.driver", "drivers/MicrosoftWebDriver.exe");
	    	  }else {
	    		  log.error("edge浏览器仅仅支持windows系统");
	    		  throw new RuntimeException("edge浏览器仅仅支持windows系统");
	    	  }
	          this.driver = new EdgeDriver();
	          break;
	      case "safari":
	    	  if(Command.isMac()) {
	    		  this.driver = new SafariDriver(); 
	    	  }else {
	    		  log.error("safari浏览器仅仅支持mac系统");
	    		  throw new RuntimeException("safari浏览器仅仅支持mac系统");
	    	  }
	          
	          break;
	      default:
	        this.driver = new ChromeDriver();
	        break;
	    }
	    driver.manage().window().maximize();
	    implicitlyWaitDefault();//设置默认的隐式等待，在global.properties文件中进行的配置
	}
	

	public static void main(String[] args) throws Exception {
		CrazyWebDriver cdriver=new CrazyWebDriver("chrome");
//		MallActionsForXml mall=new MallActionsForXml(cdriver);
//		mall.login("shamotest1", "123456");
		cdriver.driver.get("http://ask.testfan.cn");
//		a.driver.findElement(By.id("xxx"));
		ElementBeans elementBeans=new ElementBeans("登录", 2, ElementBeans.ByType.linkText, "登录链接");
		ElementOperate action=new ElementOperate(cdriver);
		action.clickToBeClickable(elementBeans);
	}

}
