package zcom.crm.comcast.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author Rashmi T K
 *
 */

public class ExcelUtility {
	
	/**
	 * This method will return string value from excel sheet
	 * @param sheetNAme
	 * @param rowNO
	 * @param cellNO
	 * @return
	 * @throws Throwable 
	 */
	public String getStringCellData(String sheetName,int rowNO,int cellNO) throws Throwable {
		FileInputStream file=new FileInputStream(IPathConstants.EXCEL_FILEPATH);
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNO);
		Cell cell = row.getCell(cellNO);
		return cell.getStringCellValue();
	}
	/**
	 * This method will return numeric value from excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	public double getNumericCellData(String sheetName,int rowNo,int cellNo) throws Throwable {
		FileInputStream file=new FileInputStream(IPathConstants.EXCEL_FILEPATH);
		Workbook workbook = WorkbookFactory.create(file);
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getNumericCellValue();
	}
	/**
	 * This method will return multiple set of data from excel sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	
	public Object[][] getMultipleData(String sheetName) throws Throwable{
		FileInputStream file = new FileInputStream(IPathConstants.EXCEL_FILEPATH);
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetName);
		int rowNo = sheet.getLastRowNum();
		int cellNo = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowNo][cellNo];
		for(int i=0;i<rowNo;i++) {
			for(int j=0;j<cellNo;j++) {
				data[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
	}
	
	public void writeDataIntoExcel(String sheetName,int rowNo,int cellNo,String value) throws Throwable {
		FileInputStream readfile = new FileInputStream(IPathConstants.EXCEL_FILEPATH);
		Workbook workbook = WorkbookFactory.create(readfile);
		workbook.createSheet(sheetName).createRow(rowNo).createCell(cellNo).setCellValue(value);
		
		FileOutputStream writeFile = new FileOutputStream(IPathConstants.EXCEL_FILEPATH);
		workbook.write(writeFile);
		workbook.close();
	}

}
