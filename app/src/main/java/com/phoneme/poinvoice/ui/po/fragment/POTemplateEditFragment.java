package com.phoneme.poinvoice.ui.po.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditGETResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditPOSTResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateListResponse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import android.widget.Toast;
import com.phoneme.poinvoice.ui.po.model.StateModelData;
import com.squareup.picasso.Picasso;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;

public class POTemplateEditFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private Button submit,Reset,ImageSelect;
    private EditText date,PONumber,POAmount,Title,TemplateName,AddressLine1,AddressLine2,AddressLine3,GstNo;
    private  Spinner spin;
    private ArrayList<String> states=new ArrayList<String>();
    HashMap<String,String> state_stateid_Map = new HashMap<String, String>();
    private PoTemplateDataModel dataModel;
    private List<StateModelData> stateModelDataList;
    private ImageView Image;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";
    private boolean imageSelected = false;
    private String imagePath=new String();
    private static final int PERMISSION_STORAGE_CODE=1000;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_po_template, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");

        Title=(EditText)view.findViewById(R.id.template_title);
        TemplateName=(EditText)view.findViewById(R.id.template_name);
        AddressLine1=(EditText)view.findViewById(R.id.address_line1);
        AddressLine2=(EditText)view.findViewById(R.id.address_line2);
        AddressLine3=(EditText)view.findViewById(R.id.address_line3);
        GstNo=(EditText)view.findViewById(R.id.gstn_no);
        spin = (Spinner) view.findViewById(R.id.spinner_state);
        Image=(ImageView)view.findViewById(R.id.image);
        ImageSelect=(Button)view.findViewById(R.id.upload);
        ImageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent, 0);

                // uploadStartFunc();

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
        //spin.setOnItemSelectedListener();


//        date=(EditText)view.findViewById(R.id.po_date);
//        PONumber=(EditText)view.findViewById(R.id.po_number);
//        POAmount=(EditText)view.findViewById(R.id.po_amount);
        submit=(Button)view.findViewById(R.id.submit);
        Reset=(Button)view.findViewById(R.id.reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelected=false;
                setOriginalData( dataModel,stateModelDataList);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Submit Button clicked",Toast.LENGTH_LONG).show();
                getData();
            }
        });
        if(id!=null && !id.isEmpty()) {
            getPOTemplateData(id);
        }
    }
    private void uploadStartFunc(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);
    }
    private void getData(){
        String poNumber,poAmount,Date,title,templateName,addressLine1,addressLine2,addressLine3,gstno;
        if(Title!=null && Title.getText()!=null && Title.getText().length()>0) {
            title = Title.getText().toString();
        }else{
            Toast.makeText(getContext(),"title cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(TemplateName!=null && TemplateName.getText()!=null && TemplateName.getText().length()>0){
            templateName=TemplateName.getText().toString();
        }else{
            Toast.makeText(getContext(),"Template Name cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(AddressLine1!=null && AddressLine1.getText()!=null && AddressLine1.getText().length()>0){
            addressLine1=AddressLine1.getText().toString();
        }else{
            Toast.makeText(getContext(),"Address Line1 cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(AddressLine2!=null && AddressLine2.getText()!=null && AddressLine2.getText().length()>0){
            addressLine2=AddressLine2.getText().toString();
        }else{
            Toast.makeText(getContext(),"Address Line 2 cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(AddressLine3!=null && AddressLine3.getText()!=null && AddressLine3.getText().length()>0){
            addressLine3=AddressLine3.getText().toString();
        }else{
            Toast.makeText(getContext(),"Address Line 3 cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if(GstNo!=null && GstNo.getText()!=null && GstNo.getText().length()>0){

            gstno=GstNo.getText().toString();
        }else{
            Toast.makeText(getContext(),"GSTN cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        HashMap<String,RequestBody> map= new HashMap<>();

        RequestBody Title=createPartFromString(title);
        map.put("title",Title);

        RequestBody TemplateName=createPartFromString(templateName);
        map.put("template_name",TemplateName);

        RequestBody AddressLine1=createPartFromString(addressLine1);
        map.put("address_line1",AddressLine1);

        RequestBody AddressLine2=createPartFromString(addressLine2);
        map.put("address_line2",AddressLine2);

        RequestBody AddressLine3=createPartFromString(addressLine3);
        map.put("address_line3",AddressLine3);

        //State id also required
        RequestBody GSTNO=createPartFromString(gstno);
        map.put("GSTN_NO",GSTNO);

        String state_id=getSateId();
        Toast.makeText(getContext(),"state id="+state_id, Toast.LENGTH_LONG).show();
        RequestBody STATE_ID=createPartFromString(state_id);
        map.put("state_id",STATE_ID);
        postUpdateWithImage(map);
    }

    private String getSateId(){
        String state = spin.getSelectedItem().toString();
        //state_stateid_Map.get(state);
        return state_stateid_Map.get(state);
    }

    private void getPOTemplateData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<PoTemplateEditGETResponse> call=service.getPoTemplateEditData(id);

        call.enqueue(new Callback<PoTemplateEditGETResponse>() {
            @Override
            public void onResponse(Call<PoTemplateEditGETResponse> call, Response<PoTemplateEditGETResponse> response) {
                dataModel=response.body().getPo_templatedata();
                stateModelDataList=response.body().getStates();
                setStates(response.body().getStates());
                setOriginalData(dataModel,stateModelDataList);
            }

            @Override
            public void onFailure(Call<PoTemplateEditGETResponse> call, Throwable t) {

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
    private void setOriginalData(PoTemplateDataModel dataModel,List<StateModelData> stateModelDataList){
        Title.setText(dataModel.getTitle());
        TemplateName.setText(dataModel.getTemplate_name());
        AddressLine1.setText(dataModel.getAddress_line1());
        AddressLine2.setText(dataModel.getAddress_line2());
        AddressLine3.setText(dataModel.getAddress_line3());
        GstNo.setText(dataModel.getGSTN_NO());
        String state=dataModel.getState();
        int statePosition=0;
        for(int i=0;i<stateModelDataList.size();i++) {
            if (stateModelDataList.get(i).getState_Name()!=null){
                if(state.equalsIgnoreCase(stateModelDataList.get(i).getState_Name())){
                    statePosition=i;
                }
            }
        }
        Picasso.with(getContext()).load(base_url_image+dataModel.getLogo()).into( this.Image);
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,states);

//        aa.
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin.setSelection(statePosition);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(this,country[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == getActivity().RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                Image.setImageURI(selectedImage);
                //file_name.setText(selectedImage.g);
                imageSelected = true;

                imagePath=getRealPathFromURI(selectedImage);
                File file=new File(imagePath);
                //file_name.setText("file.getName()");

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

    private void postUpdateWithImage(HashMap<String,RequestBody> map){
        File file;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        if (imagePath != null && !imagePath.isEmpty()) {
            //String newimagePath = compressImage(imagePath);
            //file = new File(newimagePath);
            file = new File(imagePath);//This one working

        }else{
            file = new File("");
        }

        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("logo", file.getName(), requestBody);//these 3 lines extra
        Call<PoTemplateEditPOSTResponse> call;
        if(imageSelected){
            call=service.postPOTemplateWithImage(body,map);
            Toast.makeText(getContext(),"image selected",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"image not selected",Toast.LENGTH_LONG).show();
            call=service.postPOTemplateWithoutImage(map);
        }

        call.enqueue(new Callback<PoTemplateEditPOSTResponse>() {
            @Override
            public void onResponse(Call<PoTemplateEditPOSTResponse> call, Response<PoTemplateEditPOSTResponse> response) {
                Toast.makeText(getContext(),"success post",Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack();
            }

            @Override
            public void onFailure(Call<PoTemplateEditPOSTResponse> call, Throwable t) {
                Toast.makeText(getContext(),"failure post",Toast.LENGTH_LONG).show();
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
