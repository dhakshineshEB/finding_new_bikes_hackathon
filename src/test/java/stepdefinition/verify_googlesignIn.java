package stepdefinition;

import com.basePage.Base;
import com.googlesignin.GoogleSignin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class verify_googlesignIn extends Base{
	GoogleSignin signin;
	@Given("click on Sign In button")
	public void click_on_sign_in_button() {
		signin = new GoogleSignin(driver);
		signin.clickSignIn();
}

	@Then("verifying the sign in with google functionality")
	public void verifying_the_sign_in_with_google_functionality() throws InterruptedException {
		signin.googleSignIn();
	}

	@When("click on sign in with google")
	public void click_on_sign_in_with_google() throws InterruptedException {
		signin.googleSignIn();
	}

	@When("enter invalid emailID")
	public void enter_invalid_email_id() {
		signin.emailInput("abc@abc");
		signin.emailNext();
	}

	@Then("click on Next button after entering email ID and capture the error message")
	public void click_on_next_button_after_entering_email_id_and_capture_the_error_message() {
		signin.getErrorMessage();
	}




}
