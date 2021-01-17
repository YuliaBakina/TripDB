package com.qa25.tripsDB.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    EventFiringWebDriver wd;
    MainPage mainPage;


    String browser;

    String baseURL = "https://www.rome2rio.com/map/";

    public static class MyListener extends AbstractWebDriverEventListener {

        Logger logger = LoggerFactory.getLogger(MyListener.class);

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.error(throwable.toString());
        }
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public String getBaseURL(){
        return baseURL;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

     public void init() {
        wd = new EventFiringWebDriver(new ChromeDriver());

/*        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }*/

        wd.register(new MyListener());

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
//        wd.manage().deleteAllCookies();

        wd.get(baseURL);

       mainPage = new MainPage(wd);

    }

    public void stop() {
        wd.quit();
    }

}

