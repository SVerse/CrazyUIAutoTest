package com.mtx.ui.auto.actions;

import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.element.ElementOperate;
import com.mtx.ui.auto.pageObject.*;

public class PageBaseActions {
	
	public ElementOperate operate;//元素操作对象
	public DriverBase driver;//driver对象

	public MtxHomePage mtxHomePage;
	public MtxLoginPage mtxLoginPage;
	public GoodsDetailPage goodsDetailPage;
	public GoodsOrderPage goodsOrderPage;
	public MtxPersonCenterPage mtxPersonCenterPage;
//	public MtxHouHomePage mtxHouHomePage;
//	public MtxHouLoginPage mtxHouLoginPage;
//	public MtxHouOrderPage mtxHouOrderPage;
//	public MtxOrderPage mtxOrderPage;
//	public MtxCommentPage mtxCommentPage;
//	public MtxAftersalesPage mtxAftersalesPage;
	
	public PageBaseActions(DriverBase driver) {
		this.driver=driver;
		this.operate=new ElementOperate(driver);
		this.mtxHomePage=new MtxHomePage(driver);
		this.mtxLoginPage=new MtxLoginPage(driver);
		this.goodsDetailPage=new GoodsDetailPage(driver);
		this.goodsOrderPage=new GoodsOrderPage(driver);
		this.mtxPersonCenterPage=new MtxPersonCenterPage(driver);
//		this.mtxHouHomePage=new MtxHouHomePage(driver);
//		this.mtxHouLoginPage=new MtxHouLoginPage(driver);
//		this.mtxHouOrderPage=new MtxHouOrderPage(driver);
//		this.mtxOrderPage=new MtxOrderPage(driver);
//		this.mtxCommentPage=new MtxCommentPage(driver);
//		this.mtxAftersalesPage=new MtxAftersalesPage(driver);
	}

}
