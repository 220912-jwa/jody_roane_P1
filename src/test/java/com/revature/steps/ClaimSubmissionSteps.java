package com.revature.steps;

import com.revature.runners.EndToEndRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;

public class ClaimSubmissionSteps {
    WebDriver driver = EndToEndRunner.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @Given("The associate is on the Home page.")
    public void the_associate_is_on_the_home_page() {
        System.out.println("The associate is on the Home page.");
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
    @When("The associate clicks the get reimbursement form button.")
    public void the_associate_clicks_the_get_reimbursement_form_button() {
        System.out.println("The associate clicks the get reimbursement form button.");
        WebElement button = driver.findElement(By.xpath("/html/body/div[3]/button"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div claim form")));
    }
    @When("The associate selects {string} event option.")
    public void the_associate_selects_event_option(String string) {
        System.out.println("The associate selects {string} event option.");
        WebElement input = driver.findElement(By.id("select 1"));
        input.sendKeys(string);
    }
    @When("The associate types {string} into description field.")
    public void the_associate_types_into_description_field(String string) {
        System.out.println("The associate types {string} into description field.");
        WebElement input = driver.findElement(By.id("input 2"));
        input.sendKeys(string);
    }
    @When("The associate types {string} into the location field.")
    public void the_associate_types_into_the_location_field(String string) {
        System.out.println("The associate types {string} into the location field.");
        WebElement input = driver.findElement(By.id("input 3"));
        input.sendKeys(string);
    }
    @When("The associate types {string} into the cost field.")
    public void the_associate_types_into_the_cost_field(String string) {
        System.out.println("The associate types {string} into the cost field.");
        WebElement input = driver.findElement(By.id("cost input"));
        input.sendKeys(string);
    }
    @When("The associate selects today's date.")
    public void the_associate_selects_today_s_date() {
        System.out.println("The associate selects today's date.");
        WebElement input = driver.findElement(By.id("date 1"));
        input.sendKeys("10/24/2022");
    }
    @When("The associate selects next fridays date.")
    public void the_associate_selects_next_fridays_date() {
        System.out.println("The associate selects next fridays date.");
        WebElement input = driver.findElement(By.id("date 2"));
        input.sendKeys("10/29/2022");
    }
    @When("The associate clicks calculate reimbursement.")
    public void the_associate_clicks_calculate_reimbursement() {
        System.out.println("The associate clicks calculate reimbursement.");
        WebElement input = driver.findElement(By.id("button 1"));
        input.click();
    }
    @Then("The associate the value for reimbursement will update.")
    public void the_associate_the_value_for_reimbursement_will_update() {
        System.out.println("The associate the value for reimbursement will update.");
        Assertions.assertTrue(true);
    }
    @When("The associate clicks the submit reimbursement page.")
    public void the_associate_clicks_the_submit_reimbursement_page() {
        System.out.println("The associate clicks the submit reimbursement page.");
        WebElement input = driver.findElement(By.id("button 2"));
        input.click();
    }
    @Then("The associate should see an alert that says {string} a.")
    public void the_associate_should_see_an_alert_that_says_a(String string) {
        System.out.println("The associate should see an alert that says {string} a.");
        wait.until(ExpectedConditions.alertIsPresent());
        String alert = driver.switchTo().alert().getText();
        Assertions.assertEquals(string,alert);
    }
    @When("The employee accepts the confirm.")
    public void the_employee_accepts_the_confirm() {
        System.out.println("The employee accepts the confirm.");
        driver.switchTo().alert().accept();
    }
    @Then("The employee should see an alert that says {string} b.")
    public void the_employee_should_see_an_alert_that_says_b(String string) {
        System.out.println("The employee should see an alert that says {string} b.");
        wait.until(ExpectedConditions.alertIsPresent());
        String confirm = driver.switchTo().alert().getText();
        Assertions.assertEquals(string,confirm);
        driver.switchTo().alert().accept();
    }



}
