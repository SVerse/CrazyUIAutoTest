package com.mtx.ui.auto.test;

import com.crazy.auto.excel.ExcelDataUtil;
import com.mtx.ui.auto.actions.LoginActions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 码同学商城登录测试用例
 */
public class MtxLoginTest extends MtxTestBase{
	
	@DataProvider
	public Object[][] getLoginData() throws Exception {
		String datafile = "src/main/resources/data/mtx_data.xlsx";
		ExcelDataUtil excel = new ExcelDataUtil(datafile);
		Object[][] testData = excel.getTestData("登录数据");
		excel.close();
		return testData;
	}

	/**
	 * 使用数据驱动方式执行登录测试用例，excel中每行代表一条用例
	 * @param caseDesc
	 * @param username
	 * @param pwd
	 * @param assertValue
	 * @throws Exception
	 */
	@Test(dataProvider = "getLoginData")
	public void test001_login(String caseDesc, String username, String pwd, String assertValue) throws Exception {
		LoginActions loginActions = new LoginActions(webdriver);
		loginActions.login(username, pwd);
		assertion.assertContainsText(assertValue, 10);
	}

	/**
	 * 普通方式执行测试登录用例
	 * @throws Exception
	 */
	@Test
	public void test002_login() throws Exception {
		webdriver.deleteCookies();
		webdriver.refresh();

		LoginActions loginActions = new LoginActions(webdriver);
		loginActions.login("shamotest1", "123456");
		assertion.assertContainsText("登录成功", 5);
	}

}
