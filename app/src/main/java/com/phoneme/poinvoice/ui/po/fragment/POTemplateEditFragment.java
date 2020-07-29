package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phoneme.poinvoice.R;

public class POTemplateEditFragment extends Fragment {

    private Button submit;
    private EditText date,PONumber,POAmount;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_po_template, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date=(EditText)view.findViewById(R.id.po_date);
        PONumber=(EditText)view.findViewById(R.id.po_number);
        POAmount=(EditText)view.findViewById(R.id.po_amount);
        submit=(Button)view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){
        String poNumber,poAmount,Date;
        if(PONumber!=null && PONumber.getText()!=null && PONumber.getText().length()>0){
            poNumber=PONumber.getText().toString();
        }

        if(POAmount!=null && POAmount.getText()!=null && POAmount.getText().length()>0){
            poAmount=POAmount.getText().toString();
        }

        if(PONumber!=null && PONumber.getText()!=null && PONumber.getText().length()>0){
            poNumber=PONumber.getText().toString();
        }

        if(date!=null && date.getText()!=null && date.getText().length()>0){

            Date=date.getText().toString();
        }


    }
}
