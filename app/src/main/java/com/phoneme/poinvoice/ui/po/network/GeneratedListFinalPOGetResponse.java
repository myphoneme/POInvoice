package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.OrgDataModel;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.phoneme.poinvoice.ui.po.model.POItemData;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class GeneratedListFinalPOGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("po_main_data")
    private List<PODataModel> poDataModelList;

    @SerializedName("org")
    private OrgDataModel orgDataModel;
    public OrgDataModel getOrgDataModel(){
        return this.orgDataModel;
    }
    @SerializedName("vendor")
    private VendorDataModel vendorDataModel;

    public VendorDataModel getVendorDataModel(){
        return this.vendorDataModel;
    }

    public List<PODataModel> getPoDataModelList(){
        return this.poDataModelList;
    }

    @SerializedName("po_items")
    private List<POItemData> poItemDataList;

    public List<POItemData> getPoItemDataList(){
        return this.poItemDataList;
    }


}
