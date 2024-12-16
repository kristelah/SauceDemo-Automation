Feature: User Login and Checkout

  Scenario: User logs in, adds products to cart, and proceeds to checkout
    Given User is on the login page
    When User logs in with valid credentials "standard_user" and "secret_sauce"
    Then User should be successfully logged in

    When User sorts products by price from high to low
    Then User adds products with price "$15.99" to the cart

    When User navigates to the cart
    Then User proceeds to checkout

    Then User should see the checkout summary with 2 items

    When User logs in with invalid credentials "locked_out_user" and "secret_sauce"
    Then User should see an error message
