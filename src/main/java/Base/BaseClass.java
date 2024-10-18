package Base;

import Utilities.Locators;
import Utilities.Screenshot;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BaseClass {

    long timeOuts =20;
    static long maxWaitTime = 20;
    public static RemoteWebDriver driver;
    public  static WebDriverWait wait ;

    @Step("lanch the Browser : {0}")
    public void  Browerlaunch(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.err.println("Driver not Found");
        }
        if (driver != null) {
//            prop = new Properties();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOuts));
            driver.get("https://auditnesttest.azurewebsites.net");
            wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
            System.out.println("Driver initialized and URL loaded: ");
        } else {
            System.err.println("Driver initialization failed.");
        }
    }

    public void close() {

        driver.close();
    }

    public void quite() {

        driver.quit();
    }

    public void Send(WebElement element,String value){
         WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
         element1.clear();
         element1.sendKeys(value);
    }

    public WebElement Findelement(Locators type, String value) {

        if (driver == null) {
            System.err.println("Driver is null in FindElement");
            return null;
        }

        switch(type)
        {
            case id:
                return driver.findElement(By.id(value));

            case name:
                return driver.findElement(By.name(value));

            case xpath:
                return driver.findElement(By.xpath(value));

            case link:
                return driver.findElement(By.linkText(value));

            case className:
                return driver.findElement(By.className(value));

            default:
                break;
        }
        return null;
    }

    public List<WebElement> Findelements(Locators type, String value) {
        if (driver == null) {
            System.err.println("Driver is null in FindElement");
            return null;
        }
        switch(type)
        {
            case id:
                return driver.findElements(By.id(value));

            case name:
                return driver.findElements(By.name(value));

            case xpath:
                return driver.findElements(By.xpath(value));

            case link:
                return driver.findElements(By.linkText(value));

            case className:
                return driver.findElements(By.className(value));

            case cssSelector:
                return driver.findElements(By.cssSelector(value));

            default:
                throw new IllegalArgumentException("Invalid locator type: " + type);

        }
    }

    public void SelectVisibleText(WebElement ele, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
        new Select(element).selectByVisibleText(value);

    }
    public void click(WebElement ele) {
        WebElement element =wait.until(ExpectedConditions.elementToBeClickable(ele));
        element.click();

    }

    public void AlertAccept() {

        driver.switchTo().alert().accept();

    }

    public String getText(Locators type,String value) {

        String text;
        if (driver == null) {
            System.err.println("Driver is null in FindElement");
            return null;
        }

        switch(type)
        {
            case id:

                text = driver.findElement(By.id(value)).getText();
                System.out.println(text);
                return text;


            case name:

                text= driver.findElement(By.name(value)).getText();
                System.out.println(text);
                return text;

            case xpath:

                return text= driver.findElement(By.xpath(value)).getText();

            case link:

                return  text = driver.findElement(By.linkText(value)).getText();

            case className:

                return  text = driver.findElement(By.className(value)).getText();

            default:
                return null;

        }

    }


    public void Screen(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String screenshotPath = Screenshot.captureScreenshot(driver, result.getName());
            attachScreenshotToAllure(screenshotPath);
        }

    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshotToAllure(String path) {
        try {
            return FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

//    @BeforeSuite
//    public void setAllureEnvironment() {
//        allureEnvironmentWriter(
//                ImmutableMap.<String, String>builder().put("Browser", "Edge").put("Browser.Version", "127.0.6533.122").put("OS Name","Windows 11")
//                        .put("URL", "https://auditnesttest.azurewebsites.net").build(),
//                System.getProperty("user.dir") + "/allure-results/");
//    }

@BeforeClass
    @Step("Enter the Microsoft Authentication of Email and Password")
    public void Setup() throws InterruptedException {

      Browerlaunch("edge");

            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0116")));
            emailField.sendKeys("raveendran.manickam@ilink-systems.com");
            click(Findelement(Locators.id, "idSIButton9"));      //driver.findElement(By.id("idSIButton9")).click();
            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("i0118")));//passwordField.sendKeys(Password);
            passwordField.sendKeys("Rmm:2003");
            Thread.sleep(3000);
            WebElement click_sign = driver.findElement(By.id("idSIButton9"));
            click_sign.click();
            Thread.sleep(3000);

        }
    }




