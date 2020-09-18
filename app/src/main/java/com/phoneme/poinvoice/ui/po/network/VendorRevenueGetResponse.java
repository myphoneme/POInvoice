package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.VendorRevenueDataModel;

import java.util.List;

public class VendorRevenueGetResponse {
    @SerializedName("vendorrevenue")
    private List<VendorRevenueDataModel> vendorRevenueDataModelList;

    public List<VendorRevenueDataModel> getVendorRevenueDataModelList(){
        return this.vendorRevenueDataModelList;
    }
}
