package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class tour_sale implements Serializable {
   @SerializedName("id")
   @Expose
   private int id;

   @SerializedName("name")
   @Expose
   private String name;

   @SerializedName("sale")
   @Expose
   private int sale;

   @SerializedName("image")
   @Expose
   private detail_image list_image;

   @SerializedName("money")
   @Expose
   private money money;

    public tour_sale(int id, String name, int sale, detail_image list_image, com.sict.app.travellite_app.model.money money) {
        this.id = id;
        this.name = name;
        this.sale = sale;
        this.list_image = list_image;
        this.money = money;
    }
    public tour_sale(){}

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

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public detail_image getList_image() {
        return list_image;
    }

    public void setList_image(detail_image list_image) {
        this.list_image = list_image;
    }

    public money getMoney() {
        return money;
    }

    public void setMoney(com.sict.app.travellite_app.model.money money) {
        this.money = money;
    }
}
