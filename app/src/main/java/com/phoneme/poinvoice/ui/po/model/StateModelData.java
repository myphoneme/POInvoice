package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class StateModelData {
    @SerializedName("Id")
    private String Id;

    @SerializedName("State_Code")
    private String State_Code;

    @SerializedName("State_Name")
    private String State_Name;

    public String getId(){
        return this.Id;
    }

    public String getState_Code(){
        return this.State_Code;
    }
    public String getState_Name(){
        return this.State_Name;
    }
}
