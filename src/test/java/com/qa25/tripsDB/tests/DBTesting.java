package com.qa25.tripsDB.tests;

import com.qa25.tripsDB.model.RouteFromDB;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBTesting {

    public static List<RouteFromDB[]> citiesQuery() throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://root@localhost:3306/cheap_trip";
        //Load MySQL JDBC Driver
        Class.forName("com.mysql.jdbc.Driver");
        //Creating connection to the database
        Connection con = DriverManager.getConnection(dbURL);
        //Creating statement object
        Statement st = con.createStatement();
        //the query bellow is limited by number of records with WHERE statement
        String queryCities = "SELECT DISTINCT l1.id, l1.name, l2.id, l2.name " +
                "FROM travel_data td " +
                "JOIN locations l1 ON (l1.id = td.from) " +
                "JOIN locations l2 ON (l2.id = td.to) " +
                "WHERE (td.id > 2000 and td.id < 10000)" +
                "ORDER by td.id;";
        //Executing the SQL Query and store the results in ResultSet
        ResultSet resultRoutes = st.executeQuery(queryCities);

        //While loop to iterate through all data
        List<RouteFromDB[]> listDB = new ArrayList<>();
        while (resultRoutes.next()) {

            listDB.add(new RouteFromDB[]{new RouteFromDB()
                    .setFromCityID(resultRoutes.getInt(1))
                    .setFromCity(resultRoutes.getString(2).trim())
                    .setToCityID(resultRoutes.getInt(3))
                    .setToCity(resultRoutes.getString(4).trim())
            });

        }

        //Closing DB Connection
        con.close();

        return listDB;
    }

    public static List<RouteFromDB[]> routesQuery(int fromCityID, int toCityID) throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://root@localhost:3306/cheap_trip";
        //Load MySQL JDBC Driver
        Class.forName("com.mysql.jdbc.Driver");
        //Creating connection to the database
        Connection con = DriverManager.getConnection(dbURL);
        //Creating statement object
        Statement st = con.createStatement();
        //String queryRoutes = "SELECT count(*) FROM travel_data;";
        String queryRoutes = "SELECT l1.name, l2.name, td.transportation_type as type, td.time_in_minutes as time, td.euro_price as eur " +
                "FROM travel_data td " +
                "JOIN locations l1 ON (l1.id = td.from) " +
                "JOIN locations l2 ON (l2.id = td.to) " +
                "WHERE (l1.id = " + fromCityID + " and l2.id = " + toCityID + ")" +
                "ORDER by td.id;";
        //Executing the SQL Query and store the results in ResultSet
        ResultSet resultRoutes = st.executeQuery(queryRoutes);

        //While loop to iterate through all data and print results
        List<RouteFromDB[]> listDB = new ArrayList<>();
        while (resultRoutes.next()) {

            listDB.add(new RouteFromDB[]{new RouteFromDB()
                    .setFromCity(resultRoutes.getString(1).trim())
                    .setToCity(resultRoutes.getString(2).trim())
                    .setTrType(resultRoutes.getString(3))
                    .setTrDuration(resultRoutes.getString(4))
                    .setTrPrice(resultRoutes.getString(5))
            });

        } //resultRoutes.getFloat(5)

        //Closing DB Connection
        con.close();

        return listDB;
    }

}
