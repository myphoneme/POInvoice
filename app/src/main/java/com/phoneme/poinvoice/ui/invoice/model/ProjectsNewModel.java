package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class ProjectsNewModel {
    @SerializedName("Prospect_Id")
    private String Prospect_Id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Employee_Id")
    private String Employee_Id;

    @SerializedName("Created_By")
    private String Created_By;

    @SerializedName("Description")
    private String Description;

    @SerializedName("Organization_Id")
    private String Organization_Id;

    @SerializedName("Contact_Id")
    private String Contact_Id;

    public String getContact_Id(){
        return this.Contact_Id;
    }
    public String getOrganization_Id(){
        return this.Organization_Id;
    }
    public String getDescription(){
        return this.Description;
    }
    public String getCreated_By(){
        return this.Created_By;
    }

    public String getEmployee_Id(){
        return this.Employee_Id;
    }

    public String getName(){
        return this.name;
    }

    public String getProspect_Id(){
        return this.Prospect_Id;
    }
}


//"Name": "abc",
//        "Client_Id": "1",
//        "Employee_Id": "47",
//        "Created_By": null,
//        "Description": "abc",
//        "Organization_Id": "1",
//        "Contact_Id": "14,15,6"