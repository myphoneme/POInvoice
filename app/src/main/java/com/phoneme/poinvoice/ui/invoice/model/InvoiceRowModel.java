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

    @SerializedName("vendor_name")
    private String vendor_name;

    @SerializedName("percentage_po_received")
    private String percentage_po_received;


    public String getPercentage_po_received(){
        return this.percentage_po_received;
    }

    public String getVendor_name(){
        return this.vendor_name;
    }

    public String getInvoice_time(){
        return this.invoice_time;
    }
    public String getInvoice_date(){
        return this.invoice_date;
    }

    public String getRemarks(){
        return this.remarks;
    }

    public String getGrand_total(){
        return this.grand_total;
    }
    public String getTotal(){
        return this.total;
    }
    public String getGsttype(){
        return this.gsttype;
    }
    public String getIgst_amount(){
        return this.igst_amount;
    }

    public String getIgst(){
        return this.igst;
    }

    public String getSgst_amount(){
        return this.sgst_amount;
    }

    public String getSgst(){
        return this.sgst;
    }



    public String getCgst_amount(){
        return this.cgst_amount;
    }
    public String getCgst(){
        return this.cgst;
    }
    public String getName(){
        return this.name;
    }
    public String getDuedate(){
        return this.duedate;
    }
    public String getSubject(){
        return this.subject;
    }

    public String getVendor_id(){
        return this.vendor_id;
    }
    public String getOrder_id(){
        return this.order_id;
    }
    public String getInvoice_number(){
        return this.invoice_number;
    }
    public String getId(){
        return this.id;
    }
}
