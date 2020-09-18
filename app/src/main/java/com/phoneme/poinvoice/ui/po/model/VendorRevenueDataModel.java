package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class VendorRevenueDataModel {
    @SerializedName("Client_Name")
    private String Client_Name;

    @SerializedName("grand_total")
    private String grand_total;

    public String getClient_Name(){
        return this.Client_Name;
    }

    public String getGrand_total(){
        return this.grand_total;
    }
}
