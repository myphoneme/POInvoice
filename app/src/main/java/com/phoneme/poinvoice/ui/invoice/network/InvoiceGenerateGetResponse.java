package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.ProjectDataModel;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class InvoiceGenerateGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("projectdata")
    private List<ProjectDataModel> projectDataModelList;

    @SerializedName("vendors")
    private List<VendorDataModel> vendorDataModelList;

    @SerializedName("potemplate_data")
    private List<PoTemplateDataModel> poTemplateDataModelList;

    public List<PoTemplateDataModel> getPoTemplateDataModelList(){
        return this.poTemplateDataModelList;
    }

    public List<VendorDataModel> getVendorDataModelList(){
        return this.vendorDataModelList;
    }

    public List<ProjectDataModel> getProjectDataModelList(){
        return this.projectDataModelList;
    }

}
