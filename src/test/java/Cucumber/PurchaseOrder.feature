
@tag
Feature: Purchase order from the Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Webpage
	
  @Regression
  Scenario Outline: Positive Test of Submitting order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and Ubmit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name              | password  | productName |
      | saneram@gmail.com | Ram@12345 | ZARA COAT 3 |
      
