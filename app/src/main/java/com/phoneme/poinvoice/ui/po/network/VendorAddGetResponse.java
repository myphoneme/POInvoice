package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.StateModelData;

import java.util.List;

public class VendorAddGetResponse {
    @SerializedName("state")
    private List<StateModelData> stateModelDataList;

    public List<StateModelData> getStateModelDataList(){
        return this.stateModelDataList;
    }
}
