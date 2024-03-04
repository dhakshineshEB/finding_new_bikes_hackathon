package smoke;
import basePage.Base;
import googlesignin_functionality.GoogleSignin;
import upcomingbikes_functionality.UpcomingBikes;
import usedcars_functionality.UserCars;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SmokeTest {
	public static WebDriver driver;
	@Parameters({"browser"})
	@BeforeClass(groups= {"smoke","regression"})
	public void setup(String browsername) throws MalformedURLException {
		Base base = new Base();
		driver = base.driverSetup(browsername);
	}
    
	@Test(priority = 1,groups= {"smoke"})
	public void newbikemenu() throws Exception{
		
		UpcomingBikes up = new UpcomingBikes(driver);
		up.newBikesMenu(); // Clicks on the New Bikes menu
		System.out.println("new bike menu test passed");
		
	}
	@Test(priority = 2,groups= {"smoke"})
	public void upComingBikes() throws Exception{
		
		UpcomingBikes up = new UpcomingBikes(driver);
		up.selectUpcomingBike(); // Clicks on the Upcoming Bikes submenu
		System.out.println("Upcoming Bikes submenu test passed");
	}
	
	@Test(priority = 3,groups= {"smoke"})
	public void usedCars() throws InterruptedException {
		
		UserCars userCars = new UserCars(driver);
		userCars.usedCarsMenu(); // Clicks on the Find Used Cars menu
		System.out.println("Find Used Cars submenu test passed");
	}
   
	@Test(priority =4,groups= {"smoke"})
	public void testGoogleSignIn() throws InterruptedException {
		
		GoogleSignin signin = new GoogleSignin(driver);
		signin.clickSignIn(); // Clicks on the Sign In button
		System.out.println("Sign In Button functionality passed");
	}
	
	@AfterClass(groups= {"smoke","regression"})
	public void closeDriver() {
		
		Base base = new Base();
		base.quitBrowser();
	}
	

}
