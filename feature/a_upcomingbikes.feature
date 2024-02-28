Feature: Finding New bikes
 @smoke
  Scenario: verifying the functionality of upcomingbikes submenu
   Given Click on the new Bikes menu
   Then verifying the upcoming bike submenu by clicking the upcoming Bikes
@regression
  Scenario: Displaying the upcoming bikes in the console
    Given  Click on the new Bikes menu
    When click on the upcoming bikes 
    And select the manufacturer as honda
    Then Get bike models, prices, and expected launch date less than four lakhs

    