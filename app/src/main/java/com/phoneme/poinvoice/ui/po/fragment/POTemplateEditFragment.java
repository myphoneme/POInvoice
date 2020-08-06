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
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditGETResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateListResponse;
import java.util.List;
import java.util.ArrayList;
import android.widget.Toast;
import com.phoneme.poinvoice.ui.po.model.StateModelData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class POTemplateEditFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private Button submit,Reset;
    private EditText date,PONumber,POAmount,Title,TemplateName,AddressLine1,AddressLine2,AddressLine3,GstNo;
    private  Spinner spin;
    private ArrayList<String> states=new ArrayList<String>();
    private PoTemplateDataModel dataModel;
    private List<StateModelData> stateModelDataList;
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
        //spin.setOnItemSelectedListener();


//        date=(EditText)view.findViewById(R.id.po_date);
//        PONumber=(EditText)view.findViewById(R.id.po_number);
//        POAmount=(EditText)view.findViewById(R.id.po_amount);
        submit=(Button)view.findViewById(R.id.submit);
        Reset=(Button)view.findViewById(R.id.reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOriginalData( dataModel,stateModelDataList);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        if(id!=null && !id.isEmpty()) {
            getPOTemplateData(id);
        }
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
        }

        if(AddressLine1!=null && AddressLine1.getText()!=null && AddressLine1.getText().length()>0){
            addressLine1=AddressLine1.getText().toString();
        }

        if(AddressLine2!=null && AddressLine2.getText()!=null && AddressLine2.getText().length()>0){
            addressLine2=AddressLine2.getText().toString();
        }

        if(AddressLine3!=null && AddressLine3.getText()!=null && AddressLine3.getText().length()>0){
            addressLine3=AddressLine3.getText().toString();
        }

        if(GstNo!=null && GstNo.getText()!=null && GstNo.getText().length()>0){

            gstno=GstNo.getText().toString();
        }

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
}
