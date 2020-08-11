package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceProductsDataModel {
    @SerializedName("id")
    private String id;

    @SerializedName("invoice_id")
    private String invoice_id;

    @SerializedName("invoice_number")
    private String invoice_number;

    @SerializedName("product")
    private String product;

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
    public String getProduct(){
        return this.product;
    }
    public String getInvoice_number(){
        return this.invoice_number;
    }
    public String getInvoice_id(){
        return this.invoice_id;
    }
    public String getId(){
        return this.id;
    }

}


/*
* "id": "65",
            "invoice_id": "46",
            "invoice_number": "PS/GEM/01/20-21",
            "product": "HD USB Camera",
            "description": "1080p Full HD Camera 120° field of view angle with 2 builtin Mics",
            "qty": "3",
            "price": "6907",
            "subtotal": "20720"
*
*
* */