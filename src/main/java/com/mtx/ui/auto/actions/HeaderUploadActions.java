package com.mtx.ui.auto.actions;

import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.utils.ImageUtil;
import com.crazy.auto.utils.RandomUtil;


public class HeaderUploadActions extends PageBaseActions {

	public HeaderUploadActions(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//先完成期望图片的采集
//	public void headerUpload() throws Exception {
//		operate.click(mtxHomePage.personcenter());
//		operate.click(mtxPersonCenterPage.personinfo());
//		operate.click(mtxPersonCenterPage.change());
//		driver.sleep(3);
//		operate.click(mtxPersonCenterPage.selectimage());
//		driver.sleep(5);
//		operate.uploadFile("C:\\Users\\lixio\\Desktop\\345.png");
//		driver.sleep(2);
//		operate.click(mtxPersonCenterPage.confirmimage());
//		driver.sleep(6);
//		operate.getScreenshotAs(mtxPersonCenterPage.headerimg(), "images/mtx/expect2.png");
//	}

/*
	public boolean headerUpload() throws Exception {
		//
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.personinfo());
		//要获取当前头像的图片，判断当前图片是哪个图
		operate.getScreenshotAs(mtxPersonCenterPage.headerimg(), "images/mtx/current.png");
		int cur_index=1;
		for (int i = 1; i < 3; i++) {
			if (ImageUtil.compareImg("images/mtx/current.png", "images/mtx/expect"+i+".png", 100f)) {
				cur_index=i;
				break;
			}
		}
		int new_index=1;
//		
//		while (new_index==cur_index) {
//			new_index=RandomUtil.randomInt(1, 2);
//		}
		do {
			new_index= RandomUtil.randomInt(1, 2);
		} while (new_index==cur_index);
		
		operate.click(mtxPersonCenterPage.change());
		driver.sleep(3);
		operate.click(mtxPersonCenterPage.selectimage());
		driver.sleep(5);
		operate.uploadFile("D:\\eclipse-workspace\\CrazyAutoTest1\\images\\mtx\\"+new_index+".png");
		driver.sleep(2);
		operate.click(mtxPersonCenterPage.confirmimage());
		driver.sleep(6);
		operate.getScreenshotAs(mtxPersonCenterPage.headerimg(), "images/mtx/new.png");
		return ImageUtil.compareImg("images/mtx/new.png", "images/mtx/expect"+new_index+".png", 100f);
	}
*/

}
