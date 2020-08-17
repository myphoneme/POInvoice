package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class SigneeDataModel {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
}
