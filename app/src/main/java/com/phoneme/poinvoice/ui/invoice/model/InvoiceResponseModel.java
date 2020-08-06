package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceResponseModel {
    @SerializedName("total")
    private String total;

    @SerializedName("totalphonemeinvoice")
    private String totalphonemeinvoice;

    @SerializedName("totalfunnelinvoice")
    private String totalfunnelinvoice;

    @SerializedName("totalpo")
    private String totalpo;

    @SerializedName("totalphonemepo")
    private String totalphonemepo;

    @SerializedName("totalfunnelpo")
    private String totalfunnelpo;

    @SerializedName("totalinvoice")
    private String totalinvoice;

    @SerializedName("totalinvociephoneme")
    private String totalinvociephoneme;

    @SerializedName("totalinvoicefunnel")
    private String totalinvoicefunnel;


    @SerializedName("totalpayment")
    private String totalpayment;

    @SerializedName("totalphonemepayment")
    private String totalphonemepayment;

    @SerializedName("totalfunnelpayment")
    private String totalfunnelpayment;


    public String getTotalfunnelpayment(){
        return this.totalfunnelpayment;
    }
    public String getTotalphonemepayment(){
        return this.totalphonemepayment;
    }
    public String getTotalpayment(){
        return this.totalpayment;
    }
    public String getTotalinvoicefunnel(){
        return this.totalinvoicefunnel;
    }
    public String getTotalinvociephoneme(){
        return this.totalinvociephoneme;
    }
    public String getTotalinvoice(){
        return this.totalinvoice;
    }
    public String getTotalfunnelpo(){
        return this.totalfunnelpo;
    }
    public String getTotalphonemepo(){
        return this.totalphonemepo;
    }
    public String getTotalpo(){
        return this.totalpo;
    }
    public String getTotalfunnelinvoice(){
        return this.totalfunnelinvoice;
    }
    public String getTotalphonemeinvoice(){
        return this.totalphonemeinvoice;
    }
    public String getTotal(){
        return this.total;
    }
}
