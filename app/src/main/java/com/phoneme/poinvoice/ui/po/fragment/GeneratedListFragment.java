package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.adapter.VendorListAdapter;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import com.phoneme.poinvoice.ui.po.network.GeneratedListResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListCompleteResponse;
import com.phoneme.poinvoice.ui.po.network.VendorListResponse;
import com.phoneme.poinvoice.ui.po.viewmodel.GeneratedListViewModel;
import com.phoneme.poinvoice.ui.po.adapter.GeneratedListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneratedListFragment extends Fragment implements GeneratedListAdapter.OnItemClickListener {

    private GeneratedListViewModel generatedListViewModel;
    private RecyclerView recyclerView;
    private Button generateNewPOButton;
    private Spinner YearsSpinner;
    private EditText searchEdit;
    private GeneratedListAdapter adapter;

    private List<PODataModel> poDataModelList;
    String[] yearsString = {"2022-23","2021-22","2020-21", "2019-20", "2018-19", "2017-18"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generatedlist, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_generated_list);
        YearsSpinner = (Spinner) view.findViewById(R.id.years);
        generateNewPOButton = (Button) view.findViewById(R.id.add_vendor_button);
        Button searchButton = (Button) view.findViewById(R.id.search_button);
        searchEdit = (EditText) view.findViewById(R.id.search_text);
        generateNewPOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_po);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchEdit.getText().toString();
                Toast.makeText(getContext(), " search clicked", Toast.LENGTH_LONG).show();
                String year = yearsString[YearsSpinner.getSelectedItemPosition()];
//        getInvoiceListSearchData(year, searchString);
                getGeneratedListSearchCompleteData(year, searchString);
            }
        });

        //GeneratedListAdapter adapter=new GeneratedListAdapter(getContext());
        //getGeneratedListData();
        //getGeneratedListCompleteData();

        setYearsSpinnerData();
//        GeneratedListAdapter adapter=new GeneratedListAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
    }

    public void onItemClick(int position) {
        Bundle args2 = new Bundle();
        args2.putString("id", this.poDataModelList.get(position).getId());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_generated_list_payment_upload, args2);
    }

    public void onItemClick2(int position) {
        Bundle args2 = new Bundle();
        args2.putString("id", this.poDataModelList.get(position).getId());
        args2.putString("po_number", this.poDataModelList.get(position).getPo_number());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_invoice_add_upload, args2);
    }

    public void onItemClick3(int position) {
        Bundle args2 = new Bundle();
        args2.putString("id", this.poDataModelList.get(position).getId());
        args2.putString("organization", this.poDataModelList.get(position).getOrganization());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_po_final_funnel_data, args2);
    }

    private void getGeneratedListSearchCompleteData(String year, String search) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //Call<GeneratedListCompleteResponse> call = service.getGeneratedListComplete(year);//This line will be changed after search api is done
        Call<GeneratedListCompleteResponse> call = service.getGeneratedListSearchComplete(year,search);//This line will be changed after search api is done

        call.enqueue(new Callback<GeneratedListCompleteResponse>() {
            @Override
            public void onResponse(Call<GeneratedListCompleteResponse> call, Response<GeneratedListCompleteResponse> response) {
                if (response != null && response.body() != null && response.body().getPoDataModelList() != null && !response.body().getPoDataModelList().isEmpty() && response.body().getPoDataModelList().size() > 0) {
                    poDataModelList.removeAll(poDataModelList);
                    poDataModelList=response.body().getPoDataModelList();
                    adapter.setNewData(poDataModelList);
                    adapter.notifyDataSetChanged();
                } else {
                    poDataModelList.removeAll(poDataModelList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GeneratedListCompleteResponse> call, Throwable t) {
                poDataModelList.removeAll(poDataModelList);
                adapter.notifyDataSetChanged();

            }
        });

    }

    private void getGeneratedListCompleteData(String year) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //Call<GeneratedListCompleteResponse> call=service.getGeneratedListComplete("2020-21");
        Call<GeneratedListCompleteResponse> call = service.getGeneratedListComplete(year);
        call.enqueue(new Callback<GeneratedListCompleteResponse>() {
            @Override
            public void onResponse(Call<GeneratedListCompleteResponse> call, Response<GeneratedListCompleteResponse> response) {
                if (response != null && response.body() != null && response.body().getPoDataModelList() != null && !response.body().getPoDataModelList().isEmpty() && response.body().getPoDataModelList().size() > 0) {
                    poDataModelList = response.body().getPoDataModelList();
                    setAdapter(poDataModelList);
                } else {
                    poDataModelList.removeAll(poDataModelList);
                    setAdapter(response.body().getPoDataModelList());
                }

            }

            @Override
            public void onFailure(Call<GeneratedListCompleteResponse> call, Throwable t) {

            }
        });
    }

    //    private void getGeneratedListData(){
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<GeneratedListResponse> call=service.getGeneratedList();
//        //Call<GeneratedListCompleteResponse> call=service.getGeneratedListComplete("2020-21");
//        // Call<VendorListResponse> call=service.getVendorList_Page(page);
//
//        call.enqueue(new Callback<GeneratedListResponse>() {
//            @Override
//            public void onResponse(Call<GeneratedListResponse> call, Response<GeneratedListResponse> response) {
//                poDataModelList=response.body().getPoDataModelList();
//                setAdapter(poDataModelList);
//            }
//
//            @Override
//            public void onFailure(Call<GeneratedListResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
    private void setAdapter(List<PODataModel> vendorDataModelList) {
        adapter = new GeneratedListAdapter(getContext(), this, vendorDataModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }

    private void setYearsSpinnerData() {
        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, yearsString);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearsSpinner.setAdapter(aa);
        YearsSpinner.setSelection(0);

        YearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String year = yearsString[YearsSpinner.getSelectedItemPosition()];
                //getInvoiceListData(year);
                getGeneratedListCompleteData(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}