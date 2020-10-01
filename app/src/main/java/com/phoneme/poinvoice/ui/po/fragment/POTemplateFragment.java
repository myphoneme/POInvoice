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
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.network.PoTemplateListResponse;
import com.phoneme.poinvoice.ui.po.network.VendorListResponse;
import com.phoneme.poinvoice.ui.po.viewmodel.POTemplateViewModel;
import com.phoneme.poinvoice.ui.po.adapter.POTemplateAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class POTemplateFragment extends Fragment implements POTemplateAdapter.OnItemClickListener{

    private com.phoneme.poinvoice.ui.po.viewmodel.POTemplateViewModel POTemplateViewModel;
    private RecyclerView recyclerView;
    private List<PoTemplateDataModel> poTemplateDataModelList;
    private EditText searchEdit;
    private POTemplateAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        POTemplateViewModel =
//                ViewModelProviders.of(this).get(POTemplateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_potemplate, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_po_template);
        Button createpotemplate=(Button)view.findViewById(R.id.add_new_po_template);
        Button searchButton = (Button) view.findViewById(R.id.search_button);
        searchEdit = (EditText) view.findViewById(R.id.search_text);

        createpotemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_create_new_po_templatge);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = searchEdit.getText().toString();
                getPoTemplateListSearchData(searchString);
            }
        });
        getPoTemplateListData();
        //POTemplateAdapter adapter=new POTemplateAdapter(getContext());
//        POTemplateAdapter adapter=new POTemplateAdapter(getContext(),this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearVertical);
    }

    public void onItemClick(int position){
        Bundle args2 = new Bundle();
        args2.putString("id",this.poTemplateDataModelList.get(position).getId());

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_po_template_edit,args2);
    }
    public void onItemClick2(int position){}

    private void setAdapter(List<PoTemplateDataModel> poTemplateDataModelList){
        adapter=new POTemplateAdapter(getContext(),this,poTemplateDataModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }
    private void getPoTemplateListSearchData(String search){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<PoTemplateListResponse> call=service.getPoTemplateList();//to be changed where search api is created
        call.enqueue(new Callback<PoTemplateListResponse>() {
            @Override
            public void onResponse(Call<PoTemplateListResponse> call, Response<PoTemplateListResponse> response) {
                poTemplateDataModelList.removeAll( poTemplateDataModelList);
                poTemplateDataModelList=response.body().getPotemplate_data();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PoTemplateListResponse> call, Throwable t) {
                poTemplateDataModelList.removeAll( poTemplateDataModelList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getPoTemplateListData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<PoTemplateListResponse> call=service.getPoTemplateList();


        call.enqueue(new Callback<PoTemplateListResponse>() {
            @Override
            public void onResponse(Call<PoTemplateListResponse> call, Response<PoTemplateListResponse> response) {
                poTemplateDataModelList=response.body().getPotemplate_data();

                setAdapter(poTemplateDataModelList);

            }

            @Override
            public void onFailure(Call<PoTemplateListResponse> call, Throwable t) {

            }
        });

    }
}