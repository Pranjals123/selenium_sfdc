package sw_test.pageobjects;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * Created by simon.wilby on 23/01/2017.
 */
public class ExcelConfig {
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    int rowCount;

    public ExcelConfig(String excelPath, String excelSheet)
    {
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            wb = new XSSFWorkbook(fis);
            wb.getSheet(excelSheet);
            rowCount = wb.getSheet(excelSheet).getLastRowNum();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String readData (String excelSheet, int row, int column){

        sheet = wb.getSheet(excelSheet);
        String data = sheet.getRow(row).getCell(column).getStringCellValue();
            return data;
    }
}
