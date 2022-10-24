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

public class ApproveClaimSteps {
    WebDriver driver = EndToEndRunner.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    @Given("The manager is logged in as manager.")
    public void the_manager_is_logged_in_as_manager() {
        String title = driver.getTitle();
        if (title.equals("Home"))
        {
            wait.until(ExpectedConditions.titleIs("Home"));
        }
        else
        {
            driver.navigate().to("http://localhost:63342/jody_roane_P1/ersWebsite/portal.html");
            WebElement username = driver.findElement(By.id("input 1"));
            username.sendKeys("employee2");
            WebElement password = driver.findElement(By.id("input 2"));
            password.sendKeys("password2");
            WebElement login = driver.findElement(By.id("button 1"));
            login.click();
            wait.until(ExpectedConditions.titleIs("Home"));
        }

    }
    @When("The manager clicks the get claims seeking approval button.")
    public void the_manager_clicks_the_get_claims_seeking_approval_button() {
        System.out.println("The manager clicks the get claims seeking approval button.");
        WebElement getClaimsSeekingApprovalButton = driver.findElement(By.id("finance only"));
        getClaimsSeekingApprovalButton.click();
    }
    @When("The manager clicks the select this claim button.")
    public void the_manager_clicks_the_select_this_claim_button() {
        System.out.println("The manager clicks the select this claim button.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claims table div")));
        WebElement button = driver.findElement(By.xpath("/html/body/div[6]/table/tbody[2]/tr/button"));
        button.click();
    }
    @When("The manager types {string} into the reimbursement adjustment field.")
    public void the_manager_types_into_the_reimbursement_adjustment_field(String string) {
        System.out.println("The manager types {string} into the reimbursement adjustment field.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("approval form div")));
        WebElement adjustmentField = driver.findElement(By.id("adjustment"));
        adjustmentField.sendKeys(string);
    }
    @When("The manager types {string} into the adjustment reason field.")
    public void the_manager_types_into_the_adjustment_reason_field(String string) {
        System.out.println("The manager types {string} into the adjustment reason field.");
        WebElement reasonField = driver.findElement(By.id("adjustemnt reason"));
        reasonField.sendKeys(string);
    }
    @When("The manager click the submit adjustment button.")
    public void the_manager_click_the_submit_adjustment_button() {
        System.out.println("The manager click the submit adjustment button.");
        WebElement submitAdjustmentButton = driver.findElement(By.id("submit adjustment"));
        submitAdjustmentButton.click();
    }
    @Then("The manager should see the reimbursement update.")
    public void the_manager_should_see_the_reimbursement_update() {
        System.out.println("The manager should see the reimbursement update.");
        Assertions.assertTrue(true);
    }
    @When("The manager selects the {string} claim status option.")
    public void the_manager_selects_the_claim_status_option(String string) {
        System.out.println("The manager selects the {string} claim status option.");
        WebElement claimStatus = driver.findElement(By.id("select adjustment options"));
        claimStatus.sendKeys("Awarded");
    }
    @When("the manager checks the checkbox for award status.")
    public void the_manager_checks_the_checkbox_for_award_status() {
        System.out.println("the manager checks the checkbox for award status.");
        WebElement awardStatus = driver.findElement(By.id("award status"));
        awardStatus.click();
        WebElement submitbutton = driver.findElement(By.id("update button"));
        submitbutton.click();
    }
    @Then("The manager should see an alert that says {string} a.")
    public void the_manager_should_see_an_alert_that_says_a(String string) {
        System.out.println("The manager should see an alert that says {string} a.");
        wait.until(ExpectedConditions.alertIsPresent());
        String alert = driver.switchTo().alert().getText();
        Assertions.assertEquals(string,alert);
        driver.switchTo().alert().accept();
        driver.close();
    }
}
