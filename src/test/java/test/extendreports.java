package test;
 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basePage.Base;
 
public class extendreports implements ITestListener{
	public ExtentSparkReporter sparkReporter;  
	public ExtentReports extent;
	public ExtentTest test;
 
	public void onStart(ITestContext context) {
		sparkReporter=new ExtentSparkReporter("C:\\Users\\2304055\\eclipse-workspace\\Hackathon-Project-IdentifyNewBikes-main\\Hackathon-Project-IdentifyNewBikes-main\\HackathonProjectIdentify\\ExtentReport\\extentReportFile.html"); //UI of the report
		sparkReporter.config().setDocumentTitle("Hackathon project"); 
		sparkReporter.config().setReportName("finding new bikes");
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);	
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("BROWSER","Edge");
	}
 
 
	public void onTestSuccess(ITestResult result){
//		test = extent.createTest(result.getName());
//		test.log(Status.PASS, "Test case PASSED is:" + result.getName());
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS,result.getName()+" got successfully executed");
	   String imgPath = new Base().captureScreen1(result.getName());
		test.addScreenCaptureFromPath(imgPath);			
	}
 
	public void onTestFailure(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
//		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
       String imgPath = new Base().captureScreen1(result.getName());
	    test.addScreenCaptureFromPath(imgPath);	
	}
 
	public void onTestSkipped(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
	test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.SKIP,result.getName()+" got skipped");
		String imgPath = new Base().captureScreen1(result.getName());
		test.addScreenCaptureFromPath(imgPath);	
	}
 
	
	public void onFinish(ITestContext context) {
		extent.flush();
		  String reportFilePath = "C:\\\\Users\\\\2304055\\\\eclipse-workspace\\\\Hackathon-Project-IdentifyNewBikes-main\\\\Hackathon-Project-IdentifyNewBikes-main\\\\HackathonProjectIdentify\\\\ExtentReport\\\\extentReportFile.html";
	        File htmlFile = new File(reportFilePath);
	        try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}