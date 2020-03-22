
Feature: Save Player
  Scenario: User creates a player
    Given User clicks on Create Player link
    And User fills out First and Last name of player
    And User fills out Data of Birth of player
    And User fills out Position of player
    And User fills out Jersey Number of player
    When User Clicks on Create Player button
    Then Player X created message should be displayed on screen