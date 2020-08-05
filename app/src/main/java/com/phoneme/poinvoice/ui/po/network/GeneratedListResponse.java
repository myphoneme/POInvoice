package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PODataModel;

import java.util.List;

public class GeneratedListResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("polist")
    private List<PODataModel> poDataModelList;

    public List<PODataModel> getPoDataModelList(){
        return this.poDataModelList;
    }
}
