package com.booking;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") String headless) {
        WebDriverManager webDriverManager;
        String selectedBrowser = browser != null ? browser.trim() : "chrome";
        boolean isHeadless = Boolean.parseBoolean(headless);
        WebDriver webDriver;

        switch (selectedBrowser.toLowerCase()) {
            case "chrome":
                webDriverManager = WebDriverManager.chromedriver();
                webDriverManager.setup();
                webDriver = new ChromeDriver(getChromeOptions(isHeadless));
                break;
            case "edge":
                webDriverManager = WebDriverManager.edgedriver();
                webDriverManager.setup();
                webDriver = new EdgeDriver(getEdgeOptions(isHeadless));
                break;
            case "firefox":
                webDriverManager = WebDriverManager.firefoxdriver();
                webDriverManager.setup();
                webDriver = new FirefoxDriver(getFirefoxOptions(isHeadless));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }

        WebDriverRunner.setWebDriver(webDriver);
        Configuration.timeout = 40000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-gpu",
                "--window-size=1920x1080",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled"
        );

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");
        return options;
    }

    private static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(
                "--disable-gpu",
                "--window-size=1920x1080",
                "--no-sandbox",
                "--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled"
        );

        if (headless) {
            options.addArguments("--headless");
        }
        return options;
    }

    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");

        if (headless) {
            options.addArguments("--headless");
        }
        return options;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWindow();
    }
}
