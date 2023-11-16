@tag
  Feature: Submit order from Ecommerce Website


    Background:
      Given I landed on ecommerce page

    @Regression
    Scenario Outline: Positive tests of submitting order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to Cart
      When Checkout <productName> and submit the order and <contryName>
      Then "THANKYOU FOR THE ORDER." message is displayed on confimation page

      Examples:
        | name                         | password    | productName     | contryName |
        | marko.gavrilovic88@yahoo.com | 3423Evepas. | ZARA COAT 3     | Canada     |
        | marko.gavrilovic88@yahoo.com | 3423Evepas. | ADIDAS ORIGINAL | Canada     |