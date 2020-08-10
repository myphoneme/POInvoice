package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;

public class POTemplateAddPostResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("added")
    private boolean update;

    public boolean isUpdate(){
        return this.update;
    }
    public String getMessage(){
        return this.message;
    }
}
