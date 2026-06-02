Feature: SignUpForm batch 1

  Scenario Outline: New User <username> Sign Up and Signin
    Given user selects signup
    When user <username> completes signup
    Then signup should be successful
    And user selects login
    When Registered user should sign in
    Then user should sign in successfully
    And user should Logout

    @webSamp @testCase01
    Examples:
      | username |
      | ajay     |

    @webSamp @testCase02
    Examples:
      | username |
      | vijay    |

    @webSamp @testCase03
    Examples:
      | username |
      | prajay   |

