package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phoneme.poinvoice.R;

public class VendorAddFragment extends Fragment {
    private Button Submit;
    private EditText vendorName,Address,Email,GSTIN,PAN;
    private Spinner State;

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
    }

    private void getData(){
        String vendorString,AddressString,EmailString,GSTINString,PANString,StateString;

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
    }
}
