package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceListDataModel {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("template_name")
    private String template_name;

    @SerializedName("address_line1")
    private String address_line1;

    @SerializedName("address_line2")
    private String address_line2;

    @SerializedName("address_line3")
    private String address_line3;

    @SerializedName("state_id")
    private String state_id;

    @SerializedName("GSTN_NO")
    private String GSTN_NO;

    @SerializedName("logo")
    private String logo;


    public String getLogo(){
        return this.logo;
    }
    public String getGSTN_NO(){
        return this.GSTN_NO;
    }
    public String getState_id(){
        return this.state_id;
    }
    public String getAddress_line3(){
        return this.address_line3;
    }
    public String getAddress_line2(){
        return this.address_line2;
    }
    public String getAddress_line1(){
        return this.address_line1;
    }
    public String getTemplate_name(){
        return this.template_name;
    }
    public String getTitle() {
        return this.title;
    }
    public String getId(){
        return this.id;
    }
}


/*
*  "id": "4",
            "title": "Phoneme Delhi",
            "template_name": "Phoneme Solutions Pvt. Ltd.",
            "address_line1": "A 3/1  SFS FLATS, ",
            "address_line2": "SAKET South Delhi,",
            "address_line3": " Delhi 110017",
            "state_id": "7",
            "GSTN_NO": "07AAHCP9748G1ZX",
            "logo": "phoneme_logo6.png"
*
*
* */