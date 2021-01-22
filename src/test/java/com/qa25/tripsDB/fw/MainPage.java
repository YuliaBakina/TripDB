package com.qa25.tripsDB.fw;

import com.qa25.tripsDB.model.RouteFromDB;
import com.qa25.tripsDB.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPage extends HelperBase {

    public MainPage(WebDriver wd) {
        super(wd);
    }

    String[] trTypesArray = {"no_one","fly", "bus", "train", "drive", "taxi", "walk", "towncar", "rideshare", "shuttle","ferry","tram"};


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
        clickEnter(By.id("destination-from"));
    }

    public void fillToCity(String toCity) {
        type(By.id("destination-to"), toCity);
        clickEnter(By.id("destination-to"));
    }

    public void readData(String fromCity, String toCity) throws InterruptedException {
        //put all routes in a list

        delay(3000);

        List<WebElement> resultList = new ArrayList<>();
        resultList.clear();
        resultList = getItemsList(By.className("route-container"));

        for(WebElement e : resultList) {

            //take only the routes with 1 transport type
            if(e.findElements(By.cssSelector(".transit-icon__mode")).size() == 1) {

                //read transport type, duration and price
                String trType = e.findElement(By.cssSelector(".route__title")).getText();
                String trDuration = e.findElement(By.cssSelector(".route__duration")).getText().trim();
                String trPrice = e.findElement(By.cssSelector(".route__price.tip-west")).getText();

                //convert transport type
                if(trType.toLowerCase().contains("ferry")){
                    trType = Integer.toString(Arrays.asList(trTypesArray).indexOf("ferry"));
                }else if(trType.toLowerCase().contains("night")){
                    trType = Integer.toString(Arrays.asList(trTypesArray).indexOf("bus"));
                }else {
                    trType = trType.contains(" ") ? trType.split(" ")[0] : trType;
                    trType = Integer.toString(Arrays.asList(trTypesArray).indexOf(trType.toLowerCase().trim()));
                }

                //convert trip duration - too many different options for d/h/m
                String durD = "0", durH = "0", durM = "0";
                trDuration = trDuration.split("•")[1];
                if(trDuration.contains("days")){
                    durD = trDuration.split("days")[0].trim();
                }
                if(trDuration.contains("min")){
                    durM = trDuration.split("min")[0].trim();
                }else {
                    String[] duration = trDuration.trim().split(" ");
                    for (int j = 0; j < duration.length; j++) {
                        if (duration[j].contains("h")) {
                            durH = duration[j].split("h")[0].trim();
                        }
                        if (duration[j].contains("m")) {
                            durM = duration[j].split("m")[0].trim();
                        }
                    }
                }

                //convert trip duration into minutes
                int tripDuration = Integer.parseInt(durD) * 24 * 60 + Integer.parseInt(durH) * 60 + Integer.parseInt(durM);

                //convert trip price
                trPrice = trPrice.split("€")[0];

                logger.info("From Site: " + fromCity + "," + toCity + "," +
                        trType + "," + tripDuration + "," + trPrice);

            }
        }

    }

}
