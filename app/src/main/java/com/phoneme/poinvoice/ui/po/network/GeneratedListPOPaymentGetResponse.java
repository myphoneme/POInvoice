package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PODataModel;

import java.util.List;

public class GeneratedListPOPaymentGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("po_no")
    private List<PODataModel> poDataModelList;

    public List<PODataModel> getPoDataModelList(){
        return this.poDataModelList;
    }

}
