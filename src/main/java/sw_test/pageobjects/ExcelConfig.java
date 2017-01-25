package sw_test.pageobjects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
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

        DataFormatter formatter = new DataFormatter();
        Cell cell = sheet.getRow(row).getCell(column);
        String data = formatter.formatCellValue(cell);

        return data;
    }
}
