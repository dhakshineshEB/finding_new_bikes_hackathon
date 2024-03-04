package basePage;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	
	String os="windows";
	String br;
	// Method to set up the WebDriver instance based on the browser specified in the
	// configuration file

	public WebDriver driverSetup(String browserName) throws MalformedURLException {

      prop = new Properties();
      try {
			// Loading the configuration file
			prop.load(new FileInputStream("src/main/java/config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
      if(prop.getProperty("execution").equalsIgnoreCase("remote"))
  	{
  		DesiredCapabilities cap=new DesiredCapabilities();
  		if(os.equalsIgnoreCase("windows"))
  		{
  			cap.setPlatform(Platform.WIN11);
  		}
  		else if(os.equalsIgnoreCase("mac"))
  		{
  			cap.setPlatform(Platform.MAC);
  		}
  		else
  		{
  			System.out.print("no matching os");
  		}
  		switch(browserName.toLowerCase())
  		{
  		case "chrome":cap.setBrowserName("chrome");break;
  		case "edge":cap.setBrowserName("MicrosoftEdge");break;
  		default:System.out.println("no matching browser");
  		}
  		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
  		 driver=new Augmenter().augment(driver);
  	}
      else if(prop.getProperty("execution").equalsIgnoreCase("local"))
      {

		System.out.println(browserName);

		// Checking which browser is specified in the configuration file and creating
		// the corresponding WebDriver instance
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("no matching browser");
			
		}
      }
      driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Navigating to the URL specified in the static variable
		driver.get(prop.getProperty("appUrl"));

		return driver;
}

	// Method to quit the browser

	public void quitBrowser() {
		driver.quit();
	}

	public String captureScreen1(String tname) {
		// TODO Auto-generated method stub
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot =(TakesScreenshot)driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

		}

	

}
