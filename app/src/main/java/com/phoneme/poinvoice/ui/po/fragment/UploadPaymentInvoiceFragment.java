package com.phoneme.poinvoice.ui.po.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.phoneme.poinvoice.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadPaymentInvoiceFragment extends Fragment {

    private EditText dob;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private EditText Message,transaction,Payment;
    private Button Submit,uploadfile;
    private TextView file_name;

    private String imagePath=new String();
    private boolean imageSelected = false;


    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_new_payment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Submit=(Button)view.findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        Message=(EditText)view.findViewById(R.id.message);
        transaction=(EditText)view.findViewById(R.id.transaction_id);
        Payment=(EditText)view.findViewById(R.id.payment_amount);
        uploadfile=(Button)view.findViewById(R.id.upload);
        file_name=(TextView)view.findViewById(R.id.file_name);
        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

//        dob=(EditText)view.findViewById(R.id.invoice_date);
//        dob.setText(sdf.format(myCalendar.getTime()));
//        date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                //alldates.clear();
//                updateLabel(year,monthOfYear,dayOfMonth);
//
//            }
//
//            private void updateLabel(int year,int monthOfYear,int dayOfMonth) {
//                Log.v("TAG","MONTH:"+monthOfYear);
//                String temp;
//                String myFormat = "MM/dd/yyyy"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                //Toast.makeText(getApplicationContext(), "My calendar"+myCalendar.getTime(), Toast.LENGTH_LONG).show();
//                dob.setText(sdf.format(myCalendar.getTime()));
//            }
//        };
//        dob.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                DatePickerDialog dialog=  new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH));
//                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//
//                dialog.show();
//
//                ;
//                // Toast.makeText(getApplicationContext(), "In On click", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void getData(){
        String transactionid,payment,message;
        if(transaction!=null && transaction.getText()!=null && transaction.getText().length()>0){
            transactionid=transaction.getText().toString();
        }

        if(Message!=null && Message.getText()!=null && Message.getText().length()>0){
            message=Message.getText().toString();
        }
        if(Payment!=null && Payment.getText()!=null && Payment.getText().length()>0){
            payment=Payment.getText().toString();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == getActivity().RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                //userimage.setImageURI(selectedImage);
                //file_name.setText(selectedImage.g);
                imageSelected = true;

                imagePath=getRealPathFromURI(selectedImage);
                File file=new File(imagePath);
                file_name.setText("file.getName()");

            }
        } catch (Exception e) {
            //Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
