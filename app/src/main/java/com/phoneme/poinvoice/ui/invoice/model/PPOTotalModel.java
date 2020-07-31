package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class PPOTotalModel {
    @SerializedName("ppototal")
    private String ppototal;

    private String getppoTotal(){
        return this.ppototal;
    }
}
