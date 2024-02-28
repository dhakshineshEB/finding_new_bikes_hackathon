package com.googlesignin;


import com.basePage.Wait_;
import com.utilityfiles.CaptureScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


public class GoogleSignin {
      
	WebDriver driver;
	public String filePath = null;
	CaptureScreenshot c;
     
	//Constructor to initialize the web driver and page objects
	
	 public GoogleSignin(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        driver.get("https://www.zigwheels.com/");
	    }
	
	 // page objects
	 
	 @FindBy(xpath = "//span[normalize-space()='Google']")
	 WebElement google;
	 
	 @FindBy(xpath ="//*[@id='forum_login_title_lg']")
	 WebElement signInButton;
	 
	 @FindBy(id = "identifierId")
	 WebElement email;
	 
	 @FindBy(xpath = "//span[contains(text(),'Next')]")
	 WebElement emailNextButton;
	 
	 @FindBy(xpath = "//div[@class='o6cuMc Jj6Lae' and contains(text(),'Enter a valid email or phone number')]")
	 WebElement errorMessage;
	 
	 @FindBy(id = "continue")
	 WebElement continueWithGoogle;
	
	// method to click on Sign In button
	 
	public void clickSignIn() {
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/SignButton.png";
		signInButton.click();	
		c=new CaptureScreenshot();
		c.captureTestScreenshot(driver, filePath);
	}
	
	// method to sign in with Google
	
	public void googleSignIn() throws InterruptedException {
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/GoogleSignInButton.png";
		
		Wait_ wait = new Wait_();
		wait.waitExplicit(30,google,driver);
		c.captureTestScreenshot(driver, filePath);
		google.click();
	}
     
	// method to enter invalid email ID
	
    public void emailInput(String emailId) {
		
		//new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
		
      try {
		// Get all window handles
		Set<String> windows = driver.getWindowHandles();
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
	    
		// Initialize an iterator for the window handles
		Iterator<String> iterator = windows.iterator();
		
		// Get the handle of the parent window
		String parent = iterator.next();
		
		// Get the handle of the child window
		String child = iterator.next();
		
		driver.switchTo().window(child);
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/EmailIdField.png";
		email.sendKeys(emailId);
		
	   } catch (NoSuchElementException e) {

         System.err.println("Exception occurred: " + e.getMessage());
         
        // Get all window handles
 		Set<String> windows = driver.getWindowHandles();
 		
 		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
 	    
 		// Initialize an iterator for the window handles
 		Iterator<String> iterator = windows.iterator();
 		
 		// Get the handle of the parent window
 		String parent = iterator.next();
 		
 		// Get the handle of the child window
 		String child = iterator.next();
 		
 		driver.switchTo().window(child);
 		
 		filePath = "C:\\Users\\2304055\\eclipse-workspace\\Hackathon-Project-IdentifyNewBikes-main\\Hackathon-Project-IdentifyNewBikes-main\\HackathonProjectIdentify\\Screenshots\\GoogleSignIn.png";
 		email.sendKeys(emailId);
      }
    }
	
    // method to click on Next button after entering email ID
    
    public void emailNext() {
		
		Actions action = new Actions(driver);
		action.moveToElement(emailNextButton).click().perform();
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/NextButton.png";
		c.captureTestScreenshot(driver, filePath);
		
	}
    
    // method to capture the error message
    
    public void getErrorMessage() {
		
    	Wait_ wait = new Wait_();
		wait.waitExplicit(30,errorMessage,driver);
		
		String message= errorMessage.getText();
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/ErrorMessage.png";
		c.captureTestScreenshot(driver, filePath);
		
		System.out.println(message);
	}

}
