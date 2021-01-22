package com.qa25.tripsDB.model;

public class RouteFromDB {
    private int fromCityID;
    private String fromCity;
    private int toCityID;
    private String toCity;
    private String trType;
    private String trDuration;
    private String trPrice;

    public int getFromCityID() {
        return fromCityID;
    }

    public int getToCityID() {
        return toCityID;
    }

    public String getFromCity() {
        return fromCity;
    }

    public RouteFromDB setFromCityID(int fromCityID) {
        this.fromCityID = fromCityID;
        return this;
    }
    public RouteFromDB setToCityID(int toCityID) {
        this.toCityID = toCityID;
        return this;
    }

    public RouteFromDB setFromCity(String fromCity) {
        this.fromCity = fromCity;
        return this;
    }

    public String getToCity() {
        return toCity;
    }

    public RouteFromDB setToCity(String toCity) {
        this.toCity = toCity;
        return this;
    }

    public String getTrType() {
        return trType;
    }

    public RouteFromDB setTrType(String trType) {
        this.trType = trType;
        return this;
    }

    public String getTrDuration() {
        return trDuration;
    }

    public RouteFromDB setTrDuration(String trDuration) {
        this.trDuration = trDuration;
        return this;
    }

    public String getTrPrice() {
        return trPrice;
    }

    public RouteFromDB setTrPrice(String trPrice) {
        this.trPrice = trPrice;
        return this;
    }

    @Override
    public String toString() {
        return  "FROM city: " + this.fromCity +
                ", TO city: " + this.toCity +
                ", trType: " + this.trType +
                ", Duration: " + this.trDuration +
                ", Price: " + this.trPrice;
    }



}
