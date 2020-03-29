package com.edu.postgrad.game.teams.selenium;

import com.edu.postgrad.game.teams.TeamsCICDApplication;
import cucumber.api.PendingException;
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
public class ViewHomeTest {
    WebDriver webdriver = new FirefoxDriver();

    @Before
    public void initialization() {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }

    @After
    public void tearDown(){
        webdriver.close();
    }

    @When("^User opens link of Home Page in a browser$")
    public void user_opens_link_of_Home_Page_in_a_browser() throws Throwable {
        webdriver.get("http://localhost:10080/teams/");
    }

    @Then("^Home Page contains Players tab$")
    public void home_Page_contains_Players_tab() throws Throwable {
        String playersLinkText = webdriver.findElement(By.id("playersLink")).getText();
        Assertions.assertEquals(playersLinkText, "Players");
    }

    @Then("^Home Page contains Teams tab$")
    public void home_Page_contains_Teams_tab() throws Throwable {
        String playersLinkText = webdriver.findElement(By.id("playersLink")).getText();
        Assertions.assertEquals(playersLinkText, "Players");
    }

    @Then("^Players tab contains like to Add Player$")
    public void players_tab_contains_like_to_Add_Player() throws Throwable {
        webdriver.findElement(By.id("playersLink")).click();
        String playersLinkText = webdriver.findElement(By.id("addPlayer")).getText();
        Assertions.assertEquals(playersLinkText, "Add Player");
    }

    @Then("^Players tab contains like to View Players$")
    public void players_tab_contains_like_to_View_Players() throws Throwable {
        webdriver.findElement(By.id("playersLink")).click();
        String playersLinkText = webdriver.findElement(By.id("viewPlayers")).getText();
        Assertions.assertEquals(playersLinkText, "View Players");
    }

    @Then("^Teams tab contains like to Add Team$")
    public void teams_tab_contains_like_to_Add_Team() throws Throwable {
        webdriver.findElement(By.id("teamsLink")).click();
        String playersLinkText = webdriver.findElement(By.id("addTeam")).getText();
        Assertions.assertEquals(playersLinkText, "Add Team");
    }

    @Then("^Teams tab contains like to View Teams$")
    public void teams_tab_contains_like_to_View_Teams() throws Throwable {
        webdriver.findElement(By.id("teamsLink")).click();
        String playersLinkText = webdriver.findElement(By.id("viewTeams")).getText();
        Assertions.assertEquals(playersLinkText, "View Teams");
    }


}
