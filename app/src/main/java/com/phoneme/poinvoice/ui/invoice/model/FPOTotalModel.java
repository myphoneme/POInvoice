package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class FPOTotalModel {
    @SerializedName("fpototal")
    private String fpototal;

    private String getFPOtotal(){
        return this.fpototal;
    }
}
