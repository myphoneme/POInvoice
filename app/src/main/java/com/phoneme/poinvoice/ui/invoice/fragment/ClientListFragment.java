package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListFragment extends Fragment implements ClientListAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private List<ClientDataModel> clientDataModelList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_client_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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


    }
    public void onItemClick2(int position){}
    public void onItemClick3(int position){}
}
