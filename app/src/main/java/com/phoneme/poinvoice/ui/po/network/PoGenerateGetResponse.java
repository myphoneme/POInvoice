package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.model.SigneeDataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class PoGenerateGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("signee_options")
    private List<SigneeDataModel> signeeDataModelList;

    @SerializedName("potemplate_data")
    private List<PoTemplateDataModel> poTemplateDataModelList;

    @SerializedName("vendors")
    private List<VendorDataModel> vendorDataModelList;


    public List<VendorDataModel> getVendorDataModelList(){
        return this.vendorDataModelList;
    }

    public List<PoTemplateDataModel> getPoTemplateDataModelList(){
        return this.poTemplateDataModelList;
    }

    public List<SigneeDataModel> getSigneeDataModelList(){
        return this.signeeDataModelList;
    }
}
