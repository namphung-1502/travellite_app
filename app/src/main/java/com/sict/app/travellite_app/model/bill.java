package com.sict.app.travellite_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bill {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("idtour")
    @Expose
    private int idtour;

    @SerializedName("iduser")
    @Expose
    private int iduser;

    @SerializedName("tour")
    @Expose
    private tour tour;

    @SerializedName("departureDay")
    @Expose
    private String departureDay;

    @SerializedName("detail")
    @Expose
    private detail_bill detail;

    public bill(int id, int idtour, int iduser, com.sict.app.travellite_app.model.tour tour, String departureDay, detail_bill detail) {
        this.id = id;
        this.idtour = idtour;
        this.iduser = iduser;
        this.tour = tour;
        this.departureDay = departureDay;
        this.detail = detail;
    }
    public bill(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtour() {
        return idtour;
    }

    public void setIdtour(int idtour) {
        this.idtour = idtour;
    }

    public com.sict.app.travellite_app.model.tour getTour() {
        return tour;
    }

    public void setTour(com.sict.app.travellite_app.model.tour tour) {
        this.tour = tour;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public detail_bill getDetail() {
        return detail;
    }

    public void setDetail(detail_bill detail) {
        this.detail = detail;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
