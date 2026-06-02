Feature: SignUpForm Batch2

  Scenario Outline: New User <username> Sign Up and Signin
    Given user selects signup
    When user <username> completes signup
    Then signup should be successful
    And user selects login
    When Registered user should sign in
    Then user should sign in successfully
    And user should Logout

    @webSamp @testCase04
    Examples:
      | username |
      | hello    |

    @webSamp @testCase05
    Examples:
      | username |
      | world    |

    @webSamp @testCase06
    Examples:
      | username |
      | welcome  |

