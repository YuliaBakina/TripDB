package com.qa25.tripsDB.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase{

    String fromCity = "London, England";
    String toCity = "Paris, France";

    @BeforeClass
    public void insurePreconditions(){
        appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());
        appManager.getMainPage().setSettings();
    }

    @Test
    public void searchRoute(){
        System.out.println("Test start");

        appManager.getMainPage().fillFromCity(fromCity);
        appManager.getMainPage().fillToCity(toCity);
        //appManager.getMainPage().click

    }

}
