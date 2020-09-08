package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.InvoiceViewModel;
import com.phoneme.poinvoice.ui.invoice.adapter.InvoiceListAdapter;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceRowModel;
import com.phoneme.poinvoice.ui.invoice.network.CheckInvoiceListResponse1;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceListResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceResponse;
import com.phoneme.poinvoice.user.network.OTPVerifactionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceFragment extends Fragment implements InvoiceListAdapter.OnItemClickListener{

    private InvoiceViewModel invoiceViewModel;
    private RecyclerView recyclerView;
    private Spinner YearsSpinner;
    private List<InvoiceRowModel> invoiceRowModelList;

//    private String years=new String[];
    String[] yearsString = {"2020-21","2019-20","2018-19","2017-18"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceViewModel =
                ViewModelProviders.of(this).get(InvoiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_invoice, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        invoiceViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        Toast.makeText(getContext(),"onCreateView", Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_invoice_list);
        YearsSpinner=(Spinner)view.findViewById(R.id.years);
        Button geneateInvoice=(Button)view.findViewById(R.id.generate_new_invoice);
        geneateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_invoice);
            }
        });
        setYearsSpinnerData();
        //getInvoiceListData();
        //InvoiceListAdapter adapter=new InvoiceListAdapter(getContext());
//        InvoiceListAdapter adapter=new InvoiceListAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
    }

    private void setAdapter(List<InvoiceRowModel> invoiceRowModelList){
        InvoiceListAdapter adapter=new InvoiceListAdapter(getContext(),this,invoiceRowModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }

    public void onItemClick(int position){
        Bundle args2 = new Bundle();
        String id=invoiceRowModelList.get(position).getId();
        args2.putString("id",id);//To be changed to dynamic data
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_payment_upload,args2);

    }
    public void onItemClick2(int position){
        Bundle args2 = new Bundle();
        String id=invoiceRowModelList.get(position).getId();
        args2.putString("id",id);//To be changed to dynamic data
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_po_template_upload,args2);
    }
    public void onItemClick3(int position){
        Bundle args2 = new Bundle();
        String id=invoiceRowModelList.get(position).getId();
        String name=invoiceRowModelList.get(position).getName();
        args2.putString("id",id);
        args2.putString("name",name);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_final_invoice,args2);

    }

    private void getInvoiceListData(String year){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<InvoiceListResponse> call=service.getInvoiceList(year);
        call.enqueue(new Callback<InvoiceListResponse>() {
            @Override
            public void onResponse(Call<InvoiceListResponse> call, Response<InvoiceListResponse> response) {
                Toast.makeText(getContext(),"onResponse"+response.toString(), Toast.LENGTH_LONG).show();
                if(response!=null && response.body()!=null && response.body().getInvoicerowList()!=null && !response.body().getInvoicerowList().isEmpty() && response.body().getInvoicerowList().size()>0){
                    System.out.println("onresponse="+response.body().getInvoicerowList().get(0).getDuedate());
                    invoiceRowModelList=response.body().getInvoicerowList();
                    setAdapter(response.body().getInvoicerowList());
                }else{
                    invoiceRowModelList.removeAll(invoiceRowModelList);
                    setAdapter(response.body().getInvoicerowList());
                }

            }

            @Override
            public void onFailure(Call<InvoiceListResponse> call, Throwable t) {
                Toast.makeText(getContext(),"onFailure", Toast.LENGTH_LONG).show();
            }
        });
//        call.enqueue(new Callback<CheckInvoiceListResponse1>() {
//            @Override
//            public void onResponse(Call<CheckInvoiceListResponse1> call, Response<CheckInvoiceListResponse1> response) {
//                Toast.makeText(getContext()," onResponse", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<CheckInvoiceListResponse1> call, Throwable t) {
//                Toast.makeText(getContext()," onFailure", Toast.LENGTH_LONG).show();
//            }
//        });
//        call.enqueue(new Callback<InvoiceResponse>() {
//            @Override
//            public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<InvoiceResponse> call, Throwable t) {
//
//            }
//        });
//        call.enqueue(new Callback<InvoiceResponse>() {
//            @Override
//            public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {
//                Toast.makeText(getContext()," onResponse", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<InvoiceResponse> call, Throwable t) {
//                Toast.makeText(getContext()," onFailure", Toast.LENGTH_LONG).show();
//
//            }
//        });
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
                getInvoiceListData(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}