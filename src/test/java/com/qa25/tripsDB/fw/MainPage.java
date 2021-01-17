package com.qa25.tripsDB.fw;

import com.qa25.tripsDB.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends HelperBase {

    public MainPage(WebDriver wd) {
        super(wd);
    }

    public void isMainPageOpened(String baseURL) {
        wd.navigate().to(baseURL);
    }

    public void setSettings() {
        //open settings
        click(By.className("js-open-settings"));

        //language
        click(By.cssSelector("[data-id='en']"));

        //open settings
        click(By.className("js-open-settings"));

        //currency
        click(By.cssSelector("[data-id='EUR']"));
    }

    public void fillFromCity(String fromCity) {
        type(By.id("destination-from"), fromCity);
    }

    public void fillToCity(String toCity) {
        type(By.id("destination-to"), toCity);
        clickEnter(By.id("destination-to"));
    }
}
