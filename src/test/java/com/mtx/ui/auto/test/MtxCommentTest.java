package com.mtx.ui.auto.test;

import com.crazy.auto.excel.ExcelDataUtil;
import com.mtx.ui.auto.actions.LoginActions;
import com.mtx.ui.auto.actions.OrderActions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 码同学商城商品评论测试用例
 */
public class MtxCommentTest extends MtxTestBase{

    @DataProvider
    public Object[][] getCommentData() throws Exception {
        String datafile = "src/main/resources/data/mtx_data.xlsx";
        ExcelDataUtil excel = new ExcelDataUtil(datafile);
        Object[][] testData = excel.getTestData("评论");
        excel.close();
        return testData;
    }

    @Test(dataProvider = "getCommentData")
    public void test003_comment(String caseDesc, String ratingFlag, String rating, String contentFlag, String content, String assertValue) throws Exception {
        LoginActions loginActions = new LoginActions(webdriver);
        loginActions.login("shamotest1", "123456");
        assertion.assertContainsText("登录成功", 5);
        webdriver.sleep(3);
        OrderActions orderActions = new OrderActions(webdriver);
        orderActions.comment(ratingFlag, Integer.parseInt(rating), contentFlag, content);
        assertion.assertContainsText(assertValue, 5);
    }

    @AfterMethod
    public void deleteCookie() {

        webdriver.deleteCookies();
        webdriver.close();	//关闭window
        webdriver.switchToWindow(0);
    }
}
