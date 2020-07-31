package com.phoneme.poinvoice.ui.invoice.model;

import com.google.gson.annotations.SerializedName;

public class PhonemeTotalModel {
    @SerializedName("phonemetotal")
    private String phonemetotal;

    private String getPhonemeTotal(){
        return this.phonemetotal;
    }
}
