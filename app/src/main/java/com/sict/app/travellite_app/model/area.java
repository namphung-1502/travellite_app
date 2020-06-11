package com.sict.app.travellite_app.model;

public class area {
    private String name;
    private int image;

    public area(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public area() {
    }

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
}
