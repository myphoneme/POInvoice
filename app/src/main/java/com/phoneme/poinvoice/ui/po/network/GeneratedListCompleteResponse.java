package com.phoneme.poinvoice.ui.po.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceResponseModel;
import com.phoneme.poinvoice.ui.po.model.PODataModel;

import java.util.List;

public class GeneratedListCompleteResponse {
    @SerializedName("polist")
    private List<PODataModel> poDataModelList;

    @SerializedName("totalamountagainpo")
    private List<InvoiceResponseModel> invoiceResponseModelList;

    public List<InvoiceResponseModel> getInvoiceResponseModelList(){
        return this.invoiceResponseModelList;
    }

    public List<PODataModel> getPoDataModelList(){
        return this.poDataModelList;
    }
}
