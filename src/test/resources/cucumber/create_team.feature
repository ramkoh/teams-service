Feature: Save Team
Scenario: User creates a team
Given User clicks on Create Team link
And User fills out name of the team
And User fills out Short name of the team
When User Clicks on Create Team button
Then Team X created message should be displayed on screen
