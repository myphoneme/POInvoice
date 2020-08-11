package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceDataModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceListDataModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceProductsDataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.util.List;

public class InvoiceFinalInvoiceGetResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String desc;

    @SerializedName("invoicedata")
    private List<InvoiceDataModel> invoiceDataModelList;

    public List<InvoiceDataModel> getInvoiceDataModelList(){
        return this.invoiceDataModelList;
    }

    @SerializedName("invoiceproductsdata")
    private List<InvoiceProductsDataModel> invoiceProductsDataModels;

    @SerializedName("list")
    private List<InvoiceListDataModel> invoiceListDataModelList;

    @SerializedName("vendor")
    private List<VendorDataModel> vendorDataModelList;

    public List<VendorDataModel> getVendorDataModelList(){
        return this.vendorDataModelList;
    }

    public List<InvoiceListDataModel> getInvoiceListDataModelList(){
        return this.invoiceListDataModelList;
    }


    public List<InvoiceProductsDataModel> getInvoiceProductsDataModels(){
        return this.invoiceProductsDataModels;
    }

}
