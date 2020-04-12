package com.edu.postgrad.game.teams.selenium;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber",
        glue = "com.edu.postgrad.game.teams.selenium",
        format = {"json:target/cucumber.json"})
public class StepsDefTest {


}
