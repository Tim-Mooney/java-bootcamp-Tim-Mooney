package com.northstar.crm.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

/** Page Object — locate via data-testid only. */
public class CustomerFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CustomerFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public CustomerFormPage open(String baseUrl) {
        driver.get(baseUrl + "/customers.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid=customer-id]")));
        return this;
    }
    public CustomerFormPage fill(String id, String name, String email, String status) {
        driver.findElement(By.cssSelector("[data-testid=customer-id]")).sendKeys(id);
        driver.findElement(By.cssSelector("[data-testid=full-name]")).sendKeys(name);
        driver.findElement(By.cssSelector("[data-testid=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[data-testid=status]")).sendKeys(status);
        return this;
    }
    public void submit() {
        driver.findElement(By.cssSelector("[data-testid=submit-customer]")).click();
    }
    public String resultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid=create-result]"))).getText();
    }
}
