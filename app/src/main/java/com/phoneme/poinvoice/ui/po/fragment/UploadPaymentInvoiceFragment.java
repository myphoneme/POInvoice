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
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceModel;
import com.phoneme.poinvoice.ui.invoice.network.UPloadPOPaymentGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.UploadPOGetResponse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPaymentInvoiceFragment extends Fragment {

    private EditText dob;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private EditText Message,transaction,Payment;
    private Button Submit,uploadfile;
    private TextView file_name,PO_Number;
    private String id;
    private InvoiceModel invoiceModel;

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
        id = getArguments().getString("id");
        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Submit=(Button)view.findViewById(R.id.submit);
        PO_Number=(TextView)view.findViewById(R.id.po_number);
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
        getPaymentData(id);
    }

    private void getData(){
        String transactionid=new String(),payment,message;
        if(transaction!=null && transaction.getText()!=null && transaction.getText().length()>0){
            transactionid=transaction.getText().toString();
        }

        if(Message!=null && Message.getText()!=null && Message.getText().length()>0){
            message=Message.getText().toString();
        }
        if(Payment!=null && Payment.getText()!=null && Payment.getText().length()>0){
            payment=Payment.getText().toString();
        }

        HashMap<String,RequestBody> map= new HashMap<>();

        RequestBody TransactionId=createPartFromString(transactionid);
        map.put("transactionid",TransactionId);

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

    private void getPaymentData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<UPloadPOPaymentGetResponse> call=service.getPOPaymentUploadData(id);
        call.enqueue(new Callback<UPloadPOPaymentGetResponse>() {
            @Override
            public void onResponse(Call<UPloadPOPaymentGetResponse> call, Response<UPloadPOPaymentGetResponse> response) {
                if(response!=null && response.body()!=null ){
                    invoiceModel=response.body().getInvoiceModelList().get(0);
                    PO_Number.setText(invoiceModel.getInvoice_number());
                }

            }

            @Override
            public void onFailure(Call<UPloadPOPaymentGetResponse> call, Throwable t) {

            }
        });
    }

}
