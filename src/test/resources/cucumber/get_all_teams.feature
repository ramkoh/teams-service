Feature: Get All Teams
  Scenario: Get all teams
    Given User is on home page of website
    When User clicks on View Teams link
    Then Each row has name of team
    And Each row has code of player
    And Each row has View Players link

