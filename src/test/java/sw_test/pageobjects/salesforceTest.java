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


public class salesforceTest {

    private WebDriver driver;
    List<WebElement> options;
    public Logon NewLogon;
    public Account AccountPage;
    public Leads LeadPage;

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
    public void testSalesforceAccount(){




        NewLogon = new Logon(driver);
        LeadPage = NewLogon.goToLeadPage();
        LeadPage.LeadProspectCreation();
        LeadPage = NewLogon.goToLeadPage();
        LeadPage.FullApplication();

        LeadPage = NewLogon.goToLeadPage();
                //    LeadPage.CreateTasks();
        LeadPage.LeadConversion();
     //   AccountPage = NewLogon.goToAccountPage();
     //   AccountPage.AccountViewOptions();
        //AccountPage.AccountCreation();


        }
    }
