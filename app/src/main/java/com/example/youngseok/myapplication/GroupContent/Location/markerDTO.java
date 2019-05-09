package com.example.youngseok.myapplication.GroupContent.Location;

public class markerDTO {
    private String title;
    private String snip;
    private String location_lat;
    private String location_lng;

    public void setLocation_lng(String location_lng) {
        this.location_lng = location_lng;
    }

    public void setLocation_lat(String location_lat) {
        this.location_lat = location_lat;
    }

    public void setSnip(String snip) {
        this.snip = snip;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation_lng() {
        return location_lng;
    }

    public String getLocation_lat() {
        return location_lat;
    }

    public String getSnip() {
        return snip;
    }

    public String getTitle() {
        return title;
    }
}
