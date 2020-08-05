package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class PODataModel {
    @SerializedName("id")
    private String id;

    @SerializedName("organization")
    private String organization;

    @SerializedName("po_number")
    private String po_number;

    @SerializedName("duedate")
    private String duedate;

    @SerializedName("vendor_id")
    private String vendor_id;


    /////////////////


    @SerializedName("subject")
    private String subject;

    @SerializedName("cgst")
    private String cgst;

    @SerializedName("cgst_amount")
    private String cgst_amount;

    @SerializedName("sgst")
    private String sgst;

    @SerializedName("sgst_amount")
    private String sgst_amount;

    /////////////////


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

    /////////////////


    @SerializedName("signee")
    private String signee;

    @SerializedName("po_date")
    private String po_date;

    @SerializedName("termsconditions")
    private String termsconditions;

    @SerializedName("po_time")
    private String po_time;

    @SerializedName("vendor_name")
    private String vendor_name;

    @SerializedName("company_logo")
    private String company_logo;

    @SerializedName("invoice_no")
    private String invoice_no;

    @SerializedName("totalamount_payment")
    private String totalamount_payment;

    @SerializedName("download")
    private String download;

    public String getDownload(){
        return this.download;
    }

    public String getTotalamount_payment(){
        return this.totalamount_payment;
    }

    //totalamount_payment
    public String getInvoice_no(){
        return this.invoice_no;
    }
    public String getId(){
        return this.id;
    }
    public String getOrganization(){
        return this.organization;
    }

    public String getPo_number(){
        return this.po_number;
    }

    public String getDuedate(){
        return this.duedate;
    }

    public String getVendor_id(){
        return this.vendor_id;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getCgst(){
        return this.cgst;
    }

    public String getCgst_amount(){
        return this.cgst_amount;
    }

    public String getSgst(){
        return this.sgst;
    }

    public String getSgst_amount(){
        return this.sgst_amount;
    }
    public String getIgst(){
        return this.igst;
    }

    public String getIgst_amount(){
        return this.igst_amount;
    }
    public String getGsttype(){
        return this.gsttype;
    }

    public String getTotal(){
        return this.total;
    }
    public String getGrand_total(){
        return this.grand_total;
    }

    public String getSignee(){
        return this.signee;
    }

    public String getPo_date(){
        return this.po_date;
    }
    public String getTermsconditions(){
        return this.termsconditions;
    }
    public String getPo_time(){
        return this.po_time;
    }
    public String getVendor_name(){
        return this.vendor_name;
    }
    public String getCompany_logo(){
        return this.company_logo;
    }

}


