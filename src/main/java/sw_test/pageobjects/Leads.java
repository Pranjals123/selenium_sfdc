package sw_test.pageobjects;


import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.Format;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources";
        String fileName = "TestData.xlsx";
        String excelSheet = "Prospects";
        ExcelConfig excel = new ExcelConfig(filePath + "\\" + fileName, excelSheet);

        //Create a loop to run through all the rows in the spreadsheet, enter all details, save and then navigate back to the start
        for (int i = 1; i <= excel.rowCount; i++) {

            //Create a new Prospect
            driver.findElement(By.name("new")).click();

            //Select Prospect from the dropdown
            driver.findElement(By.name("p3")).sendKeys("Prospect");
            driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();

            //Enter Required details
            driver.findElement(By.name("lea3")).sendKeys(excel.readData(excelSheet, i, 0) + j);//Company
            driver.findElement(By.name("00Nw0000008rAc5")).sendKeys(excel.readData(excelSheet, i, 1));//dealershipType

            //only check the Trading for 12 mths if cell equals Y otherwise skip this part
            if (excel.readData(excelSheet, i, 2).equals("Y")) {
                driver.findElement(By.name("00Nw0000006lDR8")).click();//Agreed to Terms and Conditions
            }

            driver.findElement(By.name("00Nw0000003yzaZ")).sendKeys(excel.readData(excelSheet, i, 3));//VAT Registered
            driver.findElement(By.name("00Nw0000008nW4P")).sendKeys(excel.readData(excelSheet, i, 4));//Source Country
            driver.findElement(By.name("00Nw0000003yg50")).sendKeys(excel.readData(excelSheet, i, 5));//CompanyStatus



            // Assign webelement for picklist VehicleTypesSold
            WebElement VehSold = driver.findElement(By.id("00Nw0000008moaq_unselected"));
            //Assign select Class variable
            Select VehSoldValues = new Select(VehSold);

            //loop through list of entries
            String VehicleSold = excel.readData(excelSheet,i,6);
            String[] VehSoldView =VehicleSold.split("; ");

            //Iterate through the returned values
            for(String str:VehSoldView) {
                VehSoldValues.selectByVisibleText(str);
            }
            //Now the options have been selected, click the right arrow
            driver.findElement(By.id("00Nw0000008moaq_right_arrow")).click();

            driver.findElement(By.name("00Nw0000003yg6N")).sendKeys(excel.readData(excelSheet,i,7));//AverageNumberOfVehicles
            driver.findElement(By.name("name_salutationlea2")).sendKeys(excel.readData(excelSheet,i,8));//Title
            driver.findElement(By.name("name_firstlea2")).sendKeys(excel.readData(excelSheet,i,9));//FirstName
            driver.findElement(By.name("name_lastlea2")).sendKeys(excel.readData(excelSheet,i,10));//LastName
            driver.findElement(By.name("lea8")).sendKeys(excel.readData(excelSheet,i,11));//Phone
            driver.findElement(By.name("00Nw0000003yd45")).sendKeys(excel.readData(excelSheet,i,12));//MobilePhone


            // Assign webelement for picklist Data Source
            WebElement DataSrc = driver.findElement(By.id("00Nw0000008nW4H_unselected"));
            //Assign select Class variable
            Select DataSrcValues = new Select(DataSrc);

            //loop through list of entries
            String DataSourc = excel.readData(excelSheet,i,13);
            String[] DataSrcView =DataSourc.split("; ");

            //Iterate through the returned values
            for(String str:DataSrcView) {
                DataSrcValues.selectByVisibleText(str);
            }
            //Now the options have been selected, click the right arrow
            driver.findElement(By.id("00Nw0000008nW4H_right_arrow")).click();


            driver.findElement(By.name("save")).click();
            //Go back to the main leads tab on each iteration
            driver.findElement(By.linkText("Leads & Apps")).click();

        }

    }


    public void FullApplication() {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources";
        String fileName = "TestData.xlsx";
        String excelSheet = "Leads";
        ExcelConfig excel = new ExcelConfig(filePath+"\\"+fileName, excelSheet);

        //Create a loop to run through all the rows in the spreadsheet, enter all details, save and then navigate back to the start
        for (int i=1; i<=excel.rowCount; i++){

            //replicate what is created via the website
            driver.findElement(By.name("new")).click();

        //Select Full Application from the dropdown
        driver.findElement(By.name("p3")).sendKeys("Full Application");
        driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();

        //Enter Required details
        driver.findElement(By.name("lea3")).sendKeys(excel.readData(excelSheet,i,0)+j);//Company
        driver.findElement(By.name("00Nw0000008PZ6n")).sendKeys(excel.readData(excelSheet,i,1));//Product
        driver.findElement(By.name("00Nw0000003yd5X")).sendKeys(excel.readData(excelSheet,i,2));//Credit Limit

        //only check the Agreed to T's and C's if cell equals Y otherwise skip this part
            if (excel.readData(excelSheet, i, 3).equals("Y")){
                driver.findElement(By.name("00Nw0000003yg6S")).click();//Agreed to Terms and Conditions
            }

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
            }
        //Now the options have been selected, click the right arrow
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



    public void LeadConversion(){

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources";
        String fileName = "TestData.xlsx";
        String excelSheet = "Leads";
        ExcelConfig excel = new ExcelConfig(filePath+"\\"+fileName, excelSheet);

        //click leads and apps tab
        driver.findElement(By.linkText("Leads & Apps")).click();

        //find the Lead view dropdown element
        WebElement LeadViewDropDownList= driver.findElement(By.id("fcf"));
        //Create a Select object with the element for the dropdown list
        Select selectLeadViewDropDownList=new Select(LeadViewDropDownList);
        List<WebElement> allOptions=selectLeadViewDropDownList.getOptions();
        //Get the default view
        WebElement selected_value= selectLeadViewDropDownList.getFirstSelectedOption();

        //if the view is already "Today's Applications then click go. Otherwise set the view which automatically redirects
        if (selected_value.getText() !="Today's Application") {
            driver.findElement(By.id("fcf")).sendKeys("Today's Application");
        } else {
        driver.findElement(By.name("go")).click();
        }

        //Get the company name from the first row of the spreadsheet
//      driver.findElement(By.linkText(excel.readData(excelSheet,1,0)+j)).click();

        driver.findElement(By.linkText(excel.readData(excelSheet,1,0)+j)).click();
        //Check for Duplicates button
        driver.findElement(By.name("dupes")).click();

        //List the rows which match the company name - there should always be 2
        List<WebElement> list = driver.findElements(By.linkText(excel.readData(excelSheet,1,0)+j));

        //Iterate through the list and return the sfdc id for the object using regex, then click the checkbox for that row
        for(int i = 0; i< list.size(); i++){
            String message = list.get(i).getText();
            String attributes = list.get(i).getAttribute("href");
                if (message.contains(excel.readData(excelSheet, 1, 0) + j)) {
                // Here you get the attributes
                    String leadhref = attributes;
                    Pattern p = Pattern.compile("com\\/([^\\/]+)([$?])");
                    Matcher m = p.matcher(leadhref);
                        if (m.find()) {
                            //System.out.println(m.group(1));
                            driver.findElement(By.xpath("//input[@name='cid' and @value='"+m.group(1)+"']")).click();
                        }
                    }
        }

        driver.findElement(By.name("goNext")).click();

        //Don't currently know if this xpath will be the same on each iteration... hope so!
        driver.findElement(By.xpath(".//*[@id='bodyCell']/table/tbody/tr[5]/td/table/tbody/tr[1]/th[2]/a")).click();

        //Select some of the left hand panel
        //Dealership Type
        driver.findElement(By.name("m2097235")).click();
        //Trading for 12 months
        driver.findElement(By.name("m2097268")).click();
        //Are you VAT registered
        driver.findElement(By.name("m2097173")).click();
        //Avg Number of Vehicles in Stock
        driver.findElement(By.name("m2097250")).click();
        //Vehicle Types Sold
        driver.findElement(By.name("m2097226")).click();
        //Phone
        driver.findElement(By.name("m9")).click();
        //Datat Source
        driver.findElement(By.name("m2097230")).click();

        //Merge the leads
        driver.findElement(By.name("save")).click();



        //Switch to the pop-up to confirm merge
        Alert alertOK = driver.switchTo().alert();
        alertOK.accept();


        //Convert the lead!
        driver.findElement(By.name("convert")).click();
        //Select a New Account
        driver.findElement(By.name("j_id0:j_id29:j_id30:j_id34:j_id41:acclist")).sendKeys("Create New: "+excel.readData(excelSheet,1,0)+j);

        //Convert
        driver.findElement(By.name("j_id0:j_id29:j_id30:j_id31:bottom:j_id32")).click();


        //Verify you are now viewing the converted opportunity record
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("submit_for_approval_ireland")));



      //  System.out.println("Help");


    }




    public void CreateTasks(){

        driver.findElement(By.id("phSearchInput")).sendKeys("Simon Activities 2");
        driver.findElement(By.id("phSearchInput")).sendKeys(Keys.RETURN);
        driver.findElement(By.linkText("Simon Activities 2")).click();

        for(int l = 0; l<52; l++ ){
            driver.findElement(By.name("task")).click();
            driver.findElement(By.name("tsk5")).sendKeys("Call"+l);
            driver.findElement(By.name("tsk4")).sendKeys("02/02/2017");
          //  driver.findElement(By.name("tsk12")).sendKeys("Completed");
            driver.findElement(By.name("IsReminderSet")).click();
            driver.findElement(By.name("save")).click();
        }

        //System.out.println("test");
    }

}



