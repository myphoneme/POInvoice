package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.model.StateModelData;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;
import com.phoneme.poinvoice.ui.po.network.VendorEditGetResponse;
import com.phoneme.poinvoice.ui.po.network.VendorEditPostResponse;
import com.phoneme.poinvoice.ui.po.viewmodel.VendorListViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class VendorEditFragment extends Fragment {
    private Button Submit;
    private EditText vendorName,Address,Email,GSTIN,PAN;
    private Spinner State;
    private VendorDataModel vendorDataModel;
    private List<StateModelData> stateModelDataList;
    private ArrayList<String> states=new ArrayList<String>();
    HashMap<String,String> state_stateid_Map = new HashMap<String, String>();
    HashMap<String,String> stateid_state_Map = new HashMap<String, String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_edit_vendor, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");
        Submit=(Button)view.findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        //,
        vendorName=(EditText)view.findViewById(R.id.vendor_name);
        Address=(EditText)view.findViewById(R.id.address);
        Email=(EditText)view.findViewById(R.id.email);
        GSTIN=(EditText)view.findViewById(R.id.gstin_no);
        PAN=(EditText)view.findViewById(R.id.pan);
        State=(Spinner)view.findViewById(R.id.state);

        getVendorData(id);
    }

    private void getData(){
        String vendorString=new String(),AddressString=new String(),EmailString=new String(),GSTINString=new String(),PANString=new String(),StateString;

        if(vendorName!=null && vendorName.getText()!=null && vendorName.getText().length()>0){
            vendorString=vendorName.getText().toString();
        }
        if(Address!=null && Address.getText()!=null && Address.getText().length()>0){
            AddressString=Address.getText().toString();
        }
        if(Email!=null && Email.getText()!=null && Email.getText().length()>0){
            EmailString=Email.getText().toString();
        }
        if(GSTIN!=null && GSTIN.getText()!=null && GSTIN.getText().length()>0){
            GSTINString=GSTIN.getText().toString();
        }
        if(PAN!=null && PAN.getText()!=null && PAN.getText().length()>0){
            PANString=PAN.getText().toString();
        }
        StateString=State.getSelectedItem().toString();
        String stateid=state_stateid_Map.get(StateString);
        Map<String,String> map=new HashMap<String,String>();
        map.put("id",stateid);
        map.put("Vendor_name",vendorString);

        map.put("Address",AddressString);
        map.put("email",EmailString);

        map.put("State",StateString);
        map.put("state_id",stateid);

        map.put("GSTIN",GSTINString);
        map.put("PAM",PANString);

        postVendorEditData(map);
    }
    private void getVendorData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorEditGetResponse> call =service.getVendorEditData(id);
        call.enqueue(new Callback<VendorEditGetResponse>() {
            @Override
            public void onResponse(Call<VendorEditGetResponse> call, Response<VendorEditGetResponse> response) {
                vendorDataModel=response.body().getVendorDataModel();
                stateModelDataList=response.body().getStateModelDataList();
                setStates(stateModelDataList);
                setSpinnerData();
                setOriginalData();
            }

            @Override
            public void onFailure(Call<VendorEditGetResponse> call, Throwable t) {

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
        String state_le = vendorDataModel.getState();
        int position=0;
        for(int i=0;i<states.size();i++){
            if(state_le.equalsIgnoreCase(states.get(i))){
                position=i;
                break;
            }
        }
        State.setAdapter(aa);
        State.setSelection(position);
    }
    private void setOriginalData(){
        vendorName.setText(vendorDataModel.getVendor_name());
        Address.setText(vendorDataModel.getAddress());
        Email.setText(vendorDataModel.getEmail());
        GSTIN.setText(vendorDataModel.getGSTIN());
        PAN.setText(vendorDataModel.getPAM());

    }
    private void postVendorEditData(Map<String ,String> map){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorEditPostResponse> call=service.postVendorEdit(map);
        call.enqueue(new Callback<VendorEditPostResponse>() {
            @Override
            public void onResponse(Call<VendorEditPostResponse> call, Response<VendorEditPostResponse> response) {
                Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack();

            }

            @Override
            public void onFailure(Call<VendorEditPostResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Edit Failed",Toast.LENGTH_LONG).show();

            }
        });

    }
}
