package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.adapter.InvoiceManagementAdapter;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceResponseModel;
import com.phoneme.poinvoice.ui.po.network.GeneratedListCompleteResponse;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceManagementDataModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManagementFragment extends Fragment implements InvoiceManagementAdapter.OnItemClickListener {
    private RecyclerView recyclerview;
    private Spinner YearsSpinner;
    private List<InvoiceManagementDataModel> invoiceManagementDataModelList=new ArrayList<InvoiceManagementDataModel>();
    private  InvoiceResponseModel invoiceResponseModel;

    String[] yearsString = {"2022-23","2021-22","2020-21","2019-20","2018-19","2017-18"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invoice_management, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview=(RecyclerView)view.findViewById(R.id.invoice_management);
        YearsSpinner=(Spinner)view.findViewById(R.id.years);
        //setAdapter();
        setYearsSpinnerData();
        //getInvoiceManagementData();
    }
    private void setAdapter(List<InvoiceManagementDataModel> invoiceManagementDataModelList){
        InvoiceManagementAdapter adapter=new InvoiceManagementAdapter(getContext(),this,invoiceManagementDataModelList);
        recyclerview.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        GridLayoutManager manager;
        manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        recyclerview.setLayoutManager(linearVertical);
    }

    private void getInvoiceManagementData(String year){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListCompleteResponse> call=service.getGeneratedListComplete(year);
        call.enqueue(new Callback<GeneratedListCompleteResponse>() {
            @Override
            public void onResponse(Call<GeneratedListCompleteResponse> call, Response<GeneratedListCompleteResponse> response) {
                if(response!=null && response.body()!=null && response.body().getInvoiceResponseModelList()!=null && response.body().getInvoiceResponseModelList().get(0)!=null){
                    invoiceResponseModel=response.body().getInvoiceResponseModelList().get(0);
                    //poDataModelList=response.body().getPoDataModelList();
                    //setAdapter(poDataModelList);
                    //Toast.makeText(getContext(),"success", Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(),"NotEmpty", Toast.LENGTH_LONG).show();

                    setData(invoiceResponseModel);
                }else{
                    Toast.makeText(getContext(),"Empty", Toast.LENGTH_LONG).show();

                    setDataEmpty();
                }

            }

            @Override
            public void onFailure(Call<GeneratedListCompleteResponse> call, Throwable t) {
                //Toast.makeText(getContext(),"fail", Toast.LENGTH_LONG).show();

            }
        });
    }



    private void setData( InvoiceResponseModel netData){
        invoiceManagementDataModelList.removeAll(invoiceManagementDataModelList);
        InvoiceManagementDataModel data1=new InvoiceManagementDataModel();
        data1.setTitle("Total Revenue");
        data1.setValue(netData.getTotal());
        data1.setPhoneme_value(netData.getTotalphonemeinvoice());
        data1.setFunnel_value(netData.getTotalfunnelinvoice());
        invoiceManagementDataModelList.add(data1);

        InvoiceManagementDataModel data2=new InvoiceManagementDataModel();
        data2.setTitle("Total Expense");
        data2.setValue(netData.getTotalpo());
        data2.setPhoneme_value( netData.getTotalphonemepo());
        data2.setFunnel_value(netData.getTotalfunnelpo());
        invoiceManagementDataModelList.add(data2);

        InvoiceManagementDataModel data3=new InvoiceManagementDataModel();
        data3.setTitle("Receivable Revenue");
        data3.setValue(netData.getTotalinvoice());
        data3.setPhoneme_value(netData.getTotalinvociephoneme());
        data3.setFunnel_value(netData.getTotalinvoicefunnel());
        invoiceManagementDataModelList.add(data3);

        InvoiceManagementDataModel data4=new InvoiceManagementDataModel();
        data4.setTitle("Pending Payments");
        data4.setValue(netData.getTotalpayment());
        data4.setPhoneme_value(netData.getTotalphonemepayment());
        data4.setFunnel_value(netData.getTotalfunnelpayment());
        invoiceManagementDataModelList.add(data4);
        //Toast.makeText(getContext(),"before set adapter", Toast.LENGTH_LONG).show();
        setAdapter(invoiceManagementDataModelList);
    }

    private void setDataEmpty(){
        invoiceManagementDataModelList.removeAll(invoiceManagementDataModelList);

        InvoiceManagementDataModel data1=new InvoiceManagementDataModel();
        data1.setTitle("Total Revenue");
        data1.setValue("0");
        data1.setPhoneme_value("0");
        data1.setFunnel_value("0");
        invoiceManagementDataModelList.add(data1);

        InvoiceManagementDataModel data2=new InvoiceManagementDataModel();
        data2.setTitle("Total Expense");
        data2.setValue("0");
        data2.setPhoneme_value( "0");
        data2.setFunnel_value("0");
        invoiceManagementDataModelList.add(data2);

        InvoiceManagementDataModel data3=new InvoiceManagementDataModel();
        data3.setTitle("Receivable Revenue");
        data3.setValue("0");
        data3.setPhoneme_value("0");
        data3.setFunnel_value("0");
        invoiceManagementDataModelList.add(data3);

        InvoiceManagementDataModel data4=new InvoiceManagementDataModel();
        data4.setTitle("Pending Payments");
        data4.setValue("0");
        data4.setPhoneme_value("0");
        data4.setFunnel_value("0");
        invoiceManagementDataModelList.add(data4);
        //Toast.makeText(getContext(),"before set adapter", Toast.LENGTH_LONG).show();
        setAdapter(invoiceManagementDataModelList);
    }

    private void setYearsSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,yearsString);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearsSpinner.setAdapter(aa);
        YearsSpinner.setSelection(0);

        YearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String year=yearsString[YearsSpinner.getSelectedItemPosition()];
                //getInvoiceListData(year);
                Toast.makeText(getContext(),"before getInvoiceManagementData", Toast.LENGTH_LONG).show();

                getInvoiceManagementData(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void onItemClick(int position){}
    public void onItemClick2(int position){}
    public void onItemClick3(int position){}
}
