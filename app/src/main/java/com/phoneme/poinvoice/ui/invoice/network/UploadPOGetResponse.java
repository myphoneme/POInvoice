package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceModel;
import java.util.List;

public class UploadPOGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("invoiceno_data")
    private List<InvoiceModel> invoiceModelList;

    public List<InvoiceModel> getInvoiceModelList(){
        return this.invoiceModelList;
    }
}
