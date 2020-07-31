package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class TotalModel {
    @SerializedName("total")
    private String total;

    private String getTotal(){
        return this.total;
    }
}
