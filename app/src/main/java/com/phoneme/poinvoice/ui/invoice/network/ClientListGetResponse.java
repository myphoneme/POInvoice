package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.ClientDataModel;

import java.util.List;

public class ClientListGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String description;

    @SerializedName("clientdata")
    private List<ClientDataModel> clientDataModelList;

    public List<ClientDataModel> getClientDataModelList(){
        return this.clientDataModelList;
    }
}
