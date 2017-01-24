package sw_test.pageobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by simon.wilby on 16/01/2017.
 */


public class salesforceTestDropwdownList2 {

    private WebDriver driver;
    List<WebElement> options;

    @Before
    public void setUp() throws Exception {
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Training\\Chrome_Driver\\chromedriver.exe");
        driver  = new ChromeDriver();
        driver.manage().window().maximize();

        //Step 2 - Navigate to salesforce
        driver.navigate().to("https://test.salesforce.com/");
    }

    @After
    public void tearDown() throws Exception {
        //Step 4 - Logout and Quit
        driver.findElement(By.id("userNavLabel")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void ChromeTest() throws Exception {
        //Step 3 - Enter user credentials
        driver.findElement(By.name("username")).sendKeys("simon.wilby@nextgearcapital.co.uk.uat");
        driver.findElement(By.name("pw")).sendKeys("!C0rnfl4kes!?");

        //Login and wait for the page to finish loading
        driver.findElement(By.name("Login")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavLabel")));

        //Navigate to Accounts tab
        driver.findElement(By.linkText("Accounts")).click();
        //WebElement selectElement = driver.findElement(By.id("fcf"));
        //Select select = new Select(selectElement);
        //List<WebElement> allOptions = select.getOptions();


        //Get and print the list of options from Dropdown list
        accountPickListOptions();}
       // for (WebElement option : options) {
       //     System.out.println(option.getText());
       //    }
       // }


        //accountPickList check dropdown values
        public void accountPickListOptions() {

            //WebElement accViewDropDownList= driver.findElement(By.id("fcf"));
            //Create a Select object with the element
            //Select selectaccViewDropDownList=new Select(accViewDropDownList);
            //List<WebElement> allOptions=selectaccViewDropDownList.getOptions();

            Select dropDownList = new Select(driver.findElement(By.id("fcf")));
            System.out.println("First selected item: " + dropDownList.getFirstSelectedOption().getText());
            options = dropDownList.getOptions();
            System.out.println("Number of items: " + options.size());




        }
    }
