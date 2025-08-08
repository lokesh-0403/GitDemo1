package org.zasya.AutoPractice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReorterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// ThreadSafe
	
	 @Override
	    public void onTestStart(ITestResult result) {
	        // Called when any test method starts
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id(errorvalisation Test-->test
	 }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // Called when a test method passes
	    	extentTest.get().log(Status.PASS, "Test Pass");
	    	
	    	//take a screenshot
	    	
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // Called when a test method fails
//	    	test.log(Status.FAIL, "Test Fail");
	    	extentTest.get().fail(result.getThrowable());
	    	
	    	try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	String filepath = null;
			try {
				filepath = getScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // Called when a test method is skipped
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Called when a test fails but is within success percentage
	    }

	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {
	        // Called when a test fails due to timeout
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // Called before any test method in the suite is run
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // Called after all the test methods in the suite are run
	    	extent.flush();
	    }
}
