package com.sict.app.travellite_app.model;

public class area {
    private String name;
    private int image;
    private int count_hotel;

    public area(String name, int image, int count_hotel) {
        this.name = name;
        this.image = image;
        this.count_hotel = count_hotel;
    }
    private area(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCount_hotel() {
        return count_hotel;
    }

    public void setCount_hotel(int count_hotel) {
        this.count_hotel = count_hotel;
    }
}
