package com.edu.postgrad.game.teams.selenium;

import java.util.ArrayList;
import java.util.List;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class AddPlayerToTeam {
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

    @Given("^User is on home page$")
    public void user_is_on_home_page() throws Throwable {
        webdriver.get("http://localhost:10080/teams/");
    }


    @Given("^User has opened Players tab$")
    public void user_has_opened_Players_tab() throws Throwable {
        webdriver.findElement(By.id("playersLink")).click();
    }

    @Given("^Open View Players link$")
    public void open_View_Players_link() throws Throwable {
        webdriver.findElement(By.id("viewPlayers")).click();
    }

    @Given("^Each row has Add to Team link$")
    public void each_row_has_Add_to_Team_link() throws Throwable {
        List<WebElement> addPlayerToTeamLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[5]"));
        List<String> addPlayerToTeam = new ArrayList<>();
        addPlayerToTeamLink.forEach(p -> addPlayerToTeam.add((p.getText())));
        addPlayerToTeam.stream().forEach(Assertions::assertNotNull);
    }

    @Given("^User clicks on Add to Team link$")
    public void user_clicks_on_Add_to_Team_link() throws Throwable {
        List<WebElement> addPlayerToTeamLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[5]"));
        addPlayerToTeamLink.get(0).click();
    }

    String team = "Russia";
    @Given("^User fills out a valid team name$")
    public void user_fills_out_a_valid_team_name() throws Throwable {
        Select teams = new Select(webdriver.findElement(By.id("tm")));
        teams.selectByVisibleText(team);
    }

    @When("^User clicks on Add button$")
    public void user_clicks_on_Add_button() throws Throwable {
        webdriver.findElement(By.id("add_player_to_team_btn")).click();
    }

    @Then("^User is shown a successful message on screen that - Player X is added to team to Y$")
    public void user_is_shown_a_successful_message_on_screen_that_Player_X_is_added_to_team_to_Y() throws Throwable {
        String notificationMessage = webdriver.findElement(By.id("notification_msg")).getText();
        Assertions.assertEquals(notificationMessage, String.format("Player %s added to team %s successfully", "Lionel", team));
    }

}
