package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class ProjectDataModel {
    @SerializedName("name")
    private String name;

    public String getName(){
        return this.name;
    }
}
