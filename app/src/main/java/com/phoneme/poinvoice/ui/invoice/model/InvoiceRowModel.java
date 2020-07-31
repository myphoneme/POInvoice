package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceRowModel {
    @SerializedName("id")
    private String id;

    @SerializedName("invoice_number")
    private String invoice_number;

    @SerializedName("order_id")
    private String order_id;

    @SerializedName( "vendor_id")
    private String vendor_id;

    @SerializedName("subject")
    private String subject;

    @SerializedName("duedate")
    private String duedate;

    @SerializedName("name")
    private String name;

    @SerializedName("cgst")
    private String cgst;


    @SerializedName("cgst_amount")
    private String cgst_amount;

    @SerializedName("sgst")
    private String sgst;

    @SerializedName("sgst_amount")
    private String sgst_amount;

    @SerializedName("igst")
    private String igst;

    @SerializedName("igst_amount")
    private String igst_amount;

    @SerializedName("gsttype")
    private String gsttype;

    @SerializedName("total")
    private String total;

    @SerializedName("grand_total")
    private String grand_total;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("invoice_date")
    private String invoice_date;

    @SerializedName("invoice_time")
    private String invoice_time;

    private String getInvoice_time(){
        return this.invoice_time;
    }
    private String getInvoice_date(){
        return this.invoice_date;
    }

    private String getRemarks(){
        return this.remarks;
    }

    private String getGrand_total(){
        return this.grand_total;
    }
    private String getTotal(){
        return this.total;
    }
    private String getGsttype(){
        return this.gsttype;
    }
    private String getIgst_amount(){
        return this.igst_amount;
    }

    private String getIgst(){
        return this.igst;
    }

    private String getSgst_amount(){
        return this.sgst_amount;
    }

    private String getSgst(){
        return this.sgst;
    }



    private String getCgst_amount(){
        return this.cgst_amount;
    }
    private String getCgst(){
        return this.cgst;
    }
    private String getName(){
        return this.name;
    }
    private String getDuedate(){
        return this.duedate;
    }
    private String getSubject(){
        return this.subject;
    }

    private String getVendor_id(){
        return this.vendor_id;
    }
    private String getOrder_id(){
        return this.order_id;
    }
    private String getInvoice_number(){
        return this.invoice_number;
    }
    private String getId(){
        return this.id;
    }

}
