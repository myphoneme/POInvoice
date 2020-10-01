package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.phoneme.poinvoice.ui.invoice.model.InvoiceRowModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;
import com.phoneme.poinvoice.ui.po.network.VendorListResponse;
import com.phoneme.poinvoice.ui.po.viewmodel.VendorListViewModel;
import com.phoneme.poinvoice.ui.po.adapter.VendorListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorListFragment extends Fragment implements VendorListAdapter.OnItemClickListener{

    private VendorListViewModel vendorListViewModel;
    private RecyclerView recyclerView;
    private int Page=1;
    private List<VendorDataModel> vendorDataModelList;

    private EditText searchEdit;
    private VendorListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vendorListViewModel =
                ViewModelProviders.of(this).get(VendorListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vendorlist, container, false);
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

//        VendorListAdapter adapter=new VendorListAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
        getVendorListData(Page);
        Button button=(Button)view.findViewById(R.id.add_vendor_button);

        Button searchButton = (Button) view.findViewById(R.id.search_button);
        searchEdit = (EditText) view.findViewById(R.id.search_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_vendor_add);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchEdit.getText().toString();
                getVendorListSearchData(searchString);
            }
        });
    }

    public void onItemClick(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.vendorDataModelList.get(position).getId());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_vendor_edit,args2);
    }
    public void onItemClick2(int position){

    }

    private void getVendorListSearchData(String search){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorListResponse> call=service.getVendorList();//This api will change when search api is created
        call.enqueue(new Callback<VendorListResponse>() {
            @Override
            public void onResponse(Call<VendorListResponse> call, Response<VendorListResponse> response) {
                vendorDataModelList.removeAll(  vendorDataModelList);
                vendorDataModelList=response.body().getVendordata();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<VendorListResponse> call, Throwable t) {
                vendorDataModelList.removeAll(  vendorDataModelList);
                adapter.notifyDataSetChanged();
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
                setAdapter(vendorDataModelList);
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

    private void setAdapter(List<VendorDataModel> vendorDataModelList){
        adapter=new VendorListAdapter(getContext(),this,vendorDataModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }
}