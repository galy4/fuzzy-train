package org.selenoid.example.pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenoid.example.settings.SetUp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class BeruHomePAge {
    WebDriver driver;

    //Second approach - use constructor
        public BeruHomePAge(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(name = "text")
        private List<WebElement> searchField;

        @FindBy(xpath = "//button/span[text()='Найти']")
        private WebElement findButton;

        @FindAll({
                @FindBy(tagName = "span"),
                @FindBy(className = "_1G9kMUZOVq")
        })
        private WebElement logo;

        @FindBys( {
                @FindBy(className = "class1"),
                @FindBy(className = "class2")
        } )
        private List<WebElement> elementsWithBoth_class1A;

        @FindBy(xpath = "//span[text()='Каталог товаров']")
        private WebElement catalogButton;

        public void checkPageOpened(){
            assertThat(searchField.get(1).isDisplayed(), equalTo(true));
            Assertions.assertTrue(findButton.isDisplayed());
        }

        public void searchFor(String text){
            searchField.get(1).sendKeys(text);
        }

        public void clickFindButton(){
            findButton.click();
        }

        public void clickLogo(){
            WebElement logo = driver.findElement(By.xpath("//a[@href=\"/\"]"));
            logo.click();
        }

        public void verifyLinksVisibility(List<String> linkNames){
            WebElement element;
            for (String linkName : linkNames) {
                try {
                    element = driver.findElement(By.partialLinkText(linkName));
                    SetUp.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkName)));
                    assertThat(element.isDisplayed(), equalTo(true));
                }catch (NoSuchElementException e){
                    Assertions.fail(linkName + " is not found");
                }
            }
        }

        public SearchResultPage openGoodsCategory(String category, String subCategory){
            Actions builder = new Actions(driver);
            catalogButton.click();
            SetUp.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/span[text()='"+category+"']")));
            builder.moveToElement(driver.findElement(By.xpath("//a/span[text()='"+category+"']")))
                    .build()
                    .perform();
            driver.findElement(By.xpath("//span[text()='"+subCategory+"']")).click();
            return new SearchResultPage(driver);
        }

        public List<String> getAllCategories(){
            catalogButton.click();
            List<String> cats = new ArrayList();
            List<WebElement> categories = driver.findElement(By.className("_3YXmW04bmk"))
                    .findElements(By.xpath("//ul/li/div/div/a/span"));
            for(WebElement we : categories){
                if(we.isDisplayed())
                    cats.add(we.getText());
            }
            return cats;
        }

        public void openMailRuInNewTab(String url) throws InterruptedException {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.open();");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.get(url);
            Thread.sleep(1000);
        }

        public void goBack(){
            System.out.println(driver.findElement(By.id("q")).getAttribute("class"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.close();");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            catalogButton.click();
        }
//        public void clickSuggestedNumber(int k){
//        List<WebElement> goods = suggestedGoods.findElements(By.xpath(
//                "./div/div/div/div"));
//        goods.get(k).click();
//    }


}
