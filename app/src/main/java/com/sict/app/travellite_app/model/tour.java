package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class tour implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("favorite")
    @Expose
    private int favorite;

    @SerializedName("image")
    @Expose
    private detail_image image;

    @SerializedName("money")
    @Expose
    private money money;

    public tour(int id, String name, String description, int favorite, detail_image image, com.sict.app.travellite_app.model.money money) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.favorite = favorite;
        this.image = image;
        this.money = money;
    }

    public tour() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public detail_image getImage() {
        return image;
    }

    public void setImage(detail_image image) {
        this.image = image;
    }

    public com.sict.app.travellite_app.model.money getMoney() {
        return money;
    }

    public void setMoney(com.sict.app.travellite_app.model.money money) {
        this.money = money;
    }
}
