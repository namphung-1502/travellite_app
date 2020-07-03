package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class booking_hotel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name_customer")
    @Expose
    private String name;

    @SerializedName("id_user")
    @Expose
    private int id_user;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("card_number")
    @Expose
    private String card_number;

    @SerializedName("id_hotel")
    @Expose
    private int id_hotel;

    @SerializedName("count_room")
    @Expose
    private int count_room;

    @SerializedName("date")
    @Expose
    private String date;

    public booking_hotel(int id, String name, int id_user, String phone, String nationality, String card_number, int id_hotel, int count_room, String date) {
        this.id = id;
        this.name = name;
        this.id_user = id_user;
        this.phone = phone;
        this.nationality = nationality;
        this.card_number = card_number;
        this.id_hotel = id_hotel;
        this.count_room = count_room;
        this.date = date;
    }

    public booking_hotel() {
    }

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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getCount_room() {
        return count_room;
    }

    public void setCount_room(int count_room) {
        this.count_room = count_room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
