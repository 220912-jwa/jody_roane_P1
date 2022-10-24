package com.revature.steps;

import com.revature.runners.EndToEndRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GradeSubmissionSteps {
    WebDriver driver = EndToEndRunner.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    @Given("The associate is logged in as associate.")
    public void the_associate_is_logged_in_as_associate() {
        System.out.println("The associate is logged in as associate.");
        String title = driver.getTitle();
        if(title.equals("Home"))
        {
            wait.until(ExpectedConditions.titleIs("Home"));
        }
        else
        {
            driver.navigate().to("http://localhost:63342/jody_roane_P1/ersWebsite/portal.html");
            WebElement username = driver.findElement(By.id("input 1"));
            username.sendKeys("employee1");
            WebElement password = driver.findElement(By.id("input 2"));
            password.sendKeys("password1");
            WebElement login = driver.findElement(By.id("button 1"));
            login.click();
            wait.until(ExpectedConditions.titleIs("Home"));
        }
    }

    @When("The associate clicks on the get pending claims button.")
    public void the_associate_clicks_on_the_get_pending_claims_button() {
        System.out.println("The associate clicks on the get pending claims button.");
        WebElement button = driver.findElement(By.id("get pending claims button"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pending claims table div")));
    }

    @When("The associate clicks the select this pending claim button next to the first claim.")
    public void the_associate_clicks_the_select_this_pending_claim_button_next_to_the_first_claim() {
        System.out.println("The associate clicks the select this pending claim button next to the first claim.");
        WebElement button = driver.findElement(By.xpath("/html/body/div[9]/table/tbody[2]/tr[1]/button"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("final grade form")));
    }

    @When("The associate selects the {string} passing grade option.")
    public void the_associate_selects_the_passing_grade_option(String string) {
        System.out.println("The associate selects the {string} passing grade option.");
        WebElement input = driver.findElement(By.id("passing grade selector"));
        input.sendKeys(string);
    }

    @When("The associate selects the {string} final grade option.")
    public void the_associate_selects_the_final_grade_option(String string) {
        System.out.println("The associate selects the {string} final grade option.");
        WebElement input = driver.findElement(By.id("final grade selector"));
        input.sendKeys(string);
    }

    @When("The associate clicks the submit final grade button.")
    public void the_associate_clicks_the_submit_final_grade_button() {
        System.out.println("The associate clicks the submit final grade button.");
        WebElement input = driver.findElement(By.id("update button 2"));
        input.click();
    }

    @Then("The associate should see an alert that says {string}.")
    public void the_associate_should_see_an_alert_that_says(String string) {
        System.out.println("The associate should see an alert that says {string}.");
        wait.until(ExpectedConditions.alertIsPresent());
        String alert = driver.switchTo().alert().getText();
        Assertions.assertEquals(string,alert);
        driver.switchTo().alert().accept();
        WebElement link = driver.findElement(By.linkText("Logout"));
        link.click();
        wait.until(ExpectedConditions.titleIs("Employee Reimbursement Portal"));
    }
}
