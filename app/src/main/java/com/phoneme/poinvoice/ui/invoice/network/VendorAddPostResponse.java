package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;

public class VendorAddPostResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("added")
    private boolean added;

    public boolean isAdded(){
        return this.added;
    }
    public String getMessage(){
        return this.message;
    }
}
