package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class FunnelTotalModel {
    @SerializedName("funneltotal")
    private String funneltotal;

    private String getFunnelTotal(){
        return this.funneltotal;
    }
}
