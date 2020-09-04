package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class ClientDataModel {
    @SerializedName("Id")
    private String id;

    @SerializedName("Client_Name")
    private String client_name;

    @SerializedName("GST_NO")
    private String gst_no;

    @SerializedName("Company_Logo")
    private String company_logo;

    @SerializedName("Website")
    private String website;

    @SerializedName("Address_Id")
    private String address_id;

    @SerializedName("state_id")
    private String state_id;

    @SerializedName("Party_Id")
    private String party_id;

    @SerializedName("email")
    private String email;

    @SerializedName("GSTIN")
    private String GSTIN;

    @SerializedName("PAM")
    private String pan;

    @SerializedName("state_name")
    private String state_name;

    @SerializedName("address")
    private String address;

    public String getAddress(){
        return this.address;
    }
    public String getState_name(){
        return this.state_name;
    }
    public String getPan(){
        return this.pan;
    }
    public String getGSTIN(){
        return this.GSTIN;
    }
    public String getEmail(){
        return this.email;
    }
    public String getParty_id(){
        return this.party_id;
    }
    public String getState_id(){
        return this.state_id;
    }
    public String getAddress_id(){
        return this.address_id;
    }
    public String getWebsite(){
        return this.website;
    }
    public String getCompany_logo(){
        return this.company_logo;
    }
    public String getGst_no(){
        return this.gst_no;
    }
    public String getClient_name(){
        return this.client_name;
    }
    private String getId(){
        return this.id;
    }
}
