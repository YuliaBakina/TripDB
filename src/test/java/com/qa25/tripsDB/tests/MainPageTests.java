package com.qa25.tripsDB.tests;

import com.qa25.tripsDB.model.RouteFromDB;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class MainPageTests extends TestBase{

    @BeforeClass
    public void insurePreconditions(){
        appManager.getMainPage().isMainPageOpened(appManager.getBaseURL());
        appManager.getMainPage().setSettings();
    }

    @Test(enabled = true)
    public void searchRoute() throws SQLException, ClassNotFoundException, InterruptedException {

        //read unique cities combination from DB
        List<RouteFromDB[]> listCitiesDB = DBTesting.citiesQuery();

        int i = 0;
        for(RouteFromDB[] cities : listCitiesDB){
            //filling the fields in the site
            String fromCity = cities[i].getFromCity();
            String toCity = cities[i].getToCity();
            appManager.getMainPage().fillFromCity(fromCity);
            appManager.getMainPage().fillToCity(toCity);

            //read data from DB for each couple of cities
            List<RouteFromDB[]> listRoutesDB = DBTesting.routesQuery(cities[i].getFromCityID(),cities[i].getToCityID());

            logger.info("--------------");
            logger.info(fromCity + " - " + toCity);
            logger.info("--------------");

            int j = 0;
            for(RouteFromDB[] routes : listRoutesDB){
                logger.info("From DataBase: " + routes[j].getFromCity() + "," + routes[j].getToCity() + "," +
                        routes[j].getTrType() + "," + routes[j].getTrDuration() + "," + routes[j].getTrPrice());
            }


            //read the search results from the site
            appManager.getMainPage().readData(cities[i].getFromCity(), cities[i].getToCity());

        }


    }


}
