@All
Feature: Parabank Registration

  @positive @register
  Scenario: Success register
    Given User is on Parabank homepage
    When User click register link button
    Then User is in register page
    When User input name
    And User input address detail
    And User input city and state
    And User input ZIP code
    And User input phone number
    And User input SSN
    And User fill valid username and password
    And User input password confirmation
    When User click Register button
    Then User regist successfully

  @negative @register
  Scenario: Failed register - Missmatch Password
    Given User is on Parabank homepage
    When User click register link button
    Then User is in register page
    When User input name
    And User input address detail
    And User input city and state
    And User input ZIP code
    And User input phone number
    And User input SSN
    And User fill valid username and password
    And User input password confirmation
    And User input invalid password confirmation
    When User click Register button
    Then User get error password did not match
