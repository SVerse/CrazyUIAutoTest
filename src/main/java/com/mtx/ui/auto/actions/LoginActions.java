package com.mtx.ui.auto.actions;

import com.crazy.auto.driver.DriverBase;


public class LoginActions extends PageBaseActions{

	public LoginActions(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 码同学商城登录
	 * @param username
	 * @param pwd
	 * @throws Exception
	 */
	public void login(String username, String pwd) throws Exception {
		//MtxHomePage homePage=new MtxHomePage(driver);
		operate.click(mtxHomePage.loginlink());

		//MtxLoginPage loginPage=new MtxLoginPage(driver);
		operate.sendKeys(mtxLoginPage.username(), username);
		operate.sendKeys(mtxLoginPage.password(), pwd);
		operate.click(mtxLoginPage.loginBtn());
	}

	/**
	 * 码同学后台登录
	 * @param username
	 * @param pwd
	 * @throws Exception
	 */
	public void loginWithHou(String username,String pwd) throws Exception {
		operate.sendKeys(mtxHouLoginPage.username(), username);
		operate.sendKeys(mtxHouLoginPage.password(), pwd);
		operate.click(mtxHouLoginPage.loginBtn());
	}


}
