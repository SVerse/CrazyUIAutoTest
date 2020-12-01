package com.crazy.auto.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * by fengluo
 * poi 4.1.2以下版本都支持，更高版本未验证
 * 注意：excel文件中所有数据都要是文本类型的
 */
public class RangeDatabyPOI {

	/**
	 * 读excel文件
	 * @param filePath 文件路径
	 * @return 返回一个二维数组
	 * @throws IOException
	 */
	public static Object[][] poiRangeData(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;

		String extensionName = filePath.substring(filePath.indexOf("."));
		if (extensionName.equals(".xls")){
			workbook= new HSSFWorkbook(inputStream);
		}else if (extensionName.equals(".xlsx")){
			workbook= new XSSFWorkbook(inputStream);
		}else{
			System.out.println("文件格式不正确");
		}

		Sheet sheet = workbook.getSheetAt(0);
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		List<Object[]> records =new ArrayList <Object[]>();
		
    	for(int i=1;i<rowCount+1;i++){
    		Row row = sheet.getRow(i);
    		String fields[] = new String[row.getLastCellNum()];
    		for(int j=0;j<row.getLastCellNum();j++){
    			fields[j]=row.getCell(j).getStringCellValue();
    		}
    		records.add(fields);
    	}
    	
    	Object[][] results = new Object[records.size()][];
    	for(int i = 0; i < records.size(); i++){
    		results[i] = records.get(i);
    	}

    	return results;
	}

	public static Object[][] poiRangeData(String filePath, String sheetName) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String extensionName = filePath.substring(filePath.indexOf("."));
		if (extensionName.equals(".xls")){
			workbook= new HSSFWorkbook(inputStream);
		}else if (extensionName.equals(".xlsx")){
			workbook= new XSSFWorkbook(inputStream);
		}else{
			System.out.println("文件格式不正确");
		}

		Sheet sheet = null;
		for(int i = 0; i < workbook.getNumberOfSheets(); i++){
			sheet = workbook.getSheetAt(i);
			if(sheet.getSheetName().contains(sheetName)){
				break;
			}
		}

		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		List<Object[]> records =new ArrayList <Object[]>();

		for(int i=1;i<rowCount+1;i++){
			Row row = sheet.getRow(i);
			String fields[] = new String[row.getLastCellNum()];
			for(int j=0;j<row.getLastCellNum();j++){
				fields[j]=row.getCell(j).getStringCellValue();
			}
			records.add(fields);
		}

		Object[][] results = new Object[records.size()][];
		for(int i = 0; i < records.size(); i++){
			results[i] = records.get(i);
		}

		return results;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
