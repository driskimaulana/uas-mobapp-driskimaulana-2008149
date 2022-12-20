package com.driskimaulana.myuas.model;

public class HasilModel {



    private String angkatText;
    private String timestamp;

    public HasilModel(String timestamp) {
        this.angkatText = "HP Diangkat";
        this.timestamp = timestamp;
    }

    public String getAngkatText() {
        return angkatText;
    }

    public void setAngkatText(String angkatText) {
        this.angkatText = angkatText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
