package org.selenoid.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {
    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    private List<WebElement> titles;

    public void isPageOpened(String param){
        Assertions.assertTrue(driver.findElement(By.xpath("//span[text()='"+param+"']")).isDisplayed());
    }

}

