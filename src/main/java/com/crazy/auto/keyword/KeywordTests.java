package com.crazy.auto.keyword;

import com.crazy.auto.excel.ExcelDataUtil;
import com.crazy.auto.test.TestBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * javamall测试驱动类
 */
public class KeywordTests extends TestBase {
	private static Logger log = Logger.getLogger(KeywordTests.class);
	public Method method[];
	public KeyWordsAction keyWordsaction;

	@DataProvider
	public Object[][] getKeywords() throws Exception {
		String file = "src/main/resources/keywords/javamall_cases3.xlsx";
		ExcelDataUtil excel = new ExcelDataUtil(file);
		Object[][] obj = excel.getKeywordData("测试用例集合");
		excel.close();
		return obj;
	}

	@Test(dataProvider = "getKeywords")
	public void testKeword(String casenum, String casename, String flag) throws Exception {
		keyWordsaction = new KeyWordsAction(driver);
		method = keyWordsaction.getClass().getMethods();
		String file = "src/main/resources/keywords/javamall_cases3.xlsx";
		ExcelDataUtil excel = new ExcelDataUtil(file);
		Object[][] testData = excel.getTestData(casename);
		KeyWordsAction.testResult = true;
		for (int i = 0; i < testData.length; i++) {
			String keyword = testData[i][1].toString();
			String elementInfo = testData[i][2].toString();
			String value = testData[i][3].toString();
			excuteKeyword(keyword, elementInfo, value);
			if (KeyWordsAction.testResult == false) {
				excel.setCellData(i + 1, excel.getLastColIndex(casename), casename, false);
				excel.setCellData(Integer.valueOf(casenum), excel.getLastColIndex("测试用例集合"), "测试用例集合", false);
				Assert.fail(casename + "失败", KeyWordsAction.exception);
				break;
			} else {
				excel.setCellData(i + 1, excel.getLastColIndex(casename), casename, true);
			}
		}
		if (KeyWordsAction.testResult) {
			excel.setCellData(Integer.valueOf(casenum), excel.getLastColIndex("测试用例集合"), "测试用例集合", true);
		}
		excel.close();
	}

	private void excuteKeyword(String keyword, String elementInfo, String value) {
		try {
			for (int i = 0; i < method.length; i++) {
				if (method[i].getName().equalsIgnoreCase(keyword)) {
					method[i].invoke(keyWordsaction, elementInfo, value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("执行步骤【" + keyword + "】发生异常" + e);
		}
	}
}
