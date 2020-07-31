package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.FTotalModel;

import java.util.List;

public class InvoiceResponse {
    @SerializedName("invoicerowdata")
    private InvoiceListResponse invoiceListResponse;

    private InvoiceListResponse getInvoiceListResponse(){
        return this.invoiceListResponse;
    }
}
