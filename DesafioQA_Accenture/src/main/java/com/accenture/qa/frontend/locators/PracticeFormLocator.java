package com.accenture.qa.frontend.locators;

import org.openqa.selenium.By;

public class PracticeFormLocator {

    public static By firstName = By.id("firstName");
    public static By lastName = By.id("lastName");
    public static By email = By.id("userEmail");
    public static By genderMale = By.xpath("//label[contains(text(),'Male')]");
    public static By mobile = By.id("userNumber");
    public static By dateInput = By.id("dateOfBirthInput");
    public static By month = By.className("react-datepicker__month-select");
    public static By year = By.className("react-datepicker__year-select");
    public static By day = By.xpath("//div[contains(@class,'react-datepicker__day') and text()='1']");
    public static By subjects = By.id("subjectsInput");
    public static By subjectsOptions = By.id("react-select-2-option-0");
    public static By hobbiesSports = By.xpath("//label[contains(text(),'Sports')]");
    public static By uploadPicture = By.id("uploadPicture");
    public static By currentAddress = By.id("currentAddress");
    public static By stateDropdown = By.id("state");
    public static By stateDropdownOption(String state) { return By.xpath("//div[text()='" + state + "']");}
    public static By cityDropdown = By.id("city");
    public static By cityDropdownOption(String city) { return By.xpath("//div[text()='" + city + "']");}
    public static By submitButton = By.id("submit");

}
