package Utilities;

import Base.BaseClass;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListeners extends BaseClass implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveFailureScreenShots(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextingLog(String message) {
        return message;
    }


    public void onStart(ITestContext iTestContext) {
        System.out.println("In onStart method " + iTestContext.getName());
//         iTestContext.setAttribute("WebDriver", Base.driver);
    }


    public void onFinish(ITestContext iTestContext) {
        System.out.println("In onFinish method " + iTestContext.getName());
    }


    public void onTestStart(ITestResult iTestResult) {
        System.out.println("In onTestStart method " + getTestMethodName(iTestResult) + " start");
    }


    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("In onTestSuccess method " + getTestMethodName(iTestResult) + " success");
    }


    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("In onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");

    }



}
