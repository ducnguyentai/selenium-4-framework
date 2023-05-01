@hiclinic-396 @booking
Feature: Login

  @regression
  Scenario: Verify Navigate to Login by hitting login button
    Given the user is already created in system
      | User Type |
      | VIP       |
    And the user is at "Home" page
    When the user clicks on "login" on Home Page
    Then the user is at "login" page
    When the user inputs email