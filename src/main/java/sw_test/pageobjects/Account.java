package sw_test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by simon.wilby on 18/01/2017.
 */
public class Account {

    private WebDriver driver;

    public Account(WebDriver driver) {
        //pass the driver through to the next method
        this.driver = driver;

    }

    public void AccountViewOptions() {
        //find the account view dropdown element
        WebElement accViewDropDownList= driver.findElement(By.id("fcf"));

        //Create a Select object with the element for the dropdown list
        Select selectaccViewDropDownList=new Select(accViewDropDownList);
        List<WebElement> allOptions=selectaccViewDropDownList.getOptions();

        //count number of records returned vs expected number
        //Expected number
        Integer accViewCnt=15;
        //Check
        if (accViewCnt != allOptions.size()) {
            throw new Errors("Expected "+accViewCnt+" Got "+allOptions.size()+" for the number of Account Views");
        }

        //Get and print the default view
        WebElement selected_value= selectaccViewDropDownList.getFirstSelectedOption();
        System.out.println("First selected view: " +selected_value.getText());

        //Array of expected values
        String accView= "All Accounts;All Accounts;All Accounts;Credit Limit Review;Low Utilisation;My Accounts;New Auction Partner G3;New This Week;Non Prospect;Prospect Qualified;Recently Viewed Accounts;StockMaster;Suspects;Test 2;Test Accounts";
        String[] arraccView=accView.split(";");

        //Iterate through the returned values and check if they match with the expected array
        for(String str:arraccView) {
            boolean found=false;
            for(WebElement ele:allOptions) {
                if(str.equals(ele.getText())) {
                    found=true;
                   // System.out.println(str+" Option value exists");
                    break;
                }
            }
            //if not found in the array then return this error
            if(!found) {
                throw new Errors(str + " The dropdown value does not exist");
            }



        }

        if (selected_value.getText() !="Prospect Qualified") {
            driver.findElement(By.id("fcf")).sendKeys("Prospect Qualified");
            //navigate back to Account tab so the rest of the test completes every time
            //select the Account tab
            driver.findElement(By.linkText("Accounts")).click();
            //wait for the presence of the view dropdown list to load
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fcf")));
        }


        //page has refreshed so we need to get the View options again.
        WebElement accViewDropDownList1= driver.findElement(By.id("fcf"));

        //Create a Select object with the element for the dropdown list
        Select selectaccViewDropDownList1=new Select(accViewDropDownList1);
        List<WebElement> allOptions1=selectaccViewDropDownList1.getOptions();
        WebElement selected_value1= selectaccViewDropDownList1.getFirstSelectedOption();
        System.out.println("After selected view value is: "+selected_value1.getText());

        //driver.findElement(By.name("go")).click();
    }

    public void AccountCreation() {
        //Create a new Account
        driver.findElement(By.name("new")).click();
        driver.findElement(By.id("p3")).sendKeys("Companies");
        driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();

        //wait for the presence of the view dropdown list to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("acc2")));

    }
}
