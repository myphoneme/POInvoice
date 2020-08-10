package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class OrgDataModel {
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

//    @SerializedName("State")
//    private String State;

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getTemplate_name(){
        return this.template_name;
    }

    public String getAddress_line1() {
        return this.address_line1;
    }

    public String getAddress_line2(){
        return  this.address_line2;
    }

    public String getAddress_line3(){
        return this.address_line3;
    }

    public String getState_id(){
        return this.state_id;
    }

    public String getGSTN_NO(){
        return this.GSTN_NO;
    }
    public String getLogo(){
        return this.logo;
    }
//    public String getState(){
//        return this.State;
//    }
}
