package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.adapter.RegionWiseRevenueListAdapter;
import com.phoneme.poinvoice.ui.po.adapter.VendorListAdapter;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;
import com.phoneme.poinvoice.ui.po.network.RegionRevenueGetResponse;
import com.phoneme.poinvoice.ui.po.network.VendorListResponse;
import com.phoneme.poinvoice.ui.po.viewmodel.VendorListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionWiseRevenueListFragment extends Fragment implements RegionWiseRevenueListAdapter.OnItemClickListener{

    private VendorListViewModel vendorListViewModel;
    private RecyclerView recyclerView;
    private Spinner YearsSpinner;
    private int Page=1;
    private List<VendorDataModel> vendorDataModelList;
    private List<PODataModel> poDataModelList;
    String[] yearsString = {"2020-21","2019-20","2018-19","2017-18"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vendorListViewModel =
                ViewModelProviders.of(this).get(VendorListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_regionwise_revenuse_list, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        vendorListViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_vendor_list);
        YearsSpinner=(Spinner)view.findViewById(R.id.years);

//        VendorListAdapter adapter=new VendorListAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
        //getVendorListData(Page);
        Button button=(Button)view.findViewById(R.id.add_vendor_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                //navController.navigate(R.id.nav_vendor_add);
                navController.navigate(R.id.nav_create_po);
            }
        });

        setYearsSpinnerData();
    }

    public void onItemClick(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.vendorDataModelList.get(position).getId());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_vendor_edit,args2);
    }
    public void onItemClick2(int position){

    }
    private void getRegionwiseData(String year){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<RegionRevenueGetResponse> call= service. getRegionRevenueListComplete(year);
        call.enqueue(new Callback<RegionRevenueGetResponse>() {
            @Override
            public void onResponse(Call<RegionRevenueGetResponse> call, Response<RegionRevenueGetResponse> response) {
                poDataModelList=response.body().getPoDataModelList();
                setAdapterRegionWise(poDataModelList);
            }

            @Override
            public void onFailure(Call<RegionRevenueGetResponse> call, Throwable t) {

            }
        });
    }
    private void getVendorListData(final int page){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorListResponse> call=service.getVendorList();
       // Call<VendorListResponse> call=service.getVendorList_Page(page);

        call.enqueue(new Callback<VendorListResponse>() {
            @Override
            public void onResponse(Call<VendorListResponse> call, Response<VendorListResponse> response) {
                vendorDataModelList=response.body().getVendordata();
                //setAdapter(vendorDataModelList);
//                if(page==1){
//                    vendorDataModelList=response.body().getVendordata();
//                    setAdapter(vendorDataModelList);
//                }else{
//                    addNewData(response.body().getVendordata());
//                }

            }

            @Override
            public void onFailure(Call<VendorListResponse> call, Throwable t) {

            }
        });

    }

    private void addNewData(List<VendorDataModel> vendorDataModelListNew){
        for(int i=0;i<vendorDataModelListNew.size();i++){
            vendorDataModelList.add(vendorDataModelListNew.get(i));
        }

    }

    private void setAdapterRegionWise(List<PODataModel> poDataModels){
        RegionWiseRevenueListAdapter adapter=new RegionWiseRevenueListAdapter(getContext(),poDataModels);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }

//    private void setAdapter(List<VendorDataModel> vendorDataModelList){
//        VendorListAdapter adapter=new VendorListAdapter(getContext(),this,vendorDataModelList);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
//
//    }

    private void setYearsSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,yearsString);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearsSpinner.setAdapter(aa);
        YearsSpinner.setSelection(0);

        YearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String year=yearsString[YearsSpinner.getSelectedItemPosition()];
                getRegionwiseData(year);
                //getInvoiceListData(year);
                //getGeneratedListCompleteData(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}