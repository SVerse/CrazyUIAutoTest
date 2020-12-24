package com.crazy.auto.test;
/** 
* @version 创建时间：2020年2月20日 上午11:16:53
*/

import com.crazy.auto.driver.CrazyWebDriver;
import com.mtx.ui.auto.actions.LoginActions;
import com.mtx.ui.auto.actions.OrderActions;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class TestBase extends TestDriverBase {
	private static Logger log = Logger.getLogger(TestBase.class);
	//public DriverBase driver;
	public Assertion assertion;
	public LoginActions loginActions;
	public OrderActions orderActions;

	@Parameters({ "browserType" })
	@BeforeClass
	public void start(String browserType) {
		driver = new CrazyWebDriver(browserType);
		assertion=new Assertion(driver);
		loginActions=new LoginActions(driver);
		orderActions=new OrderActions(driver);
		driver.get("http://121.42.15.146:9090/mtx");
		log.info("========浏览器启动成功========");
	}

	@AfterClass
	public void tearDown() throws IOException {
		driver.quit();
		log.info("=========测试结束关闭浏览器======");
	}
}
