package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class hotel {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("costs")
    @Expose
    private int cost;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("empty")
    @Expose
    private int empty;

    @SerializedName("phone number")
    @Expose
    private String phonenumber;

    @SerializedName("description")
    @Expose
    private String description;

    public hotel(String name, String place, int cost, String image, int empty, String phonenumber, String description) {
        this.name = name;
        this.place = place;
        this.cost = cost;
        this.image = image;
        this.empty = empty;
        this.phonenumber = phonenumber;
        this.description = description;
    }
    public hotel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
