package com.crazy.auto.keyword;

import com.crazy.auto.excel.ExcelDataUtil;
import com.mtx.ui.auto.test.MtxTestBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * 码同学商城测试驱动类
 */
public class KeyWordTestsMtx extends MtxTestBase {
	private static Logger log = Logger.getLogger(KeyWordTestsMtx.class);
	KeyWordsAction keyWordsAction;
	Method[] methods;

	@DataProvider
	public Object[][] getCaseNames() throws Exception{
		String file = "src/main/resources/keywords/mtxshop.xlsx";
		ExcelDataUtil excel = new ExcelDataUtil(file);
		Object[][] caseNames = excel.getKeywordData("测试用例集合");
		excel.close();
		return caseNames;
	}
	
	@Test(dataProvider = "getCaseNames")
	public void test(String casenum, String casename, String flag) throws Exception {
		keyWordsAction = new KeyWordsAction(webdriver);
		methods = keyWordsAction.getClass().getMethods();//会返回KeyWordsAction实例的所有方法对象

		log.info("casenum:" + casenum + ", casename: " + casename + ", flag: " + flag );
		String file = "src/main/resources/keywords/mtxshop.xlsx";
		ExcelDataUtil excel = new ExcelDataUtil(file);
		Object[][] testData = excel.getTestData(casename);
		KeyWordsAction.testResult = true;

		for (int i = 0; i < testData.length; i++) {
			String keyword = testData[i][1].toString();
			String locator = testData[i][2].toString();
			String value = testData[i][3].toString();
			System.out.println(keyword + " " + locator + " " + value);

			System.out.println("excel.getLastColIndex(casename) = " + excel.getLastColIndex(casename));

			excuteKeyword(keyword, locator, value);//这表示执行当前步骤
			if (KeyWordsAction.testResult) {
				//当前步骤执行成功
				excel.setCellData(i+1, excel.getLastColIndex(casename), casename, true);
			}else {
				//当前步骤执行失败
				excel.setCellData(i+1, excel.getLastColIndex(casename), casename, false);
				excel.setCellData(Integer.parseInt(casenum), excel.getLastColIndex("测试用例集合"), "测试用例集合", false);
				excel.close();
				Assert.fail(casename + "失败",KeyWordsAction.exception);
				break;
			}
		}
		
		if (KeyWordsAction.testResult) {
			excel.setCellData(Integer.parseInt(casenum), excel.getLastColIndex("测试用例集合"), "测试用例集合", true);
		}
		excel.close();
	}

	/**
	 * 使用java反射调用关键字对应的方法
	 * @param keyword
	 * @param locator
	 * @param value
	 */
	public void excuteKeyword(String keyword, String locator, String value) {
		for (int j = 0; j < methods.length; j++) {
			String name = methods[j].getName();
			if (name.equals(keyword)) {
				try {
					methods[j].invoke(keyWordsAction, locator, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					KeyWordsAction.testResult=false;
				} //java反射的用法
			}
		}
	}

}
