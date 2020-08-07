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

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.network.VendorAddPostResponse;
import com.phoneme.poinvoice.ui.po.model.StateModelData;
import com.phoneme.poinvoice.ui.po.network.VendorAddGetResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorAddFragment extends Fragment {
    private Button Submit;
    private EditText vendorName,Address,Email,GSTIN,PAN;
    private Spinner State;
    private List<StateModelData> stateModelDataList;
    private ArrayList<String> states=new ArrayList<String>();
    HashMap<String,String> state_stateid_Map = new HashMap<String, String>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_vendor, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        getVendorAddData();
    }

    private void getData(){
        String vendorString=new String(),AddressString=new String(),EmailString=new String(),GSTINString=new String(),PANString=new String(),StateString=new String();

        if(vendorName!=null && vendorName.getText()!=null && vendorName.getText().length()>0){
            vendorString=vendorName.getText().toString();
        }else{
            Toast.makeText(getContext(),"Vendor is must",Toast.LENGTH_LONG).show();
            return ;
        }
        if(Address!=null && Address.getText()!=null && Address.getText().length()>0){
            AddressString=Address.getText().toString();
        }else{
            Toast.makeText(getContext(),"Address is must",Toast.LENGTH_LONG).show();
            return ;
        }
        if(Email!=null && Email.getText()!=null && Email.getText().length()>0){
            EmailString=Email.getText().toString();
        }else{
            Toast.makeText(getContext(),"Email is must",Toast.LENGTH_LONG).show();
            return ;
        }
        if(GSTIN!=null && GSTIN.getText()!=null && GSTIN.getText().length()>0){
            GSTINString=GSTIN.getText().toString();
        }else{
            Toast.makeText(getContext(),"GSTIN is must",Toast.LENGTH_LONG).show();
            return ;
        }
        if(PAN!=null && PAN.getText()!=null && PAN.getText().length()>0){
            PANString=PAN.getText().toString();
        }else{
            Toast.makeText(getContext(),"PAN is must",Toast.LENGTH_LONG).show();
            return ;
        }
        StateString=State.getSelectedItem().toString();
        String stateid=state_stateid_Map.get(StateString);
        Map<String,String> map=new HashMap<String,String>();
        map.put("Vendor_name",vendorString);

        map.put("Address",AddressString);
        map.put("email",EmailString);
        map.put("State",StateString);
        map.put("state_id",stateid);
        map.put("GSTIN",GSTINString);
        map.put("PAN",PANString);
        postVendorData(map);
    }

    private void getVendorAddData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorAddGetResponse> call=service.getVendorAddData();
        call.enqueue(new Callback<VendorAddGetResponse>() {
            @Override
            public void onResponse(Call<VendorAddGetResponse> call, Response<VendorAddGetResponse> response) {
                stateModelDataList=response.body().getStateModelDataList();
                setStates(response.body().getStateModelDataList());
                setSpinnerData();
            }

            @Override
            public void onFailure(Call<VendorAddGetResponse> call, Throwable t) {

            }
        });
    }

    private void postVendorData(Map<String ,String> map){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<VendorAddPostResponse> call=service.postVendorAdd(map);
        call.enqueue(new Callback<VendorAddPostResponse>() {
            @Override
            public void onResponse(Call<VendorAddPostResponse> call, Response<VendorAddPostResponse> response) {
                Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<VendorAddPostResponse> call, Throwable t) {

            }
        });
    }

    private void setStates(List<StateModelData> stateModelDataList){
        for(int i=0;i<stateModelDataList.size();i++) {
            if (stateModelDataList.get(i).getState_Name()!=null){
                states.add(stateModelDataList.get(i).getState_Name());
                state_stateid_Map.put(stateModelDataList.get(i).getState_Name(),stateModelDataList.get(i).getState_Code());

            }
        }
    }
    private void setSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,states);

//        aa.
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
        State.setAdapter(aa);
        State.setSelection(0);
    }
}
