package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;

public class PoGeneratePostResponse {
    @SerializedName("added")
    private boolean added;

    @SerializedName("message")
    private String message;

    public String getMessage(){
        return this.message;
    }

    public boolean isAdded(){
        return this.added;
    }
}
