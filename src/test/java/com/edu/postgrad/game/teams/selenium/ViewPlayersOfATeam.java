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
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class ViewPlayersOfATeam {
    WebDriver webdriver;

    @Before
    public void initialization() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        webdriver = new FirefoxDriver(options);
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }

    @After
    public void tearDown(){
        webdriver.close();
    }

    @Given("^User is on main page of the website$")
    public void user_is_on_main_page_of_the_website() throws Throwable {
        webdriver.get("http://localhost:10080/teams/");
    }

    @Given("^User opens Teams tab$")
    public void user_opens_Teams_tab() throws Throwable {
        webdriver.findElement(By.id("teamsLink")).click();
    }

    @Given("^Clicks on View Teams link$")
    public void clicks_on_view_teams_links() throws Throwable {
        webdriver.findElement(By.id("viewTeams")).click();
    }

    @When("^Clicks on View Players$")
    public void clicks_on_view_players() throws Throwable {
        webdriver.findElement(By.id("viewPlayers")).click();
    }

    @Then("^A list of players is shown to user$")
    public void a_list_of_players_is_shown_to_user() throws Throwable {
        String firstNameHeader = webdriver.findElement(By.id("firstName")).getText();
        Assertions.assertEquals("First Name", firstNameHeader);

        String lastNameHeader = webdriver.findElement(By.id("lastName")).getText();
        Assertions.assertEquals("Last Name", lastNameHeader);
    }
}
