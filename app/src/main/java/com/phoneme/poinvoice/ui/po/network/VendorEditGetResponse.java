package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.StateModelData;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class VendorEditGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("state")
    private List<StateModelData> stateModelDataList;

    @SerializedName("vendordata")
    private VendorDataModel vendorDataModel;

    public VendorDataModel getVendorDataModel(){
        return this.vendorDataModel;
    }

    public List<StateModelData> getStateModelDataList(){
        return this.stateModelDataList;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getHeading(){
        return this.heading;
    }
    public String getTitle(){
        return this.title;
    }
}
