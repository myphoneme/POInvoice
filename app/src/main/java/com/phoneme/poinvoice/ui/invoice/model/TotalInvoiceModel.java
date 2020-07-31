package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class TotalInvoiceModel {
    @SerializedName("totalinvoice")
    private String totalinvoice;

    private String getTotalInvoice(){
        return this.totalinvoice;
    }
}
