package org.selenoid.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenoid.example.settings.SetUp;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class=\"_22Nyawtpph\"]//span[text()='Перейти в корзину']")
    public WebElement goToCart;

    @FindBy(className = "_2TbI0lRCD8")
    public WebElement deleteIcon;

    public void goToCartAndDelete(){
        goToCart.click();
        SetUp.waitVar.until(ExpectedConditions.presenceOfElementLocated(
                By.className("_2TbI0lRCD8")));
        deleteIcon.click();
        Assertions.assertTrue(driver.findElement(By.xpath(
                "//div[text()='В корзине нет товаров']")).isDisplayed());
    }

    public void checkGoodDeleted(){
        Assertions.assertTrue(driver.findElement(By.xpath(
                "//div[text()='В корзине нет товаров']")).isDisplayed());
    }
}
