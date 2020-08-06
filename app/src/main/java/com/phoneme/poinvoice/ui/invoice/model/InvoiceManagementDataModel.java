package com.phoneme.poinvoice.ui.invoice.model;

public class InvoiceManagementDataModel {
    private String title;
    private String value;

    private String phoneme_value;
    private String funnel_value;

    public void setFunnel_value(String funnel_value){
        this.funnel_value=funnel_value;
    }
    public void setPhoneme_value(String value){
        this.phoneme_value=value;
    }
    public void setValue(String value){
        this.value=value;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getPhoneme_value(){
        return this.phoneme_value;
    }

    public String getFunnel_value(){
        return this.funnel_value;
    }
    public String getValue(){
        return this.value;
    }
    public String getTitle(){
        return this.title;
    }
}
