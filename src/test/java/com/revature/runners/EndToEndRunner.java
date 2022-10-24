package com.revature.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features/1authentication.feature","src/test/resources/features/2claim-submission.feature","src/test/resources/features/3grade-submission.feature","src/test/resources/features/4approve-claim.feature"}, glue={"com.revature.steps"})

public class EndToEndRunner {
    public static WebDriver driver = null;
    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

}
