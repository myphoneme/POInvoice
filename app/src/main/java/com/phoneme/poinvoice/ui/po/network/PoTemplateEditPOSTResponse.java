package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;

public class PoTemplateEditPOSTResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("update")
    private boolean update;

    public boolean isUpdate(){
        return this.update;
    }
    public String getMessage(){
        return this.message;
    }
}
