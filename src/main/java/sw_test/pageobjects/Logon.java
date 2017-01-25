package sw_test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by simon.wilby on 18/01/2017.
 */
public class Logon {
    private WebDriver driver;

    public Logon(WebDriver driver) {
        this.driver = driver;
        //User Credentials
        driver.findElement(By.name("username")).sendKeys("automation.user@examplecompany.co.uk.ukdev04");
        driver.findElement(By.name("pw")).sendKeys("8ut0M4tionUzer!");

        //Login and wait for the page to finish loading
        driver.findElement(By.name("Login")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavLabel")));
    }
    public Account goToAccountPage(){
        //select the Account tab
        driver.findElement(By.linkText("Accounts")).click();
        //wait for the presence of the view dropdown list to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fcf")));
        //pass the driver through to the next method
        return new Account(driver);
    }
    public Leads goToLeadPage(){
        //select the Lead tab
        driver.findElement(By.linkText("Leads & Apps")).click();
        //wait for the presence of the view dropdown list to load
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
        //pass the driver through to the next method
        return new Leads(driver);
    }
}
