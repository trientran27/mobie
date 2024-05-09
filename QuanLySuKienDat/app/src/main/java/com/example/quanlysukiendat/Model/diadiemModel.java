package com.example.quanlysukiendat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class diadiemModel implements Serializable {
    @SerializedName("id")
    private int iddiadiem;
    @SerializedName("name")
    private String tendiadiem;
    @SerializedName("address")
    private String diachi;
    private String creatAt;
    private String endAt;
    private String decrision;
    private String latitude;
    private String longitude;
    @SerializedName("price")
    private int giadiadiem;
    @SerializedName("imageURLs")
    private List<String> imageURLs;

    public diadiemModel() {
    }

    public diadiemModel(int iddiadiem, String tendiadiem, int giadiadiem) {
        this.iddiadiem = iddiadiem;
        this.tendiadiem = tendiadiem;
        this.giadiadiem = giadiadiem;
    }

    public int getIddiadiem() {
        return iddiadiem;
    }

    public void setIddiadiem(int iddiadiem) {
        this.iddiadiem = iddiadiem;
    }

    public String getTendiadiem() {
        return tendiadiem;
    }

    public void setTendiadiem(String tendiadiem) {
        this.tendiadiem = tendiadiem;
    }

    public int getGiadiadiem() {
        return giadiadiem;
    }

    public void setGiadiadiem(int giadiadiem) {
        this.giadiadiem = giadiadiem;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getDecrision() {
        return decrision;
    }

    public void setDecrision(String decrision) {
        this.decrision = decrision;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }
}
