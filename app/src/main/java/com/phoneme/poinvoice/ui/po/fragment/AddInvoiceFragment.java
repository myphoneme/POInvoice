package com.phoneme.poinvoice.ui.po.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.network.InvoiceAddPostResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInvoiceFragment extends Fragment {

    private EditText dob,invoiceNumber,invoiceAmount;
    private TextView PO_number;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private Button submit,fileSelect;
    String id;

    private static final int PERMISSION_STORAGE_CODE=1000;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_add_invoice, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
        String po_number=getArguments().getString("po_number");

        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob=(EditText)view.findViewById(R.id.invoice_date);
        dob.setText(sdf.format(myCalendar.getTime()));

        submit=(Button)view.findViewById(R.id.submit);
        invoiceNumber=(EditText)view.findViewById(R.id.invoice_number);
        invoiceAmount=(EditText)view.findViewById(R.id.invoice_amount);
        PO_number=(TextView)view.findViewById(R.id.po_number);
        fileSelect=(Button)view.findViewById(R.id.upload);
        PO_number.setText(po_number);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Submit Button clicked",Toast.LENGTH_LONG).show();
                getData();
            }
        });

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

        fileSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                    }else{
//                        new DownloadFileFromURL().execute(file_url);
                        uploadStartFunc();
                    }
                }else{
//                    new DownloadFileFromURL().execute(file_url);
                    uploadStartFunc();
                }
            }
        });
    }

    private void getData(){
        String dateString=new String(),invoiceNumberString=new String(),invoiceAmountString=new String();
        if(dob!=null && dob.getText()!=null && dob.getText().length()>0){
            dateString=dob.getText().toString();
        }

        if(  invoiceNumber!=null &&   invoiceNumber.getText()!=null &&   invoiceNumber.getText().length()>0){
            invoiceNumberString = invoiceNumber.getText().toString();
        }
        if(  invoiceAmount!=null &&   invoiceAmount.getText()!=null &&   invoiceAmount.getText().length()>0){
            invoiceAmountString = invoiceAmount.getText().toString();
        }

        Map<String,String> map=new HashMap<String,String>();
        map.put("id",id);
        map.put("invoicedate",dateString);
        map.put("invoice_number",invoiceNumberString);
        map.put("invoice_amount",invoiceAmountString);

        postAddInvoiceData(map);
    }

    private void postAddInvoiceData(Map<String ,String> map){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<InvoiceAddPostResponse> call=service.postInvoiceAdd(map);
        call.enqueue(new Callback<InvoiceAddPostResponse>() {
            @Override
            public void onResponse(Call<InvoiceAddPostResponse> call, Response<InvoiceAddPostResponse> response) {
                Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack();

            }

            @Override
            public void onFailure(Call<InvoiceAddPostResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_LONG).show();

            }
        });

    }

    private void uploadStartFunc(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case PERMISSION_STORAGE_CODE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //new DownloadFileFromURL().execute(file_url);
                    uploadStartFunc();
                }else{
                    //Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();
                }
        }
    }
}
