package com.example.quanlysukiendat.Model;

import com.google.gson.annotations.SerializedName;

public class lichtrinhModel {
    @SerializedName("id")
    private int idlt;
    @SerializedName("name")
    private String namelt;
    @SerializedName("createAt")
    private String timebd;
    @SerializedName("endAt")
    private String timekt;

    private String descrision;
    public lichtrinhModel() {
    }

    public lichtrinhModel(int idlt, String namelt, String timebd, String descrision) {
        this.idlt = idlt;
        this.namelt = namelt;
        this.timebd = timebd;
        this.descrision = descrision;
    }

    public int getIdlt() {
        return idlt;
    }

    public void setIdlt(int idlt) {
        this.idlt = idlt;
    }

    public String getNamelt() {
        return namelt;
    }

    public void setNamelt(String namelt) {
        this.namelt = namelt;
    }

    public String getTimebd() {
        return timebd;
    }

    public void setTimebd(String timebd) {
        this.timebd = timebd;
    }

    public String getTimekt() {
        return timekt;
    }

    public void setTimekt(String timekt) {
        this.timekt = timekt;
    }

    public String getdescrision() {
        return descrision;
    }

    public void setdescrision(String descrision) {
        this.descrision = descrision;
    }
}
