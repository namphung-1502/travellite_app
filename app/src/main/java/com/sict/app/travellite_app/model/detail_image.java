package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class detail_image implements Serializable {
    @SerializedName("image1")
    @Expose
    private String image1;

    @SerializedName("image2")
    @Expose
    private String image2;

    @SerializedName("image3")
    @Expose
    private String image3;

    @SerializedName("image4")
    @Expose
    private String image4;

    public detail_image(String image1, String image2, String image3, String image4) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }
    public detail_image(){}

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }
}
