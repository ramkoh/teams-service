package com.edu.postgrad.game.teams.selenium;

import com.edu.postgrad.game.teams.TeamsCICDApplication;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class CreateTeamTest {

    WebDriver webdriver = new FirefoxDriver();

    private String teamName = "Ireland";

    @Before
    public void initialization() {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }

    @After
    public void tearDown(){
        webdriver.close();
    }

    @Given("^User clicks on Create Team link$")
    public void user_clicks_on_Create_Team_link() throws Throwable {
        webdriver.get("http://localhost:10080/teams/team");
    }

    @Given("^User fills out name of the team$")
    public void user_fills_out_name_of_the_team() throws Throwable {
       webdriver.findElement(By.id("name")).sendKeys(teamName);
    }

    @Given("^User fills out Short name of the team$")
    public void user_fills_out_Short_name_of_the_team() throws Throwable {
        webdriver.findElement(By.id("code")).sendKeys("IRE");
    }


    @When("^User Clicks on Create Team button$")
    public void user_Clicks_on_Create_Team_button() throws Throwable {
        webdriver.findElement(By.id("save_team_btn")).click();
    }

    @Then("^Team X created message should be displayed on screen$")
    public void team_X_created_message_should_be_displayed_on_screen() throws Throwable {

        String notificationMessage = webdriver.findElement(By.id("notification_msg")).getText();
        Assertions.assertEquals(notificationMessage, String.format("Team %s created successfully", teamName));
    }

}
