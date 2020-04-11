package com.edu.postgrad.game.teams.selenium;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.edu.postgrad.game.teams.TeamsCICDApplication;
import com.edu.postgrad.game.teams.dto.Player;
import com.edu.postgrad.game.teams.service.PlayerService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class ViewAllPlayers {
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

    @Given("^User is on home page of the website$")
    public void user_is_on_home_page_of_the_website() throws Throwable {
        webdriver.get("http://localhost:10080/teams/");
    }

    @When("^User clicks on View Players link$")
    public void user_clicks_on_view_players_link() throws Throwable {
        webdriver.findElement(By.id("playersLink")).click();
        webdriver.findElement(By.id("viewPlayers")).click();
    }

    @Then("^Each row has first name of player$")
    public void each_row_has_first_name_of_player() throws Throwable {
        List<String> firstNames = new ArrayList<>();
        List<WebElement> firstNameColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[1]"));
        firstNameColumn.forEach(p ->firstNames.add(p.getText()));
        firstNames.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has last name of player$")
    public void each_row_has_last_name_of_player() throws Throwable {
        List<String> lastNames = new ArrayList<>();
        List<WebElement> lastNameColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[2]"));
        lastNameColumn.forEach(p -> lastNames.add(p.getText()));
        lastNames.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has Date of Birth of player$")
    public void each_row_has_date_of_birth_of_players() throws Throwable {
        List<Integer> age = new ArrayList<>();
        List<WebElement> ageColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[3]"));
        ageColumn.forEach(p -> age.add(Integer.valueOf(p.getText())));
        age.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has a Position of player$")
    public void each_row_has_position_of_player() throws Throwable {
        List<Integer> position = new ArrayList<>();
        List<WebElement> positionColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[4]"));
        positionColumn.forEach(p -> position.add(Integer.valueOf(p.getText())));
        position.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has View Delete and Add to Team links$")
    public void each_row_has_view_delete_and_add_to_team_links() throws Throwable {
        List<WebElement> viewPlayerLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[6]"));
        List<String> viewPlayer = new ArrayList<>();
        viewPlayerLink.forEach(p -> viewPlayer.add((p.getText())));
        viewPlayer.stream().forEach(Assertions::assertNotNull);

        List<WebElement> updatePlayerLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[7]"));
        List<String> updatePlayer = new ArrayList<>();
        updatePlayerLink.forEach(p -> updatePlayer.add((p.getText())));
        updatePlayer.stream().forEach(Assertions::assertNotNull);

        List<WebElement> addPlayerToTeamLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[5]"));
        List<String> addPlayerToTeam = new ArrayList<>();
        addPlayerToTeamLink.forEach(p -> addPlayerToTeam.add((p.getText())));
        addPlayerToTeam.stream().forEach(Assertions::assertNotNull);
    }
}
