package com.phoneme.poinvoice.ui.po.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceListResponse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.phoneme.poinvoice.ui.invoice.network.PoUploadPOSTResponse;
import com.phoneme.poinvoice.ui.invoice.network.UploadPOGetResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditPOSTResponse;

import java.util.List;
public class POUploadFragment extends Fragment {

  private EditText dob,PONumber,POAmount;
  Calendar myCalendar;
  DatePickerDialog.OnDateSetListener date;
  private String imagePath=new String();
  private boolean imageSelected = false;
  private Button fileUpload,Submit;
  private TextView InvoiceNumber;
  private String id;
  private static final int PERMISSION_STORAGE_CODE=1000;
  public static final String MULTIPART_FORM_DATA = "multipart/form-data";

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_upload_po, container, false);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    id = getArguments().getString("id");
    myCalendar = Calendar.getInstance();
    String myFormat = "MM/dd/yyyy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    dob=(EditText)view.findViewById(R.id.po_date);
    dob.setText(sdf.format(myCalendar.getTime()));
    fileUpload=(Button)view.findViewById(R.id.upload);
    fileUpload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent, 0);

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
    InvoiceNumber=(TextView)view.findViewById(R.id.invoice_number);
    PONumber=(EditText)view.findViewById(R.id.po_number);
    POAmount=(EditText)view.findViewById(R.id.po_amount);
    Submit=(Button)view.findViewById(R.id.submit);
    Submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getContext(),"Submit Button clicked",Toast.LENGTH_LONG).show();
        getData();
      }
    });
    getPOData(id);
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
        //individual_vaccine_dates="";
//                for(int k=0;k<vaccine_months.length;k++)
//                {
//                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                    myCalendar.set(Calendar.YEAR, year);
//                    myCalendar.set(Calendar.MONTH, monthOfYear);
//
//                    int temp1=Math.round(Float.parseFloat(vaccine_months[k])*30);
//                    Log.i("temp1",""+temp1);
//
//                    myCalendar.add(Calendar.DATE,temp1);
//                    temp=sdf.format(myCalendar.getTime());
//                    //&&01/10/2016&&D&&||&&01/11/2016&&P&&||&&01/12/2016&&P&&||&&01/07/2016&&D&&||&&01/04/2016&&D&&||&&01/02/2016&&P&&||&&01/01/2016&&D&&||&&01/12/2016&&D&&||&&01/12/2016&&P&&
//                   // individual_vaccine_dates+="&&"+temp+"&&P&&%%";
//
//                    myCalendar.set(Calendar.HOUR_OF_DAY, 13);
//                    myCalendar.set(Calendar.MINUTE, 12);
//                    Log.i("fog","for log"+sdf.format(myCalendar.getTime()));
//
//                    Calendar cal = Calendar.getInstance();
//                    cal = (Calendar)myCalendar.clone();
//                    alldates.add(cal);
//                    //For Local Notifications
//
//
//                }


//                individual_weight="";
//                individual_height="";


//                for(int k=0;k<baby_monthsnumbers.length;k++)
//                {
//
//                    //&&1.34&&||&&5.66&&||&&7.88&&||&&01/07/2016&&D&&||&&01/04/2016&&D&&||&&01/02/2016&&P&&||&&01/01/2016&&D&&||&&01/12/2016&&D&&||&&01/12/2016&&P&&
//                    individual_weight+="0&&";
//                    individual_height+="0&&";
//
//                }


        //Log.i("Final date String",individual_vaccine_dates);
        //Log.i("Weight",individual_weight);
        //Log.i("Height",individual_height);


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
  }

  private void uploadStartFunc(){
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);

    //galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
    galleryIntent.setType("*/*");

    startActivityForResult(galleryIntent, 0);
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
        //file_name.setText("file.getName()");
        Toast.makeText(getContext(), "Selected file name="+file.getName(), Toast.LENGTH_LONG).show();
      }
      Toast.makeText(getContext(), "Selected data="+data, Toast.LENGTH_LONG).show();

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

  private void getPOData(String id){
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    Call<UploadPOGetResponse> call=service.getPOUploadData(id);
    call.enqueue(new Callback<UploadPOGetResponse>() {
      @Override
      public void onResponse(Call<UploadPOGetResponse> call, Response<UploadPOGetResponse> response) {
        Toast.makeText(getContext(),"aisa",Toast.LENGTH_LONG).show();
        InvoiceNumber.setText(response.body().getInvoiceModelList().get(0).getInvoice_number());
      }

      @Override
      public void onFailure(Call<UploadPOGetResponse> call, Throwable t) {
        Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
      }
    });
  }

  private void getData(){
    String date= dob.getText().toString();
    String ponumber= PONumber.getText().toString();
    String poamount= POAmount.getText().toString();

    HashMap<String,RequestBody> map= new HashMap<>();

    RequestBody DATE=createPartFromString(date);
    map.put("podate",DATE);

    RequestBody PONUMBER=createPartFromString(ponumber);
    map.put("po_number",PONUMBER);

    RequestBody POAMOUNT=createPartFromString(poamount);
    map.put("po_amount",POAMOUNT);

    RequestBody ID=createPartFromString(id);
    map.put("id",ID);
    postDataWithImage(map);
  }

  private void postDataWithImage(HashMap<String,RequestBody> map){
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    File file;

    if (imagePath != null && !imagePath.isEmpty()) {
      //String newimagePath = compressImage(imagePath);
      //file = new File(newimagePath);
      file = new File(imagePath);//This one working

    }else{
      file = new File("");
    }

    final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("pofile", file.getName(), requestBody);//these 3 lines extra
    Call<PoUploadPOSTResponse> call;

    if(imageSelected){
      call=service.postPOUploadWithImage(body,map);
      Toast.makeText(getContext(),"image selected",Toast.LENGTH_LONG).show();
    }else{
      Toast.makeText(getContext(),"image not selected",Toast.LENGTH_LONG).show();
      call=service.postPOUploadWithoutImage(map);
    }

    call.enqueue(new Callback<PoUploadPOSTResponse>() {
      @Override
      public void onResponse(Call<PoUploadPOSTResponse> call, Response<PoUploadPOSTResponse> response) {
        Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.popBackStack();
      }

      @Override
      public void onFailure(Call<PoUploadPOSTResponse> call, Throwable t) {
        Toast.makeText(getContext(),"failure post"+t.getMessage(),Toast.LENGTH_LONG).show();

      }
    });
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
