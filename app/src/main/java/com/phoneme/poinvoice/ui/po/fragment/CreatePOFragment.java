package com.phoneme.poinvoice.ui.po.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phoneme.poinvoice.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreatePOFragment extends Fragment {

    private EditText dob,poNumber,deliveryDate,invoiceDate,Subject,gstPercentage,termsConditions;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private Button Submit;
    private Spinner Company,Vendor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_po, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob=(EditText)view.findViewById(R.id.invoice_date);
        dob.setText(sdf.format(myCalendar.getTime()));
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                //alldates.clear();
                updateLabel(year,monthOfYear,dayOfMonth);

            }

            private void updateLabel(int year,int monthOfYear,int dayOfMonth) {
                Log.v("TAG","MONTH:"+monthOfYear);
                String temp;
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                //Toast.makeText(getApplicationContext(), "My calendar"+myCalendar.getTime(), Toast.LENGTH_LONG).show();
                dob.setText(sdf.format(myCalendar.getTime()));
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog=  new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                dialog.show();

                ;
                // Toast.makeText(getApplicationContext(), "In On click", Toast.LENGTH_LONG).show();
            }
        });

        Company=(Spinner)view.findViewById(R.id.company);
        Vendor=(Spinner)view.findViewById(R.id.vendor);
        Submit=(Button)view.findViewById(R.id.submit);

        poNumber=(EditText)view.findViewById(R.id.po_number);
        deliveryDate=(EditText)view.findViewById(R.id.delievery_date);
        invoiceDate=(EditText)view.findViewById(R.id.invoice_date);
        Subject=(EditText)view.findViewById(R.id.subject);
        gstPercentage=(EditText)view.findViewById(R.id.gst_percentage);
        termsConditions=(EditText)view.findViewById(R.id.terms_conditions);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData(){
        String ponumber,deliverydate,invoicedate,subject,gstpercetage,termscondtion,vendor,company;


        if(poNumber!=null && poNumber.getText()!=null && poNumber.getText().length()>0){
            ponumber=poNumber.getText().toString();
        }

        if(deliveryDate!=null && deliveryDate.getText()!=null && deliveryDate.getText().length()>0){
            deliverydate=deliveryDate.getText().toString();
        }

        if(invoiceDate!=null && invoiceDate.getText()!=null && invoiceDate.getText().length()>0){

            invoicedate=invoiceDate.getText().toString();
        }

        if(Subject!=null && Subject.getText()!=null && Subject.getText().length()>0){

            subject=Subject.getText().toString();
        }

        if(gstPercentage!=null && gstPercentage.getText()!=null && gstPercentage.getText().length()>0){

            gstpercetage=gstPercentage.getText().toString();
        }

        if(termsConditions!=null && termsConditions.getText()!=null && termsConditions.getText().length()>0){
            termscondtion=termsConditions.getText().toString();
        }

        vendor=Vendor.getSelectedItem().toString();
        company=Company.getSelectedItem().toString();
    }
}
