package com.phoneme.poinvoice.ui.invoice.invoice;

import com.google.gson.annotations.SerializedName;

public class InvoiceModel {
    @SerializedName("id")
    private String id;

    @SerializedName("invoice_number")
    private String invoice_number;

    @SerializedName("order_id")
    private String order_id;

    @SerializedName("vendor_id")
    private String vendor_id;

    @SerializedName("project")
    private String project;

    @SerializedName("duedate")
    private String duedate;

    @SerializedName("name")
    private String name;

    @SerializedName("address_line1")
    private String address_line1;

    @SerializedName("address_line2")
    private String address_line2;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("gst")
    private String gst;

    @SerializedName("gst_amount")
    private String gst_amount;

    @SerializedName("other")
    private String other;

    @SerializedName("other_amount")
    private String other_amount;

    @SerializedName("total")
    private String total;

    @SerializedName("grand_total")
    private String grand_total;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("invoice_date")
    private String invoice_date;

    @SerializedName("invoice_time")
    private String invoie_time;

    public String getId(){
        return this.id;
    }

    public String getInvoice_number(){
        return this.invoice_number;
    }

    public String getOrder_id(){
        return this.order_id;
    }
    public String getVendor_id(){
        return this.vendor_id;
    }

    public String getProject(){
        return this.project;
    }

    public  String getDuedate(){
        return this.duedate;
    }
    public String getName(){
        return this.name;
    }

    public String getAddress_line1(){
        return this.address_line1;
    }

    public String getAddress_line2(){
        return this.address_line2;

    }

    public String getMobile(){
        return this.mobile;
    }

    public String getEmail(){
        return this.email;
    }

    public String getGst(){
        return this.gst;
    }

    public String getGst_amount(){
        return this.gst_amount;
    }

    public String getOther(){
        return this.other;
    }

    public String getOther_amount(){
        return this.other_amount;
    }

    public String getTotal(){
        return this.total;
    }
    public String getGrand_total(){
        return this.grand_total;
    }

    public String getRemarks(){
        return this.remarks;
    }

    public String getInvoice_date(){
        return this.invoice_date;
    }
    public String getInvoie_time(){
        return this.invoie_time;
    }
    
}


