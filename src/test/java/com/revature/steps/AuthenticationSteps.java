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

public class AuthenticationSteps {
    WebDriver driver = EndToEndRunner.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @Given("The employee is on the login page.")
    public void the_employee_is_on_the_login_page() {
        System.out.println("The employee is on the login page.");
        driver.navigate().to("http://localhost:63342/jody_roane_P1/ersWebsite/portal.html");

    }

    @When("The employee types incorrect {string} into the username input.")
    public void the_employee_types_incorrect_into_the_username_input(String string) {
        System.out.println("The employee types incorrect {string} into the username input.");
        WebElement username = driver.findElement(By.id("input 1"));
        username.sendKeys(string);

    }

    @When("The employee types incorrect {string} into the password input.")
    public void the_employee_types_incorrect_into_the_password_input(String string) {
        System.out.println("The employee types incorrect {string} into the password input.");
        WebElement password = driver.findElement(By.id("input 2"));
        password.sendKeys(string);
    }

    @When("the employee clicks the login button a.")
    public void the_employee_clicks_the_login_button_a() {
        System.out.println("the employee clicks the login button a.");
        WebElement login = driver.findElement(By.id("button 1"));
        login.click();
    }

    @Then("the employee sees an alert that that says {string}.")
    public void the_employee_sees_an_alert_that_that_says(String string) {
        System.out.println("the employee sees an alert that that says {string}.");
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assertions.assertEquals(string, alertText);
        driver.switchTo().alert().accept();

    }

    @When("The employee types correct {string} into the username input.")
    public void the_employee_types_correct_into_the_username_input(String string) {
        System.out.println("The employee types correct {string} into the username input.");
        WebElement username = driver.findElement(By.id("input 1"));
        username.sendKeys(string);
    }

    @When("The employee types correct {string} into the password input.")
    public void the_employee_types_correct_into_the_password_input(String string) {
        System.out.println("The employee types correct {string} into the password input.");
        WebElement password = driver.findElement(By.id("input 2"));
        password.sendKeys(string);
    }

    @When("The employee clicks the login button b.")
    public void the_employee_clicks_the_login_button_b() {
        System.out.println("The employee clicks the login button b.");
        WebElement login = driver.findElement(By.id("button 1"));
        login.click();
    }

    @Then("The employee should be on the {string} page.")
    public void the_employee_should_be_on_the_page(String string) {
        System.out.println("The employee should be on the {string} page.");
        wait.until(ExpectedConditions.titleIs(string));
        String title = driver.getTitle();
        Assertions.assertEquals(string,title);
    }
    @Then("The employee should see their {string},{string},{string} on the home page.")
    public void the_employee_should_see_their_on_the_home_page(String string, String string2, String string3) {
        System.out.println("The employee should see their {string},{string},{string} on the home page.");
        String firstname = driver.findElement(By.id("h1 1")).getText();
        String lastname = driver.findElement(By.id("h1 2")).getText();
        String role = driver.findElement(By.id("p 1")).getText();
        Assertions.assertEquals(string, firstname);
        Assertions.assertEquals(string2, lastname);
        Assertions.assertEquals(string3, role);
    }

    @When("The manager clicks on {string} link.")
    public void the_manager_clicks_on_link(String string) {
        System.out.println("The manager clicks on {string} link.");
        WebElement link = driver.findElement(By.linkText(string));
        link.click();
    }

    @Then("The manager should be on the {string} page a.")
    public void the_manager_should_be_on_the_page_a(String string) {
        System.out.println("The manager should be on the {string} page a.");
        wait.until(ExpectedConditions.titleIs(string));
        String title = driver.getTitle();
        Assertions.assertEquals(string, title);
    }

    @When("The manager clicks on the {string} link.")
    public void the_manager_clicks_on_the_link(String string) {
        System.out.println("The manager clicks on the {string} link.");
        WebElement link = driver.findElement(By.linkText(string));
        link.click();
    }

    @Then("the manager should be on the {string} page b.")
    public void the_manager_should_be_on_the_page_b(String string) {
        System.out.println("the manager should be on the {string} page b.");
        wait.until(ExpectedConditions.titleIs(string));
        String title = driver.getTitle();
        Assertions.assertEquals(string, title);
    }

}
