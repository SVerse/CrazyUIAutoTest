package com.mtx.ui.auto.test;

import com.crazy.auto.driver.CrazyWebDriver;
import com.crazy.auto.element.ElementOperate;
import com.crazy.auto.test.Assertion;
import com.crazy.auto.test.TestDriverBase;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 *
 */
public class MtxTestBase extends TestDriverBase {
	private static Logger log = Logger.getLogger(MtxTestBase.class);

	public Assertion assertion;//咱们封装的断言
	public ElementOperate operate;

	@Parameters({ "browserType" })
	@BeforeClass
	public void init(String browserType) {
		webdriver = new CrazyWebDriver(browserType);
		assertion = new Assertion(webdriver);
		operate = new ElementOperate(webdriver);
		webdriver.get("http://121.42.15.146:9090/mtx");
		log.info("浏览器网址已打开");
	}

	@AfterMethod
	public void backHome() {
		//每个用例执行完之后，返回首页
		webdriver.get("http://121.42.15.146:9090/mtx/");
	}

	@AfterClass
	public void quit() {
		//测试执行完成以后关闭浏览器
		webdriver.quit();
	}
}
