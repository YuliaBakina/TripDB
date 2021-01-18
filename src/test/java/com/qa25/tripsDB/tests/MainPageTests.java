package com.qa25.tripsDB.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase{

    /*String fromCity = "London, England";
    String toCity = "Paris, France";*/

    /*String fromCity = "Antwerp";
    String toCity = "Aberdeen";*/

    String fromCity = "Antwerp";
    String toCity = "Alicante";


    @BeforeClass
    public void insurePreconditions(){
        appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());
        appManager.getMainPage().setSettings();
    }

    @Test
    public void searchRoute(){

        //filling the fields
        appManager.getMainPage().fillFromCity(fromCity);
        appManager.getMainPage().fillToCity(toCity);

        //reading the search results
        appManager.getMainPage().readData(fromCity, toCity);

    }

}
