package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PODataModel;

import java.util.List;

public class GeneratedListCompleteResponse {
    @SerializedName("polist")
    private List<PODataModel> poDataModelList;

    public List<PODataModel> getPoDataModelList(){
        return this.poDataModelList;
    }
}
