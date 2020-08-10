package com.phoneme.poinvoice.ui.po.model;

import com.google.gson.annotations.SerializedName;

public class POItemData {
    @SerializedName("id")
    private String id;

    @SerializedName("po_id")
    private String po_id;

    @SerializedName("item")
    private String item;

    @SerializedName("description")
    private String description;

    @SerializedName("qty")
    private String qty;

    @SerializedName("price")
    private String price;

    @SerializedName("subtotal")
    private String subtotal;

    public String getSubtotal(){
        return this.subtotal;
    }
    public String getPrice(){
        return this.price;
    }
    public String getQty(){
        return this.qty;
    }
    public String getDescription(){
        return this.description;
    }
    public String getItem(){
        return this.item;
    }

    public String getPo_id(){
        return this.po_id;
    }
    public String getId(){
        return this.id;
    }
}
