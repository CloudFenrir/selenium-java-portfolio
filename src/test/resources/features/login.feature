Feature: Login
  As a customer
  I want to log in
  So that I can buy products

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Valid login opens the inventory
    When the user logs in with "standard_user" and "secret_sauce"
    Then the inventory page is displayed
    And the page title is "Products"



  @regression
  Scenario Outline: Invalid login shows an error
    When the user logs in with "<username>" and "<password>"
    Then the error message "<error>" is displayed

    Examples:
      | username        | password     | error                                                                     |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | wrong        | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |
