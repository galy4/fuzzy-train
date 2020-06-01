package org.selenoid.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenoid.example.settings.SetUp;

public class GoodsPage {

    private WebDriver driver;

    public GoodsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Добавить в корзину']")
    private WebElement addToCartButton;

    public boolean isPageOpened(){
        SetUp.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//span[text()='Добавить в корзину']")));
        return addToCartButton.isDisplayed();
    }

    public CartPage addToCart(){
        addToCartButton.click();
        return new CartPage(driver);
    }
}
