package com.upcomingbikes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.basePage.Base;
import com.basePage.Wait_;
import com.utilityfiles.excelutilities;


public class UpcomingBikes extends Base{
	WebDriver driver;
	double price;
	String info;
	double limit=400000.0;
	public String filePath = null;
	boolean flag;
	excelutilities ex;
	
	//Constructor to initialize WebElements
	
	public UpcomingBikes(WebDriver driver) {
		PageFactory.initElements(driver, this);  //to initialize the web elements present in the UpcomingBikes class.
		this.driver = driver;
	}
	
	// WebElements for Upcoming Bikes Page
	
	@FindBy(xpath= "//a[contains(text(),'New Bikes')]")
	WebElement newBikesMenu;
	
	@FindBy(xpath = "//span[contains(text(),'Upcoming Bikes')]")
	WebElement upcomingBikes;
	
	@FindBy(id = "makeId")
	WebElement manufacturer;
	
	@FindBy(xpath = "//*[@id=\"modelList\"]/li//span[contains(text(),'View More Bikes')]")
	WebElement viewMore;
	
	@FindBy(xpath = "//*[@id='carModels']/ul")
	WebElement hondaBikeModels;
	@FindBy(xpath="//*[text()='Upcoming Bikes in India ']")
	WebElement heading;
	
	int count = 0;
	
	// Hover over 'New Bikes' menu to view 'Upcoming Bikes'
	
	public void newBikesMenu() throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(newBikesMenu).perform(); 
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/NewBikesMenu.png";
	}
	
	// Select 'Upcoming Bikes'
	
	public void selectUpcomingBike() throws Exception {

		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/UpcomingBikeMenu.png";
		upcomingBikes.click(); // Click 'Upcoming Bikes'
	}
	public boolean capturing_upcomingbikes_heading() {
		if(heading.getText().equalsIgnoreCase("Upcoming Bikes in India "))
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	
	// Select 'Honda' as manufacturer
	
	public void selectManufacturer() throws Exception {
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/ManufacturerDropdown.png";
		
		Wait_ wait = new Wait_();
		wait.waitExplicit(30,manufacturer,driver);
		
		Select select = new Select(manufacturer);
		select.selectByVisibleText("Honda");  // Select 'Honda'
		
	}
     
	// Click 'View More Bikes'
	
	public void viewMoreBikes() throws Exception {
		
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewMore);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Get bike models, prices, and expected launch date
	
	public void bikeModels() throws Exception {
		
		// Get text of all bike models available on the page
		String bikeModels = hondaBikeModels.getText();
		
		// Split the text by new line character and store it in an ArrayList
		ArrayList<String> bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements, bikeModels.split("\n"));
        
		// Initialize ArrayLists to store names, launch dates and prices of bikes
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		ArrayList<String> priceList = new ArrayList<String>();
		String[] arr = null;
		
		// Iterate through the list of bike models and extract the required information
		for (int i = 0; i < bikeModelsElements.size(); i++) {
			String s = bikeModelsElements.get(i);
			if (s.contains("Honda")) {
				nameList.add(s);
			}
			if (s.contains("Rs. ")) {
				arr = s.split(" ");
				priceList.add(arr[1]);
			}
			if (s.contains("Launch Date : ")) {
				dateList.add(s);
			}
		}
		
		// Wait for page elements to load
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
		// Initialize an ArrayList to store upcoming bikes with price less than 4 Lakhs
		ArrayList<String> upcomingBikes = new ArrayList<String>();
		if(nameList.size()>0) {
			for (int i = 0; i <nameList.size(); i++) {
			String temp = nameList.get(i);
			
			// Convert bike price to a double value
			if(priceList.get(i).contains("."))
			{
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // parse numbers in French-style format
			Number number = format.parse(priceList.get(i));
			 price = number.doubleValue();
			if (Double.compare(price, 4d) < 0) 
			 info = temp + "  " + priceList.get(i) + " Lakh  " + dateList.get(i);
			if (info.contains(temp)) {
				upcomingBikes.add(info);
			}
			 }
			
			else 
			{
				double amount1=Double.parseDouble(priceList.get(i).replace(",",""));
			if(amount1<limit)
			{
				 info = temp + "  " + priceList.get(i) + " thousand " + dateList.get(i);
				 if (info.contains(temp)) {
						upcomingBikes.add(info);
					}
			}
			}
		  }
		}
		
		
		// Print the list of upcoming bikes to the console
		System.out.println("Upcoming Honda Bikes Below 4 Lakhs are as follows:");
		for (int i = 0; i < upcomingBikes.size(); i++) {
			 ex=new excelutilities();
		     ex.setcelldata("sheet1",upcomingBikes.get(i),i,0);
			System.out.println(upcomingBikes.get(i));
		}

	}


}
