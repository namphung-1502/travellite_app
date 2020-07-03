package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class money {
    @SerializedName("adult")
    @Expose
    private int adult;

    @SerializedName("young")
    @Expose
    private int young;

    @SerializedName("baby")
    @Expose
    private int baby;

    public money(int adult, int young, int baby) {
        this.adult = adult;
        this.young = young;
        this.baby = baby;
    }
    public money(){}

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
}
