package com.accenture.qa.frontend.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumMethods {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public SeleniumMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void waitForTextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void waitForElementToBeClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(By elementBy) {
        waitForElementToBeVisible(elementBy);
        waitForElementToBeClickable(elementBy);
        WebElement elem = driver.findElement(elementBy);
        try {
            elem.click();
        } catch (ElementClickInterceptedException e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", elem);
            } catch (Exception jsException) {
                try {
                    String elementId = elem.getAttribute("id");
                    if (elementId != null && !elementId.isEmpty()) {
                        WebElement label = driver.findElement(By.cssSelector("label[for='" + elementId + "']"));
                        label.click();
                    } else {
                        Actions actions = new Actions(driver);
                        actions.moveToElement(elem).click().perform();
                    }
                } catch (Exception finalException) {
                    throw new RuntimeException("Failed to click element after multiple attempts", finalException);
                }
            }
        }
    }

    protected void setText(By element, String text) {
        waitForElementToBeVisible(element);
        WebElement elem = driver.findElement(element);
        elem.clear();
        elem.sendKeys(text);
    }

    protected void selectDropdownByVisibleText(By element, String text) {
        waitForElementToBeVisible(element);
        WebElement elem = driver.findElement(element);
        Select dropdown = new Select(elem);
        dropdown.selectByVisibleText(text);
    }

    protected  void scrollView(By element){
        WebElement elem = driver.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", elem);
    }

    protected boolean isElementDisplayed(By element) {
        try {
            waitForElementToBeVisible(element);
            WebElement elem = driver.findElement(element);
            return elem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}