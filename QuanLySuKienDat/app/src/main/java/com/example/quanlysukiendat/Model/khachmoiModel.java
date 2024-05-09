package com.example.quanlysukiendat.Model;

import java.io.Serializable;

public class khachmoiModel implements Serializable {
    private int idKM;
    private String nameKM;
    private String genderKM;
    private String addressKM;
    private String phoneKM;
    private String emailKM;

    public khachmoiModel() {
    }

    public khachmoiModel(int idKM, String nameKM, String phoneKM) {
        this.idKM = idKM;
        this.nameKM = nameKM;
        this.phoneKM = phoneKM;
    }

    public khachmoiModel(int idKM, String nameKM, String genderKM, String addressKM, String phoneKM, String emailKM) {
        this.idKM = idKM;
        this.nameKM = nameKM;
        this.genderKM = genderKM;
        this.addressKM = addressKM;
        this.phoneKM = phoneKM;
        this.emailKM = emailKM;
    }

    public int getIdKM() {
        return idKM;
    }

    public void setIdKM(int idKM) {
        this.idKM = idKM;
    }

    public String getNameKM() {
        return nameKM;
    }

    public void setNameKM(String nameKM) {
        this.nameKM = nameKM;
    }

    public String getGenderKM() {
        return genderKM;
    }

    public void setGenderKM(String genderKM) {
        this.genderKM = genderKM;
    }

    public String getAddressKM() {
        return addressKM;
    }

    public void setAddressKM(String addressKM) {
        this.addressKM = addressKM;
    }

    public String getPhoneKM() {
        return phoneKM;
    }

    public void setPhoneKM(String phoneKM) {
        this.phoneKM = phoneKM;
    }

    public String getEmailKM() {
        return emailKM;
    }

    public void setEmailKM(String emailKM) {
        this.emailKM = emailKM;
    }
}
