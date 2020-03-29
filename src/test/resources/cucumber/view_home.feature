Feature:Home Page

  Scenario: User navigates to Home Page of the site
    When User opens link of Home Page in a browser
    Then Home Page contains Players tab
    And Home Page contains Teams tab
    And Players tab contains like to Add Player
    And Players tab contains like to View Players
    And Teams tab contains like to Add Team
    And Teams tab contains like to View Teams