package com.phoneme.poinvoice.ui.po.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.loader.content.CursorLoader;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.model.StateModelData;
import com.phoneme.poinvoice.ui.po.network.AddNewPoTemplateGetResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListPOPaymentPostResponse;
import com.phoneme.poinvoice.ui.po.network.POTemplateAddPostResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPoTemplateFragment extends Fragment {

    private Button Submit,Upload;
    private EditText templateTitle,templateName,addressLine1,addressLine2,addressLine3,gstNumber;
    private Spinner State;

    private String imagePath=new String();
    private boolean imageSelected = false;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private List<StateModelData> stateModelDataList;
    private ArrayList<String> states=new ArrayList<String>();
    HashMap<String,String> state_stateid_Map = new HashMap<String, String>();


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
        Upload=(Button)view.findViewById(R.id.upload);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        Toast.makeText(getContext(),"Before get states",Toast.LENGTH_LONG).show();
        getStates();
    }

    private void getData(){
        String templatetitle=new String(),templatename=new String(),addressline1=new String(),addressline2=new String(),addressline3=new String(),state=new String(),gstnumber=new String();
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
        String StateId=state_stateid_Map.get(state);
        HashMap<String,RequestBody> map= new HashMap<>();

        RequestBody Templatetitle=createPartFromString(templatetitle);
        map.put("title",Templatetitle);

        RequestBody  Templatename=createPartFromString( templatename);
        map.put("template_name", Templatename);

        RequestBody  Addressline1=createPartFromString( addressline1);
        map.put("address_line1", Addressline1);

        RequestBody  Addressline2=createPartFromString( addressline2);
        map.put("address_line2", Addressline2);

        RequestBody  Addressline3=createPartFromString( addressline3);
        map.put("address_line3", Addressline3);

        RequestBody  stateid=createPartFromString( StateId);
        map.put("state_id", stateid);

        RequestBody  GSTnumber=createPartFromString( gstnumber);
        map.put("GSTN_NO", GSTnumber);

        postDataWithImage2(map);
    }
    //POTemplateAddPostResponse

    private void getStates(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<AddNewPoTemplateGetResponse> call=service.getPoTemplateAddData();
        call.enqueue(new Callback<AddNewPoTemplateGetResponse>() {
            @Override
            public void onResponse(Call<AddNewPoTemplateGetResponse> call, Response<AddNewPoTemplateGetResponse> response) {
                Toast.makeText(getContext(),"Success get states",Toast.LENGTH_LONG).show();

                stateModelDataList=response.body().getStateModelDataList();
                setStates(response.body().getStateModelDataList());
                setSpinnerData();
            }

            @Override
            public void onFailure(Call<AddNewPoTemplateGetResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
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
                //File file=new File(imagePath);
                //file_name.setText("file.getName()");

            }
        } catch (Exception e) {
            //Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void postDataWithImage2(HashMap<String,RequestBody> map){
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
        MultipartBody.Part body = MultipartBody.Part.createFormData("logo", file.getName(), requestBody);//these 3 lines extra
        Call<POTemplateAddPostResponse> call;

        if(imageSelected){
            call=service.postPOTemplateAddWithImage(body,map);
            Toast.makeText(getContext(),"image selected",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"image not selected",Toast.LENGTH_LONG).show();
            call=service.postPOTemplateAddWithoutImage(map);
        }
        call.enqueue(new Callback<POTemplateAddPostResponse>() {
            @Override
            public void onResponse(Call<POTemplateAddPostResponse> call, Response<POTemplateAddPostResponse> response) {
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<POTemplateAddPostResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failed"+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void setStates(List<StateModelData> stateModelDataList){
        Toast.makeText(getContext(),"set States",Toast.LENGTH_LONG).show();
        for(int i=0;i<stateModelDataList.size();i++) {
            if (stateModelDataList.get(i).getState_Name()!=null){
                states.add(stateModelDataList.get(i).getState_Name());
                state_stateid_Map.put(stateModelDataList.get(i).getState_Name(),stateModelDataList.get(i).getState_Code());

            }
        }
    }

    private void setSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,states);
        Toast.makeText(getContext(),"set spinner data",Toast.LENGTH_LONG).show();

//        aa.
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
        State.setAdapter(aa);
        State.setSelection(0);
    }
}
