package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.adapter.ClientListAdapter;
import com.phoneme.poinvoice.ui.invoice.model.ClientDataModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceRowModel;
import com.phoneme.poinvoice.ui.invoice.network.ClientListGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceListResponse;
import com.phoneme.poinvoice.ui.po.fragment.CreatePOFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListFragment extends Fragment implements ClientListAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private List<ClientDataModel> clientDataModelList;


    private Button CreateClient;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_client_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CreateClient=(Button)view.findViewById(R.id.create_client);
        CreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_client_create);
            }
        });
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_client_list);
        getClientListData();
//        setAdapter();
    }

    private void setAdapter(List<ClientDataModel> clientDataModelList){

        //ClientListAdapter adapter=new ClientListAdapter(getContext());
        ClientListAdapter adapter=new ClientListAdapter(getContext(),this,clientDataModelList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }

    private void getClientListData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ClientListGetResponse> call=service.getClientList();
        call.enqueue(new Callback<ClientListGetResponse>() {
            @Override
            public void onResponse(Call<ClientListGetResponse> call, Response<ClientListGetResponse> response) {
                if(response!=null && response.body()!=null && response.body().getClientDataModelList()!=null){
                    clientDataModelList=response.body().getClientDataModelList();
                    setAdapter(clientDataModelList);
                }

            }

            @Override
            public void onFailure(Call<ClientListGetResponse> call, Throwable t) {

            }
        });

    }
    public void onItemClick(int position){
        Bundle args = new Bundle();
        String id=clientDataModelList.get(position).getId();
        args.putString("id",id);//To be changed to dynamic data
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.nav_client_edit,args);

    }
    public void onItemClick2(int position){}
    public void onItemClick3(int position){}
}
