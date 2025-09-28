package com.accenture.qa.frontend.pages;

import com.accenture.qa.frontend.utils.SeleniumMethods;
import org.openqa.selenium.WebDriver;
import static com.accenture.qa.frontend.locators.PracticeFormLocator.*;
import static com.accenture.qa.frontend.utils.TestDataGenerator.*;

public class PracticeFormPage extends SeleniumMethods {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalInfo(String fName, String lName, String userEmail, String mobileNumber) {
        setText(firstName, fName);
        setText(lastName, lName);
        setText(email, userEmail);
        setText(firstName, fName);
        setText(firstName, fName);
        clickElement(genderMale);
        setText(mobile, mobileNumber);
    }

    public void fillDateOfBirth() {
        clickElement(dateInput);
        selectDropdownByVisibleText(month,"January");
        selectDropdownByVisibleText(year,"1984");
        clickElement(day);
    }

    public void fillSubjects(String subject) {
        setText(subjects, subject);
        clickElement(subjectsOptions);
    }

    public void selectHobbies() {
        clickElement(hobbiesSports);
    }

    public void uploadFile() {
        setText(uploadPicture, directoryFile());
    }

    public void fillAddress(String address) {
        setText(currentAddress, address);
    }

    public void selectStateAndCity(String state, String city) {
        scrollView(stateDropdown);
        clickElement(stateDropdown);
        clickElement(stateDropdownOption("NCR"));
        clickElement(cityDropdown);
        clickElement(cityDropdownOption("Delhi"));
    }

    public void submitForm() {
        clickElement(submitButton);
    }
}
