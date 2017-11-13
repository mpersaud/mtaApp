package com.example.mike.mta_testing;

/**
 * Created by mike on 11/12/17.
 */

public class subwayStation {
    private
    int stationId;
    int complexId;
    String stopid;
    String line;
    String stopName;
    String Boro;
    String daytimeRoutes;
    String struc;
    double latitude;
    double longitude;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getComplexId() {
        return complexId;
    }

    public void setComplexId(int complexId) {
        this.complexId = complexId;
    }

    public String getStopid() {
        return stopid;
    }

    public void setStopid(String stopid) {
        this.stopid = stopid;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getBoro() {
        return Boro;
    }

    public void setBoro(String boro) {
        Boro = boro;
    }

    public String getDaytimeRoutes() {
        return daytimeRoutes;
    }

    public void setDaytimeRoutes(String daytimeRoutes) {
        this.daytimeRoutes = daytimeRoutes;
    }

    public String getStruc() {
        return struc;
    }

    public void setStruc(String struc) {
        this.struc = struc;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public subwayStation(String[] tokens) {
        stopName = tokens[5];
        latitude=Double.parseDouble(tokens[tokens.length-2]);
        longitude=Double.parseDouble(tokens[tokens.length-1]);
        //System.out.println(this.toString());

    }
    @Override
    public String toString(){
        return stopName +" " + latitude + " " +longitude;
    }
}
