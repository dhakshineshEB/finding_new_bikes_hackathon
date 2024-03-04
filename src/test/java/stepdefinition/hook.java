package stepdefinition;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import basePage.*;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class hook {
public static WebDriver driver;
public static Properties prop;
@BeforeAll
public static void setup() throws MalformedURLException
{
	Base b=new Base();
	prop = new Properties();

	try {
		// Loading the configuration file
		prop.load(new FileInputStream("src/main/java/config/config.properties"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	driver=b.driverSetup(prop.getProperty("browserName"));
}
@AfterAll
public static void teardown()
{
	driver.quit();
}
@AfterStep
public void takeScreenshot(Scenario scenario) {
	TakesScreenshot ts=(TakesScreenshot) driver;
	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
	scenario.attach(screenshot, "image/png",scenario.getName());
}
}
