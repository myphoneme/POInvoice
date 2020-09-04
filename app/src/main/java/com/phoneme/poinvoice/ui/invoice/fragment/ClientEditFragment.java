package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.model.ClientDataModel;
import com.phoneme.poinvoice.ui.invoice.network.ClientEditGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.ClientEditPostResponse;
import com.phoneme.poinvoice.ui.invoice.network.ClientListGetResponse;
import com.phoneme.poinvoice.ui.po.model.StateModelData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientEditFragment extends Fragment {
    private ClientDataModel clientDataModel;
    private List<StateModelData> stateModelDataList;


    private ArrayList<String> states=new ArrayList<String>();
    HashMap<String,String> state_stateid_Map = new HashMap<String, String>();
    HashMap<String,String> stateid_state_Map = new HashMap<String, String>();

    private EditText ClientNameEdit,WebsiteUrlEdit,AddressEdit,EmailEdit,GSTINEdit,PANEdit;

    private TextView AddressText;

    private Button Submit;
    private Spinner StateSpinner;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_client_edit, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");
        ClientNameEdit=view.findViewById(R.id.client_name);
        WebsiteUrlEdit=view.findViewById(R.id.website_url);
        //AddressEdit=view.findViewById(R.id.address);
        EmailEdit=view.findViewById(R.id.email);
        GSTINEdit=view.findViewById(R.id.gstin_no);
        PANEdit =view.findViewById(R.id.pan);

        AddressText=(TextView)view.findViewById(R.id.address);

        StateSpinner=(Spinner)view.findViewById(R.id.state);

        Submit=(Button)view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Button clicked",Toast.LENGTH_LONG).show();
                getLocalData();
            }
        });
        getData(id);
        //recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_client_list);
        //getClientListData();
//        setAdapter();
    }

    private void getData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ClientEditGetResponse> call=service.getClientEditData(id);
        call.enqueue(new Callback<ClientEditGetResponse>() {
            @Override
            public void onResponse(Call<ClientEditGetResponse> call, Response<ClientEditGetResponse> response) {
                if(response!=null && response.isSuccessful() && response.body()!=null ){
                    if(response.body().getClientDataModel()!=null){
                        clientDataModel=response.body().getClientDataModel();
                        stateModelDataList = response.body().getStateModelData();
                        setStates(stateModelDataList);
                        setSpinnerData();
                        setOriginalData(clientDataModel);
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientEditGetResponse> call, Throwable t) {

            }
        });
    }

    private void setStates(List<StateModelData> stateModelDataList){
        for(int i=0;i<stateModelDataList.size();i++) {
            if (stateModelDataList.get(i).getState_Name()!=null){
                states.add(stateModelDataList.get(i).getState_Name());
                state_stateid_Map.put(stateModelDataList.get(i).getState_Name(),stateModelDataList.get(i).getState_Code());
                stateid_state_Map.put(stateModelDataList.get(i).getState_Code(),stateModelDataList.get(i).getState_Name());
            }
        }
    }

    private void setSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,states);

//        aa.
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
        String state_le = clientDataModel.getState_name();
        int position=0;
        for(int i=0;i<states.size();i++){
            if(state_le.equalsIgnoreCase(states.get(i))){
                position=i;
                break;
            }
        }
        StateSpinner.setAdapter(aa);
        StateSpinner.setSelection(position);
    }
    private void setOriginalData(ClientDataModel clientDataModel){
        ClientNameEdit.setText(clientDataModel.getClient_name());

        WebsiteUrlEdit.setText(clientDataModel.getWebsite());
        //AddressEdit.setText(clientDataModel.getAddress());
        EmailEdit.setText(clientDataModel.getEmail());
        GSTINEdit.setText(clientDataModel.getGSTIN());
        PANEdit.setText(clientDataModel.getPan());

        AddressText.setText(clientDataModel.getAddress());
    }

    private void getLocalData(){
        String clientNameString=ClientNameEdit.getText().toString();
        String websiteString=WebsiteUrlEdit.getText().toString();
        String emailString=EmailEdit.getText().toString();
        String gstinString=GSTINEdit.getText().toString();
        String panString=PANEdit.getText().toString();

        String stateString=StateSpinner.getSelectedItem().toString();
        String stateid=state_stateid_Map.get(stateString);

        Map<String,String> map=new HashMap<String,String>();
        map.put("Id",clientDataModel.getId());
        map.put("Client_name",clientNameString);
        map.put("Website",websiteString);
        map.put("email",emailString);
        map.put("state_id",stateid);
        map.put("GSTIN",gstinString);
        map.put("PAN",panString);

        postClientEditData(map);
    }

    private void postClientEditData(Map<String,String> map){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ClientEditPostResponse> call=service.postClientEdit(map);
        call.enqueue(new Callback<ClientEditPostResponse>() {
            @Override
            public void onResponse(Call<ClientEditPostResponse> call, Response<ClientEditPostResponse> response) {
                if(response!=null && response.isSuccessful() && response.body()!=null){
                    if(response.body().isUpdate()){
                        Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.popBackStack();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientEditPostResponse> call, Throwable t) {

            }
        });
    }
}
