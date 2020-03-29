Feature: Get All Players
  Scenario: Get all players
    Given User is on home page of the website
    When User clicks on View Players link
    Then Each row has first name of player
    And Each row has last name of player
    And Each row has Date of Birth of player
    And Each row has a Position of player
    And Each row has View Delete and Add to Team links

