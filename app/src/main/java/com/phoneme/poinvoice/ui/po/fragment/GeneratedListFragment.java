package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class GeneratedListFragment extends Fragment implements GeneratedListAdapter.OnItemClickListener{

    private GeneratedListViewModel generatedListViewModel;
    private RecyclerView recyclerView;
    private Button generateNewPOButton;
    private List<PODataModel> poDataModelList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        generatedListViewModel =
//                ViewModelProviders.of(this).get(GeneratedListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_generatedlist, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_generated_list);
        generateNewPOButton=(Button)view.findViewById(R.id.add_vendor_button);
        generateNewPOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_po);
            }
        });
        //GeneratedListAdapter adapter=new GeneratedListAdapter(getContext());
        //getGeneratedListData();
        getGeneratedListCompleteData();
//        GeneratedListAdapter adapter=new GeneratedListAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
    }
    public void onItemClick(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.poDataModelList.get(position).getId());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_generated_list_payment_upload,args2);
    }
    public void onItemClick2(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.poDataModelList.get(position).getId());
        args2.putString("po_number",this.poDataModelList.get(position).getPo_number());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_invoice_add_upload,args2);
    }
    public void onItemClick3(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.poDataModelList.get(position).getId());
        args2.putString("organization",this.poDataModelList.get(position).getOrganization());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_po_final_funnel_data);
    }
    private void getGeneratedListCompleteData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListCompleteResponse> call=service.getGeneratedListComplete("2020-21");
        call.enqueue(new Callback<GeneratedListCompleteResponse>() {
            @Override
            public void onResponse(Call<GeneratedListCompleteResponse> call, Response<GeneratedListCompleteResponse> response) {
                poDataModelList=response.body().getPoDataModelList();
                setAdapter(poDataModelList);
            }

            @Override
            public void onFailure(Call<GeneratedListCompleteResponse> call, Throwable t) {

            }
        });
    }
    private void getGeneratedListData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListResponse> call=service.getGeneratedList();
        //Call<GeneratedListCompleteResponse> call=service.getGeneratedListComplete("2020-21");
        // Call<VendorListResponse> call=service.getVendorList_Page(page);

        call.enqueue(new Callback<GeneratedListResponse>() {
            @Override
            public void onResponse(Call<GeneratedListResponse> call, Response<GeneratedListResponse> response) {
                poDataModelList=response.body().getPoDataModelList();
                setAdapter(poDataModelList);
            }

            @Override
            public void onFailure(Call<GeneratedListResponse> call, Throwable t) {

            }
        });

    }
    private void setAdapter(List<PODataModel> vendorDataModelList){
        GeneratedListAdapter adapter=new GeneratedListAdapter(getContext(),this,vendorDataModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }
}