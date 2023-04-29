@hiclinic-396 @booking
Feature: Login
  @regression
  Scenario: Verify Navigate to Login by hitting login button
    Given the user is at "home" page
    When the user clicks on "login" on Home Page
    Then the user is at "Login" page