package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class FTotalModel {
    @SerializedName("ftotal")
    private String ftotal;

    private String getFtotal(){
        return this.ftotal;
    }
}
