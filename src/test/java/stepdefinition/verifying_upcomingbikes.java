package stepdefinition;

import org.testng.Assert;

import basePage.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import upcomingbikes_functionality.UpcomingBikes;

public class verifying_upcomingbikes extends Base{
	UpcomingBikes up;
	@Given("Click on the new Bikes menu")
	public void click_on_the_new_bikes_menu() throws Exception {
	    up = new UpcomingBikes(driver);
		up.newBikesMenu();
	}

	@Then("verifying the upcoming bike submenu by clicking the upcoming Bikes")
	public void verifying_the_upcoming_bike_submenu_by_clicking_the_upcoming_bikes() throws Exception {
		up.selectUpcomingBike();
	}

	@When("click on the upcoming bikes")
	public void click_on_the_upcoming_bikes() throws Exception {
		up.selectUpcomingBike();
	}

	@When("select the manufacturer as honda")
	public void select_the_manufacturer_as_honda() throws Exception {
		up.selectManufacturer();
		up.viewMoreBikes();
	}

	@Then("Get bike models, prices, and expected launch date less than four lakhs")
	public void get_bike_models_prices_and_expected_launch_date_less_than_four_lakhs() throws Exception {
		up.bikeModels();
		//boolean a=up.capturing_upcomingbikes_heading();
		//Assert.assertEquals(a, true);
	}






}
