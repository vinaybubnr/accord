package Utlilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public FileInputStream file;
	public FileOutputStream file1;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String path;
	
	public ExcelUtils(String path) {
		
		this.path = path;
		
	}

	public int getRowCount(String sheetName) throws IOException {

		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		wb.close();
		file.close();
		return rows;

	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {

		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet(sheetName);
		int cells = sheet.getRow(rowNum).getLastCellNum();
		wb.close();
		file.close();
		return cells;

	}

	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {

		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = sheet.getRow(rowNum).getCell(cellNum);
		
		
			DataFormatter formatter = new DataFormatter();
			String data = formatter.formatCellValue(cell);
		
		
		wb.close();
		file.close();
		return data;

	}
	
	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		
		file1 = new FileOutputStream(path);
		wb = new XSSFWorkbook();
		sheet = wb.createSheet(sheetName);
		cell = sheet.createRow(rowNum).createCell(cellNum);
		cell.setCellValue(data);
		wb.write(file1);
		wb.close();
		file1.close();
		
	}

}
