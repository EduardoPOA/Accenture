package com.accenture.qa.frontend.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver driver;
    private static final Object lock = new Object();

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (lock) {
                if (driver == null) {
                    initializeDriver();
                }
            }
        }
        return driver;
    }

    private static void initializeDriver() {
        String browser = System.getProperty("browser", "chrome"); // Mudei default para chrome

        try {
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--kiosk");
                    // Adicionar argumentos para headless se necessário
                    // firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-fullscreen");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-web-security");
                    chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    prefs.put("profile.default_content_settings.popups", 0);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }

            if (driver != null) {
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
                driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
            }

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha na inicialização do WebDriver", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Erro ao fechar o WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public static boolean isDriverInitialized() {
        return driver != null;
    }
}