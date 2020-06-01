package org.selenoid.example.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SetUp {
    public static WebDriver driver;
    public static WebDriverWait waitVar;

    public static WebDriver createDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("81.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("resolution", "1920x1080");
        capabilities.acceptInsecureCerts();
        capabilities.setAcceptInsecureCerts(true);
        driver = new RemoteWebDriver(
                new URL("http://172.26.10.42:4444/wd/hub"),
                capabilities
        );
        waitVar = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown(){
        driver.close();
    }

}
