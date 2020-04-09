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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsCICDApplication.class)
@ContextConfiguration(classes = TeamsCICDApplication.class)
public class ViewAllTeams {

    WebDriver webdriver = new FirefoxDriver();

    @Before
    public void initialization() { System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }

    @After
    public void tearDown(){
        webdriver.close();
    }
    @Given("^User is on home page of website$")
    public void user_is_on_home_page_of_website() throws Throwable {
        webdriver.get("http://localhost:10080/teams/");
    }
    @When("^User clicks on View Teams link$")
    public void user_clicks_on_View_Teams_link() throws Throwable {
        webdriver.findElement(By.id("teamsLink")).click();
        webdriver.findElement(By.id("viewTeams")).click();
    }

    @Then("^Each row has name of team$")
    public void each_row_has_name_of_team() throws Throwable {
        List<String> names = new ArrayList<>();
        List<WebElement> nameColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[1]"));
        nameColumn.forEach(p ->names.add(p.getText()));
        names.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has code of player$")
    public void each_row_has_code_of_player() throws Throwable {
        List<String> codes = new ArrayList<>();
        List<WebElement> codeColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[2]"));
        codeColumn.forEach(p ->codes.add(p.getText()));
        codes.stream().forEach(Assertions::assertNotNull);
    }

    @Then("^Each row has View Players link$")
    public void each_row_has_View_Players_link() throws Throwable {
        List<WebElement> viewPlayersLink = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[3]"));
        List<String> viewPlayers = new ArrayList<>();
        viewPlayersLink.forEach(p -> viewPlayers.add((p.getText())));
        viewPlayers.stream().forEach(Assertions::assertNotNull);
    }


}
