package com.mtx.ui.auto.actions;

import com.crazy.auto.driver.DriverBase;
import com.crazy.auto.utils.ImageUtil;
import com.crazy.auto.utils.RandomUtil;


public class HeaderUploadActions extends PageBaseActions {

	public HeaderUploadActions(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 完成期望图片的采集
	 *
	 * 1.通常在编写测试用例时进行采集，采集完成后保存成标准图片放在 images 目录下，供自动化测试时使用
	 * 2.一般至少采集2张图片，自动化测试时要保证上传的图片与当前图片不一致
	 */
	public void headerUploadCollection() throws Exception {
		operate.click(mtxHomePage.personcenter());
		operate.click(mtxPersonCenterPage.personinfo());
		operate.click(mtxPersonCenterPage.change());
		driver.sleep(3);
		operate.click(mtxPersonCenterPage.selectimage());
		driver.sleep(5);
		operate.uploadFile("C:\\Users\\lixio\\Desktop\\345.png");
		driver.sleep(2);
		operate.click(mtxPersonCenterPage.confirmimage());
		driver.sleep(6);
		operate.getScreenshotAs(mtxPersonCenterPage.headerimg(), "images/mtx/expect2.png");
	}


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
		do {
			new_index= RandomUtil.randomInt(1, 2);
		} while (new_index==cur_index);
		
		operate.click(mtxPersonCenterPage.change());
		driver.sleep(3);
		operate.click(mtxPersonCenterPage.selectimage());
		driver.sleep(5);
		operate.uploadFile("images/mtx/"+new_index+".png");
		driver.sleep(2);
		operate.click(mtxPersonCenterPage.confirmimage());
		driver.sleep(6);
		operate.getScreenshotAs(mtxPersonCenterPage.headerimg(), "images/mtx/new.png");
		return ImageUtil.compareImg("images/mtx/new.png", "images/mtx/expect"+new_index+".png", 100f);
	}


}
