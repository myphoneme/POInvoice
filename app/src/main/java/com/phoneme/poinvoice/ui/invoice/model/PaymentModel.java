package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class PaymentModel {
    @SerializedName("payment")
    private String payment;

    private String getPayment(){
        return this.payment;
    }
}
