package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData_Swag.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);

		if (row == null) {
			workbook.close();
			fis.close();
			return "";
		}

		Cell cell = row.getCell(cellNum);

		if (cell == null || cell.getCellType() == CellType.BLANK) {
			workbook.close();
			fis.close();
			return "";
		}

		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(cell);

		workbook.close();
		fis.close();

		return data;
	}

	public int getRowCount(String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData_Swag.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rowCount = 0;

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);

			if (row != null) {
				Cell usernameCell = row.getCell(0);
				Cell passwordCell = row.getCell(1);

				String username = usernameCell != null ? usernameCell.toString() : "";
				String password = passwordCell != null ? passwordCell.toString() : "";

				if (!username.isEmpty() || !password.isEmpty()) {
					rowCount = i;
				}
			}
		}

		workbook.close();
		fis.close();

		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData_Swag.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();

		workbook.close();
		fis.close();

		return cellCount;
	}

	public void setDataToExcel(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData_Swag.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);

		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		Cell cell = row.getCell(cellNum);

		if (cell == null) {
			cell = row.createCell(cellNum);
		}

		cell.setCellValue(data);

		fis.close();

		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData_Swag.xlsx");
		workbook.write(fos);

		workbook.close();
		fos.close();
	}
}