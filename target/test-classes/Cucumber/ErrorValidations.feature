
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error validations
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "invalid email or password" message is displayed 
    
    Examples: 
      | name              | password  | 
      | saneram@gmail.com | Ram@1234 | 
      
