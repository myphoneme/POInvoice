package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.model.StateModelData;

import java.util.List;

public class PoTemplateEditGETResponse {
    @SerializedName("po_templatedata")
    private PoTemplateDataModel po_templatedata;

    @SerializedName("state")
    private List<StateModelData> states;

    public List<StateModelData> getStates(){
        return this.states;
    }
   public PoTemplateDataModel getPo_templatedata(){
       return this.po_templatedata;
   }
}
