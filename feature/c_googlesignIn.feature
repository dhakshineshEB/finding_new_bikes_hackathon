Feature: verifying the google sign in feature

  @smoke
  Scenario: verifying the google signin functionality
    Given click on Sign In button
    Then verifying the sign in with google functionality
    

  @regression
  Scenario:  Trying to Login with google and giving invalid account details & capturing the error message
    Given click on Sign In button 
    When click on sign in with google
    And enter invalid emailID
    Then click on Next button after entering email ID and capture the error message

  
