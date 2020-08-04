package com.phoneme.poinvoice.ui.invoice.network;

import com.google.gson.annotations.SerializedName;
import com.phoneme.poinvoice.ui.invoice.model.FPOTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.FTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.FUTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.FunnelTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceRowModel;
import com.phoneme.poinvoice.ui.invoice.model.PPOTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.PTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.PaymentModel;
import com.phoneme.poinvoice.ui.invoice.model.PhTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.PhonemeTotalModel;
import com.phoneme.poinvoice.ui.invoice.model.TotalInvoiceModel;
import com.phoneme.poinvoice.ui.invoice.model.TotalModel;

import java.util.List;

public class InvoiceListResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("heading")
    private String heading;

    @SerializedName("desc")
    private String description;

    @SerializedName("yr")
    private String yr;

    @SerializedName("dtArr")
    private List<String> dtArr;

    @SerializedName("invoicerow")
    private List<InvoiceRowModel> invoicerowList;

    @SerializedName("totalamountagainpo")
    private List<TotalModel> totalamountagainpoList;

    @SerializedName("totalpo")
    private List<TotalModel> totalpoList;

    @SerializedName("totalphonemeinvoice")
    private List<PTotalModel> totalphonemeinvoiceList;

    @SerializedName("totalfunnelinvoice")
    private List<FTotalModel> totalfunnelinvoiceList;

    @SerializedName("totalphonemepo")
    private List<PPOTotalModel> totalphonemepoList;


    @SerializedName("totalfunnelpo")
    private List<FPOTotalModel> totalfunnelpoList;


    @SerializedName("totalinvoice")
    private List<TotalInvoiceModel> totalinvoiceList;

    @SerializedName("totalinvociephoneme")
    private List<PhonemeTotalModel> totalinvociephonemeList;

    @SerializedName("totalinvoicefunnel")
    private List<FunnelTotalModel> totalinvoicefunnelList;

    @SerializedName("totalpayment")
    private List<PaymentModel> totalpaymentList;


    @SerializedName("totalphonemepayment")
    private List<PhTotalModel> totalphonemepaymentList;

    @SerializedName("totalfunnelpayment")
    private List<FUTotalModel> totalfunnelpaymentList;



    public  List<InvoiceRowModel> getInvoicerowList(){
        return this.invoicerowList;
    }

    private List<TotalInvoiceModel> getTotalinvoiceList(){
        return this.totalinvoiceList;
    }
    private List<PTotalModel> getTotalphonemeinvoiceList(){
        return this.totalphonemeinvoiceList;
    }
    private List<PPOTotalModel> getTotalphonemepoList(){
        return this.totalphonemepoList;
    }
    private List<PhTotalModel> getTotalphonemepaymentList(){
        return this.totalphonemepaymentList;
    }
    private List<PhonemeTotalModel> getTotalinvociephonemeList(){
        return this.totalinvociephonemeList;
    }

    private List<PaymentModel> getTotalpaymentList(){
        return this.totalpaymentList;
    }

    private List<FUTotalModel> getTotalfunnelpaymentList(){
        return this.totalfunnelpaymentList;
    }
    private List<FunnelTotalModel> getTotalinvoicefunnelList(){
        return this.totalinvoicefunnelList;
    }

    private List<FTotalModel> getTotalfunnelinvoiceList(){
        return this.totalfunnelinvoiceList;
    }
    private List<FPOTotalModel> getTotalfunnelpoList(){
        return this.totalfunnelpoList;
    }




    private List<TotalModel> getTotalpoList(){
        return this.totalpoList;
    }
    private List<TotalModel> getTotalamountagainpoList(){
        return this.totalamountagainpoList;
    }

    private List<String> getDtArr(){
        return this.dtArr;
    }
    private String getYr(){
        return this.yr;
    }


    private String getDescription(){
            return this.description;
    }
    private String getTitle(){
        return this.title;
    }

    private String getHeading(){
            return this.heading;
    }
}
