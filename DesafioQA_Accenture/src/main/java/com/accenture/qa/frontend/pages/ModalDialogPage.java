package com.accenture.qa.frontend.pages;

import com.accenture.qa.frontend.utils.SeleniumMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.accenture.qa.frontend.locators.ModalDialogLocator.*;

public class ModalDialogPage extends SeleniumMethods {

    public ModalDialogPage(WebDriver driver) {
        super(driver);
    }
    public boolean modalDisplayed() {
        return isElementDisplayed(modalTitle);
    }

    public String getModalTitle() {
        WebElement element = driver.findElement(modalTitle);
        return element.getText();
    }

    public void closeModal() {
        clickElement(closeButton);
    }
}
