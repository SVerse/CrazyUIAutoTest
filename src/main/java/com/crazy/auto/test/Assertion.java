package com.crazy.auto.test;

import com.crazy.auto.driver.DriverBase;
import io.appium.java_client.functions.ExpectedCondition;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * 自定义的断言类
 */
public class Assertion {
	private static Logger log = Logger.getLogger(Assertion.class);
	DriverBase driver;
	public Assertion(DriverBase driver) {
		this.driver=driver;
	}
	public void assertContainsText(String exceptText,int timeout) {
		log.info("断言页面中是否包含【"+exceptText+"】");
		try {
			Boolean flag = new WebDriverWait(driver.getDriver(), timeout).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver input) {
					// TODO Auto-generated method stub
					return input.getPageSource().contains(exceptText);
				}
			});
			Assert.assertTrue(flag,"页面中不包含【"+exceptText+"】");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("页面中不包含【"+exceptText+"】",e);
			Assert.fail("页面中不包含【"+exceptText+"】",e);
		}
	}
	
	public void assertAlertText(String exceptText,int timeout) {
		log.info("断言弹框上的提示语是否和【"+exceptText+"】一致");
		String text="";
		Alert alert=null;
		try {
			alert = new WebDriverWait(driver.getDriver(), timeout).until(ExpectedConditions.alertIsPresent());
			text=alert.getText();
			
			alert.accept();
			Assert.assertEquals(text, exceptText,"实际值【"+text+"】与期望值【"+exceptText+"】不一致");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("实际值【"+text+"】与期望值【"+exceptText+"】不一致",e);
			Assert.fail("实际值【"+text+"】与期望值【"+exceptText+"】不一致",e);
		}
	}
	public void assertText(String actual,String expect) {
		log.info("断言字符串实际值【"+actual+"】和期望值【"+expect+"】是否一致");
		Assert.assertEquals(actual, expect,"实际值【"+actual+"】与期望值【"+expect+"】不一致");
	}
	public void assertTitle(String expectTitle) {
		String title=driver.getTitle();
		log.info("断言页面title实际值【"+title+"】和期望值【"+expectTitle+"】是否一致");
		Assert.assertEquals(title, expectTitle,"实际值【"+title+"】与期望值【"+expectTitle+"】不一致");
	}
	public void assertURL(String expectURL) {
		String url=driver.getCurrentUrl();
		log.info("断言页面url实际值【"+url+"】和期望值【"+expectURL+"】是否一致");
		Assert.assertEquals(url, expectURL,"实际值【"+url+"】与期望值【"+expectURL+"】不一致");
	}
	public void assertTrue(boolean condition) {
		log.info("断言布尔值【"+condition+"是不是true");
		Assert.assertTrue(condition);
	}
	
	
}
