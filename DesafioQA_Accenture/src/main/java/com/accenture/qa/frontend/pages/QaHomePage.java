package com.accenture.qa.frontend.pages;

import com.accenture.qa.frontend.utils.SeleniumMethods;
import org.openqa.selenium.WebDriver;

import static com.accenture.qa.frontend.locators.QaHomeLocator.formsCard;

public class QaHomePage extends SeleniumMethods {

    public QaHomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://demoqa.com/");
    }

    public void clickForms() {
       clickElement(formsCard);
    }
}
