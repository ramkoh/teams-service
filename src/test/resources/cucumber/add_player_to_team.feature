Feature: Add player to team
Scenario: User adds players to a team
Given User is on home page
And User has opened Players tab
And Open View Players link
And Each row has Add to Team link
And User clicks on Add to Team link
And User fills out a valid team name
When User clicks on Add button
Then User is shown a successful message on screen that - Player X is added to team to Y
