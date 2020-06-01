package org.selenoid.example;

import org.selenoid.example.pages.*;
import org.selenoid.example.settings.SetUp;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenoid.example.pages.BeruHomePAge;
import org.selenoid.example.pages.SearchResultPage;
import org.selenoid.example.pages.TestWatcher;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(TestWatcher.class)
public class BeruTest {
        private static BeruHomePAge homePAge;
    @BeforeAll
    static void init() throws MalformedURLException {
        SetUp.createDriver();
        homePAge = new BeruHomePAge(SetUp.driver);
        SetUp.driver.navigate().to("https://beru.ru/");
    }
    @Description("test 1")
    @Test
    void checkLinks(){
        homePAge.verifyLinksVisibility(Arrays.asList(
              "Скидки", "доставка", "оплата", "возврат"
        ));
    }
    @Description("test 2")
    @Test
    void mailru() throws InterruptedException {
        homePAge.openMailRuInNewTab("http://mail.ru");
        homePAge.goBack();
    }
    @Description("test 3")
    @Test
    void checkCats(){
        List<String> aa = homePAge.getAllCategories();
        assertThat(aa, containsInAnyOrder("Оставайтесь дома",
                "Автотовары",
                "Электроника",
                "Компьютерная техника",
                "Бытовая техника",
                "Товары для ремонта",
                "Мебель",
                "Стирка и уборка",
                "Товары для дома",
                "Дача, сезонные товары",
                "Детские товары",
                "Продукты",
                "Гигиена",
                "Красота",
                "Ювелирные изделия",
                "Товары для взрослых",
                "Здоровье",
                "Товары для животных",
                "Спорт и отдых",
                "Чулки, колготки, носки",
                "Сумки и аксессуары",
                "Часы, украшения, аксессуары",
                "Одежда",
                "Книги",
                "Хобби и творчество",
                "Канцелярские товары",
                "Сувениры"));
        homePAge.clickLogo();

    }
    @Description("test 5")
    @Test
    void searchForSmth(){
       SearchResultPage res = homePAge.openGoodsCategory(
               "Бытовая техника", "Морозильники");
       res.isPageOpened("Морозильники");
    }


    @AfterEach
    void goToMain(){
        homePAge.clickLogo();
    }

    @AfterAll
    static void tearDown(){
        SetUp.tearDown();
    }
}

