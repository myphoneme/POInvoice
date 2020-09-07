package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.ClientDataModel;
import com.phoneme.poinvoice.ui.po.model.StateModelData;

import java.util.List;

public class ClientCreateGetResponse {
    @SerializedName("state")
    private List<StateModelData> stateModelData;

    public List<StateModelData> getStateModelData(){
        return this.stateModelData;
    }
}
