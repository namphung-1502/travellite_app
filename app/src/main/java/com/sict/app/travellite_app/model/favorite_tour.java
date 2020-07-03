package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class favorite_tour {
    @SerializedName("id_user")
    @Expose
    private int iduser;

    @SerializedName("id_tour")
    @Expose
    private int idtour;

    public favorite_tour(int iduser, int idtour) {
        this.iduser = iduser;
        this.idtour = idtour;
    }

    public favorite_tour() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdtour() {
        return idtour;
    }

    public void setIdtour(int idtour) {
        this.idtour = idtour;
    }
}
