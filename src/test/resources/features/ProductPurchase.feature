Feature: Product Purchase

  # TC-ProductPurchase-01: Verify user is able to purchase a product successfully.

  @productPurchase @TC-ProductPurchase-01
  Scenario: TC-ProductPurchase-01 Verify user is able to purchase a product successfully
    Given user selects login
    When user logs in with username "wwq221" and password "wwq221"
    And Registered user should sign in
    Then user should sign in successfully
    When user selects product "Samsung galaxy s6"
    And user adds the product to cart
    Then product added alert should be displayed
    When user selects cart
    Then cart should load with selected product
    When user places an order with home address and visa card
    Then purchase confirmation should be displayed
    And user closes the confirmation
    And user should Logout