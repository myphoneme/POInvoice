package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class FUTotalModel {
    @SerializedName("futotal")
    private String futotal;

    private String getFUTotal(){
        return this.futotal;
    }
}
