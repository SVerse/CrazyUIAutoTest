package com.mtx.ui.auto.actions;

import com.crazy.auto.driver.DriverBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderActions extends PageBaseActions{

	public OrderActions(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 前台下单
	 * @throws Exception
	 */
	public void order() throws Exception {
		operate.click(mtxHomePage.firstgoods());

		//切换到商品详情页的新窗口上
		driver.switchToWindow(1);
		driver.sleep(2); //此处需要优化
		operate.click(goodsDetailPage.buy());

		operate.click(goodsOrderPage.货到付款());
		operate.click(goodsOrderPage.提交订单());
	}

	/**
	 * 后台发货
	 * @param express_number
	 * @throws Exception
	 */
	public void sendGoods(String express_number) throws Exception {
		operate.click(mtxHouHomePage.orderlink());
		operate.click(mtxHouHomePage.ordermenu());
		driver.switchToFrame(mtxHouOrderManagePage.orderiframe(), 5);
		operate.click(mtxHouOrderManagePage.sendgoods());
		driver.sleep(3); //此处需要优化
		operate.click(mtxHouOrderManagePage.express());
		operate.sendKeys(mtxHouOrderManagePage.express_number(), express_number);
		operate.click(mtxHouOrderManagePage.expressconfirm());
	}

	/**
	 * 前台收货
	 * @throws Exception
	 */
	public void acceptGoods() throws Exception {
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.orderlink());
		operate.click(mtxOrderPage.acceptgoods());
		operate.click(mtxOrderPage.confirm());
	}

	/**
	 * 前台评论
	 * @param rating 星号数量
	 * @param content 评论内容
	 * @throws Exception
	 */
	public void comment(int rating, String content) throws Exception {
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.orderlink());
		operate.click(mtxOrderPage.comment());
		driver.switchToWindow(2); //此处需要优化
		List<WebElement> ratings = driver.findElements(mtxCommentPage.rating());
		ratings.get(rating).click();
		operate.sendKeys(mtxCommentPage.content(), content);
		operate.click(mtxCommentPage.amswitch());
		operate.click(mtxCommentPage.submit());
	}

	/**
	 * 评论
	 * @param ratingFlag 是否点击星级
	 * @param rating 星号数量
	 * @param contentFlag 是否输入评论
	 * @param content 评论内容
	 * @throws Exception
	 */
	public void comment(String ratingFlag, int rating, String contentFlag, String content) throws Exception {
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.orderlink());
		operate.click(mtxOrderPage.comment());
		driver.switchToWindow(1);	//此处需要优化
		if (ratingFlag.equalsIgnoreCase("y")) {
			List<WebElement> ratings = driver.findElements(mtxCommentPage.rating());
			ratings.get(rating).click();
		}
		if (contentFlag.equalsIgnoreCase("y")) {
			operate.sendKeys(mtxCommentPage.content(), content);
		}
		operate.click(mtxCommentPage.amswitch());
		operate.click(mtxCommentPage.submit());
	}

	/**
	 * 申请售后-仅退款
	 * @param msg
	 * @throws Exception
	 */
	public void onlyRefund(String msg) throws Exception {
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.orderlink());
		operate.click(mtxOrderPage.申请售后());
		driver.switchToWindow(3); //此处需要优化
		operate.click(mtxAftersalesPage.onlyrefund());
		String value = mtxAftersalesPage.reason().getValue();
		driver.executeJS("document.getElementsByName('"+value+"')[0].style='';");
		operate.selectRandomOption(mtxAftersalesPage.reason());
		operate.sendKeys(mtxAftersalesPage.msg(), msg);
		operate.click(mtxAftersalesPage.submit());
	}


}
