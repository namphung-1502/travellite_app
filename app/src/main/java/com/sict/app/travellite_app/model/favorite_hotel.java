package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class favorite_hotel {
    @SerializedName("id_user")
    @Expose
    private int iduser;

    @SerializedName("id_hotel")
    @Expose
    private int idhotel;

    public favorite_hotel(int iduser, int idhotel) {
        this.iduser = iduser;
        this.idhotel = idhotel;
    }
    public favorite_hotel(){}

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdhotel() {
        return idhotel;
    }

    public void setIdhotel(int idhotel) {
        this.idhotel = idhotel;
    }
}
