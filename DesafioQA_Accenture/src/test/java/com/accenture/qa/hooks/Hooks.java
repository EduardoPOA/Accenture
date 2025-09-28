package com.accenture.qa.hooks;

import com.accenture.qa.frontend.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@web")
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After("@web")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
