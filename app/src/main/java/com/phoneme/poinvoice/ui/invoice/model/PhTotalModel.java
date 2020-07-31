package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class PhTotalModel {
    @SerializedName("phtotal")
    private String phtotal;

    private String getPhtotal(){
        return this.phtotal;
    }
}
