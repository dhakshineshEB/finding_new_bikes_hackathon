package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.basePage.Base;
import com.usedcars.UserCars;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class verify_usedcars extends Base{
	UserCars userCars;
	@Given("click on used car menu")
	public void click_on_used_car_menu() {
		userCars = new UserCars(driver);
		userCars.usedCarsMenu();
	}

	@Then("verifying the used car in chennai link")
	public void verifying_the_used_car_in_chennai_link() {
		userCars.selectChennaiUsedCars();
	}

	@When("Click on Used cars in Chennai link")
	public void click_on_used_cars_in_chennai_link() {
		userCars.selectChennaiUsedCars();
	}

	@Then("Display the list of popular models on the console")
	public void display_the_list_of_popular_models_on_the_console() throws InterruptedException, IOException {
		userCars.modelList();
	}

}
