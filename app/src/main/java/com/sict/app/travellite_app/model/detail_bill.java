package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class detail_bill {
    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("phone_number")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("adult")
    @Expose
    private int adult;

    @SerializedName("young")
    @Expose
    private int young;

    @SerializedName("baby")
    @Expose
    private int baby;

    @SerializedName("total")
    @Expose
    private int total;

    public detail_bill(String fullname, String phone, String address, int adult, int young, int baby, int total) {
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.adult = adult;
        this.young = young;
        this.baby = baby;
        this.total = total;
    }
    public  detail_bill(){}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getYoung() {
        return young;
    }

    public void setYoung(int young) {
        this.young = young;
    }

    public int getBaby() {
        return baby;
    }

    public void setBaby(int baby) {
        this.baby = baby;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
