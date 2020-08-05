package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;

import java.util.List;

public class PoTemplateListResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("potemplate_data")
    private List<PoTemplateDataModel> potemplate_data;

    public List<PoTemplateDataModel> getPotemplate_data(){
        return this.potemplate_data;
    }
}
