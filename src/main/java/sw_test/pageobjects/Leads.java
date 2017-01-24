package sw_test.pageobjects;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;


/**
 * Created by simon.wilby on 19/01/2017.
 */
public class Leads {

    private WebDriver driver;
    RandomNameGenerator randGen;
    //get the static value from the randomiser
    static String j = RandomNameGenerator.getRandomNumber();

    public Leads(WebDriver driver) {
        //pass the driver through to the next method
        this.driver = driver;
    }

    public void LeadProspectCreation() {
        //Create a new Prospect
        driver.findElement(By.name("new")).click();
        //Select Prospect from the dropdown
        driver.findElement(By.name("p3")).sendKeys("Prospect");
        driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();

        //Enter Required details
        driver.findElement(By.name("lea3")).sendKeys("Simon Test Company_"+j);
        driver.findElement(By.id("00Nw0000008nW4P")).sendKeys("UK");

        //Assign webelement for picklist vehicleTypesSold
        WebElement VehicleTypesSold = driver.findElement(By.id("00Nw0000008moaq_unselected"));
        //Assign select Class variable
        Select vehicleTypes = new Select(VehicleTypesSold);
        //Select value by index
        vehicleTypes.selectByVisibleText("Cars");
        driver.findElement(By.id("00Nw0000008moaq_right_arrow")).click();

        driver.findElement(By.id("name_lastlea2")).sendKeys("Prospect");
        driver.findElement(By.id("lea8")).sendKeys("01244 566797");

        //Assign webelement for picklist Data Source
        WebElement DataSrc = driver.findElement(By.id("00Nw0000008nW4H_unselected"));
        //Assign select Class variable
        Select dataSrcValues = new Select(DataSrc);
        dataSrcValues.selectByVisibleText("AE generated");
        driver.findElement(By.id("00Nw0000008nW4H_right_arrow")).click();

        //Save the record!
        driver.findElement(By.name("save")).click();
    }

    public void FullApplication() {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources";
        String fileName = "TestData.xlsx";
        String excelSheet = "Leads";
        ExcelConfig excel = new ExcelConfig(filePath+"\\"+fileName, excelSheet);



        //Create a loop to run through all the rows in the spreadsheet
        for (int i=1; i<=excel.rowCount; i++){

            //replicate what is created via the website
            driver.findElement(By.name("new")).click();

        //Select Full Application from the dropdown
        driver.findElement(By.name("p3")).sendKeys("Full Application");
        driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();

        //Enter Required details
        driver.findElement(By.name("lea3")).sendKeys(excel.readData(excelSheet,i,0)+j);//Company
        driver.findElement(By.name("00Nw0000008PZ6n")).sendKeys(excel.readData(excelSheet,i,1));//Product

      //  driver.findElement(By.name("00Nw0000003yd5X")).sendKeys(excel.readData(excelSheet,i,2));//Credit Limit
        driver.findElement(By.name("00Nw0000003yg6S")).click();//Agreed to Terms and Conditions
        driver.findElement(By.name("00Nw0000003yg50")).sendKeys(excel.readData(excelSheet,i,4));//Company Status
        driver.findElement(By.name("00Nw0000008nW4P")).sendKeys(excel.readData(excelSheet,i,5));//Source Country
        //address details
        driver.findElement(By.name("00Nw0000003yd4Z")).sendKeys(excel.readData(excelSheet,i,6));//line1
        driver.findElement(By.name("00Nw0000003yd4e")).sendKeys(excel.readData(excelSheet,i,7));//line2
        driver.findElement(By.name("00Nw0000003yg5o")).sendKeys(excel.readData(excelSheet,i,8));//line3
        driver.findElement(By.name("00Nw0000003yd4j")).sendKeys(excel.readData(excelSheet,i,9));//Town/City
        driver.findElement(By.name("00Nw0000003yd4o")).sendKeys(excel.readData(excelSheet,i,10));//County
        driver.findElement(By.name("00Nw0000003yd4t")).sendKeys(excel.readData(excelSheet,i,11));//Postcode
        driver.findElement(By.name("lea11")).sendKeys(excel.readData(excelSheet,i,12));//email address
        driver.findElement(By.name("lea12")).sendKeys(excel.readData(excelSheet,i,13));//website

        // Assign webelement for picklist Business Activities
        WebElement BusAct = driver.findElement(By.id("00Nw0000003yd4y_unselected"));
        //Assign select Class variable
        Select BusActValues = new Select(BusAct);

        //loop through list of entries

            String BusinessAct = excel.readData(excelSheet,i,14);
            String[] BusinessActView =BusinessAct.split("; ");


            //Iterate through the returned values
            for(String str:BusinessActView) {
                BusActValues.selectByVisibleText(str);
               // System.out.println(BusinessActView);
            }


        driver.findElement(By.id("00Nw0000003yd4y_right_arrow")).click();

        driver.findElement(By.name("00Nw0000003yvki")).sendKeys(excel.readData(excelSheet,i,15));//Business Phone
        driver.findElement(By.name("00Nw0000003yd45")).sendKeys(excel.readData(excelSheet,i,16));//Mobile Phone
        driver.findElement(By.name("name_salutationlea2")).sendKeys(excel.readData(excelSheet,i,17));//Title
        driver.findElement(By.name("name_firstlea2")).sendKeys(excel.readData(excelSheet,i,18));//FirstName
        driver.findElement(By.name("name_lastlea2")).sendKeys(excel.readData(excelSheet,i,19));//LastName

        driver.findElement(By.name("save")).click();
            //Go back to the main leads tab on each iteration
            driver.findElement(By.linkText("Leads & Apps")).click();
        }
    }
}
