@tag
  Feature: Error Validation

    @ErrorValidation
    Scenario Outline: Checkout Error validation
      Given I landed on ecommerce page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | name                         | password       |  |  |
        | marko.gavrilovic88@yahoo.com | 3423Evepas.111 |  |  |