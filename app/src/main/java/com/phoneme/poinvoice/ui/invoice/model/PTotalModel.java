package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class PTotalModel {
    @SerializedName("ptotal")
    private String ptotal;

    private String getPtotal(){
        return this.ptotal;
    }
}
