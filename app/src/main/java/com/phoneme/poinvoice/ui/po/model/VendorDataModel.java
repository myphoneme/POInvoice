package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class VendorDataModel {
    @SerializedName("Id")
    private String Id;

    @SerializedName("Vendor_name")
    private String Vendor_name;

    @SerializedName("Address")
    private String Address;


    @SerializedName("email")
    private String email;


    @SerializedName("State")
    private String State;

    @SerializedName("state_id")
    private String state_id;

    @SerializedName("GSTIN")
    private String GSTIN;

    @SerializedName("PAM")
    private String PAM;

    public String getPAM(){
        return this.PAM;
    }
    public String getGSTIN(){
        return this.GSTIN;
    }
    public String getState_id(){
        return this.state_id;
    }
    public String getState(){
        return this.State;
    }
    public String getEmail(){
        return this.email;
    }
    public String getAddress(){
        return this.Address;
    }

    public String getVendor_name(){
        return this.Vendor_name;
    }

    public String getId(){
        return this.Id;
    }
}
