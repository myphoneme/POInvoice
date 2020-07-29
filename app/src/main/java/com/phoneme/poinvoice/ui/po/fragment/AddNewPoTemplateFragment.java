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

public class AddNewPoTemplateFragment extends Fragment {

    private Button Submit;
    private EditText templateTitle,templateName,addressLine1,addressLine2,addressLine3,gstNumber;
    private Spinner State;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_new_po_template, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Submit=(Button)view.findViewById(R.id.submit);
        templateTitle=(EditText)view.findViewById(R.id.template_title);
        templateName=(EditText)view.findViewById(R.id.template_name);
        addressLine1=(EditText)view.findViewById(R.id.address_line1);
        addressLine2=(EditText)view.findViewById(R.id.address_line2);
        addressLine3=(EditText)view.findViewById(R.id.address_line3);
        State=(Spinner)view.findViewById(R.id.state);
        gstNumber=(EditText)view.findViewById(R.id.gstn_no);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){
        String templatetitle,templatename,addressline1,addressline2,addressline3,state,gstnumber;
        if(templateTitle!=null && templateTitle.getText()!=null && templateTitle.getText().length()>0){
            templatetitle=templateTitle.getText().toString();
        }

        if(templateName!=null && templateName.getText()!=null && templateName.getText().length()>0){
            templatename =templateName.getText().toString();
        }


        if(addressLine1!=null && addressLine1.getText()!=null && addressLine1.getText().length()>0){
            addressline1=addressLine1.getText().toString();
        }

        if(addressLine2!=null && addressLine2.getText()!=null && addressLine2.getText().length()>0){
            addressline2=addressLine2.getText().toString();
        }

        if(addressLine3!=null && addressLine3.getText()!=null && addressLine3.getText().length()>0){
            addressline3=addressLine3.getText().toString();
        }

        if(gstNumber!=null && gstNumber.getText()!=null && gstNumber.getText().length()>0){
            gstnumber=gstNumber.getText().toString();
        }
        state=State.getSelectedItem().toString();
    }
}
