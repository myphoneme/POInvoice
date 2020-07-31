package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;

public class CheckInvoiceListResponse1 {
    @SerializedName("title")
    private String title;

    private String getTitle(){
        return this.title;
    }
}
