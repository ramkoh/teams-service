package com.edu.postgrad.game.teams.selenium;

import com.edu.postgrad.game.teams.TeamsCICDApplication;
import com.edu.postgrad.game.teams.service.PlayerService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class CreatePlayerTest {
 
    WebDriver webdriver = new FirefoxDriver();

    private final String firstName = "Parii";
    private final String lastName = "Kohli";

    @Before
    public void initialization() {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }

    @After
    public void tearDown(){
        webdriver.close();
    }

    @Given("^User clicks on Create Player link$")
    public void user_clicks_on_Create_Player_link() throws Throwable {
        webdriver.get("http://localhost:10080/teams/player");
    }

    @Given("^User fills out First and Last name of player$")
    public void user_fills_out_First_and_Last_name_of_player() throws Throwable {
       webdriver.findElement(By.id("first_name")).sendKeys(firstName);
        webdriver.findElement(By.id("last_name")).sendKeys("Kohli");
    }

    @Given("^User fills out Data of Birth of player$")
    public void user_fills_out_Data_of_Birth_of_player() throws Throwable {
        webdriver.findElement(By.id("dob")).sendKeys("1990-07-17");
    }

    @Given("^User fills out Position of player$")
    public void user_fills_out_Position_of_player() throws Throwable {
        webdriver.findElement(By.id("GOAL_KEEPER")).click();
    }

    @Given("^User fills out Jersey Number of player$")
    public void user_fills_out_Jersey_Number_of_player() throws Throwable {
        webdriver.findElement(By.id("jerseyNumber")).sendKeys("10");
    }

    @When("^User Clicks on Create Player button$")
    public void user_Clicks_on_Create_Player_button() throws Throwable {
        webdriver.findElement(By.id("save_player_btn")).click();
    }

    @Then("^Player X created message should be displayed on screen$")
    public void player_X_created_message_should_be_displayed_on_screen() throws Throwable {

        String notificationMessage = webdriver.findElement(By.id("notification_msg")).getText();
        Assertions.assertEquals(notificationMessage, String.format("Player %s %s created successfully", firstName, lastName));
    }

}
