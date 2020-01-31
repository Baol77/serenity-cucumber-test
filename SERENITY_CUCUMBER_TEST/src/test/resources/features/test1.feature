Feature: I am able to test basic functionalities on the Itecor homepage

  Scenario: I want access the Itecor Homepage
    Given As anonymous user I go to the Itecor Homepage
    When I click on the hamburger button
    Then I am able to see the "about us" section