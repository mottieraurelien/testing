Feature: Login

  Scenario Outline: Access to homepage when using right credentials
    Given the user navigated to login page
    When the user inputs a valid username <username> and its password
    And the user submits the login form
    Then the user is forwarded to homepage

    And the user closes the browser

    Examples:
      | username |
      | student  |
      | student  |