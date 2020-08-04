package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class VendorListResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("vendordata")
    private List<VendorDataModel> vendordata;

    public List<VendorDataModel> getVendordata(){
        return this.vendordata;
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
