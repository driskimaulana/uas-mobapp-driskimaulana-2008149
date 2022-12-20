package com.driskimaulana.myuas.model;

public class BoredModel {

    String type;
    String activity;

    public BoredModel() {}

    public BoredModel(String type, String activity) {
        this.type = type;
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
