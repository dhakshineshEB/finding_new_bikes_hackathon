package regression;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import basePage.Base;
import googlesignin_functionality.GoogleSignin;
import upcomingbikes_functionality.UpcomingBikes;
import usedcars_functionality.UserCars;

//Class for Regression tests
public class RegressionTests{

	public static WebDriver driver;
	public Logger logger;
	// Setting up the driver before each test method
	@Parameters({"browser"})
	@BeforeClass(groups= {"regression","smoke"})
	public void setup(String browser) throws MalformedURLException {
		logger=LogManager.getLogger(this.getClass());
		Base base = new Base();
		logger.info("driver is initiated and the website is launched");
		driver = base.driverSetup(browser);
	}
    
	// Test method for Upcoming Bikes
	@Test(priority = 1,groups= {"regression"})
	public void upComingBikes() throws Exception{
		
		try{
			UpcomingBikes up = new UpcomingBikes(driver);
		logger.info("new bike menu is clicked");
		up.newBikesMenu();
		logger.info("selecting the upcoming bikes option");
		up.selectUpcomingBike();
		logger.info("choosing the manufacturer as honda");
		up.selectManufacturer();
		up.viewMoreBikes();
		logger.info("getting the bike details and printing in the console");
		up.bikeModels();
		}catch(Exception e)
		{
			logger.error("upcoming bikes testcase failed");
		}
		
	}
	
	// Test method for Used Cars
	@Test(priority = 2,groups= {"regression"})
	public void usedCars() throws InterruptedException, IOException {
		try
		{
			logger.info("getting the popular car models list");
		UserCars userCars = new UserCars(driver);
		userCars.usedCarsMenu();
		userCars.selectChennaiUsedCars();
		userCars.modelList();
		}catch(Exception e)
		{
			logger.error("used cars testcase failed");
		}
	}
   
	// Test method for Google Sign In
	@Test(priority = 3,groups= {"regression"})
	public void testGoogleSignIn() throws InterruptedException {
		try{
			logger.info("verifying the google sign in functionality");
		GoogleSignin signin = new GoogleSignin(driver);
		signin.clickSignIn();
		signin.googleSignIn();
		}
		catch(Exception e)
		{
			logger.error("google sign in testcase failed");
		}
	}
		@Test(priority = 4,groups= {"regression"})
		public void capture_errormessage() throws InterruptedException {
			try{
				logger.info("capturing the error message");
			GoogleSignin signin = new GoogleSignin(driver);
			signin.emailInput("abc@abc");
			signin.emailNext();
			signin.getErrorMessage();
			}
			catch(Exception e)
			{
				logger.error("error message capturing testcase failed");
			}
		
	}
	
	// Closing the driver after each test method
	@AfterClass(groups= {"regression","smoke"})
	public void closeDriver() {
		logger.info("browser is closed");
		Base base = new Base();
		base.quitBrowser();
	}
	
}

