package com.mtx.ui.auto.test;

import com.mtx.ui.auto.test.MtxTestBase0510;
import com.mtx.ui.auto.actions.HeaderUploadActions;
import com.mtx.ui.auto.actions.LoginActions;
import com.mtx.ui.auto.actions.OrderActions;
import org.testng.annotations.Test;

/**
 * mtx商城测试
 */
public class MtxTests extends MtxTestBase0510 {
	
	
	//在测试类中调用业务层的代码
	@Test(description = "mtx商城登录用户名错误")
	public void test001_login() throws Exception {
		LoginActions loginActions = new LoginActions(webdriver);
		loginActions.login("shamotest","123456");
		assertion.assertContainsText("帐号不存在", 10);//断言页面是否包含登录成功在19秒内
	}
	
	@Test(description = "mtx商城登录密码错误")
	public void test002_login() throws Exception {
		LoginActions loginActions = new LoginActions(webdriver);
		loginActions.login("shamotest1","1234567");
		assertion.assertContainsText("密码错误", 10);//断言页面是否包含登录成功在19秒内
	}

	@Test(description = "mtx商城登录正确")
	public void test003_login() throws Exception {
		LoginActions loginActions = new LoginActions(webdriver);
		loginActions.login("shamotest1","123456");
		//assertion.assertContainsText("登录成功", 10);//断言页面是否包含登录成功在19秒内
		boolean flag = operate.isElementExistTimeOut(loginActions.mtxHomePage.modvip());
		assertion.assertTrue(flag);
		
	}
	
	@Test
	public void test004_order() throws Exception {
		OrderActions orderActions = new OrderActions(webdriver);
		orderActions.order();
		assertion.assertContainsText("支付成功", 10);
	}
	
	//@Test
/*	public void test005_headerUpload() throws Exception {
		HeaderUploadActions headerUploadActions=new HeaderUploadActions(webdriver);
		boolean headerUpload = headerUploadActions.headerUpload();
		assertion.assertTrue(headerUpload);
	}
	@Test
	public void test006_sendGoods() throws Exception {
		webdriver.get("http://121.42.15.146:9090/mtx/admin");
		LoginActions loginActions=new LoginActions(webdriver);
		loginActions.loginWithHou("shamo", "123456");
		OrderActions orderActions=new OrderActions(webdriver);
		orderActions.sendGoods("dhhjhsdh");
		assertion.assertContainsText("发货成功", 10);
	}
	@Test
	public void test007_acceptGoods() throws Exception {
		OrderActions orderActions=new OrderActions(webdriver);
		orderActions.acceptGoods();
		assertion.assertContainsText("收货成功", 10);	
	}
	@Test
	public void test008_comment() throws Exception {
		OrderActions orderActions=new OrderActions(webdriver);
		orderActions.comment(4, "货已收到，商家给力，快递很快");
		assertion.assertContainsText("评论成功", 10);	
	}
	@Test
	public void test009_onlyRefund() throws Exception {
		OrderActions orderActions=new OrderActions(webdriver);
		orderActions.onlyRefund("没什么原因，我就是不想要了");;
		assertion.assertContainsText("申请成功", 10);	
	}*/
	

}
