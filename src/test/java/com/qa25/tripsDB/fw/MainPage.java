package com.qa25.tripsDB.fw;

import com.qa25.tripsDB.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public void readData(String fromCity, String toCity) {
        //put all routes in a list
        List<WebElement> resultList = getItemsList(By.className("route-container"));

        for(WebElement e : resultList) {

            //take only the routes with 1 transport type
            if(e.findElements(By.cssSelector(".transit-icon__mode")).size() == 1) {

                //read transport type
                String trType = e.findElement(By.tagName("h3")).getText();
                trType = trType.contains(" ") ? trType.split(" ")[0] : trType;
                //transform trip from word to number
                //not done yet

                //read trip duration
                String trDuration = e.findElement(By.cssSelector(".route__duration")).getText().trim();
                String durD = "0", durH = "0", durM = "0";
                if(trDuration.contains("days")) {
                    durD = trDuration.trim().split(" ")[2].split("days")[0].trim();
                    durH = trDuration.trim().split(" ")[4].split("h")[0];
                 //   durM = trDuration.trim().split(" ")[5].split("m")[0];
                }else {
                    durH = trDuration.trim().split(" ")[2].split("h")[0];
                    durM = trDuration.trim().split(" ")[3].split("m")[0];
                }

                //count trip duration in minutes
                int tripDuration = Integer.parseInt(durD) * 24 * 60 + Integer.parseInt(durH) * 60 + Integer.parseInt(durM);

                //read trip price
                String trPrice = e.findElement(By.cssSelector(".route__price.tip-west")).getText();
                trPrice = trPrice.split("€")[0];

                System.out.println(fromCity + "," + toCity + "," +
                                    trType + "," + tripDuration + "," + trPrice);
                logger.info(fromCity + "," + toCity + "," +
                        trType + "," + tripDuration + "," + trPrice);
            }
        }

    }
}
