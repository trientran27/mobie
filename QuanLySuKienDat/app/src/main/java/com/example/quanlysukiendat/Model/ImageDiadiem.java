package com.example.quanlysukiendat.Model;

import com.example.quanlysukiendat.DiaDiemQuanLyActivity;
import com.google.gson.annotations.SerializedName;

public class ImageDiadiem {
    private int id;
    @SerializedName("imageURL")
    private String imageUrl;
    private int idDiadiem;

    public ImageDiadiem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIdDiadiem() {
        return idDiadiem;
    }

    public void setIdDiadiem(int idDiadiem) {
        this.idDiadiem = idDiadiem;
    }
}
