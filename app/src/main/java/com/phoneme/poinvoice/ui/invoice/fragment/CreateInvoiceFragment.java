package com.phoneme.poinvoice.ui.invoice.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceGenerateGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceGeneratePostResponse;
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInvoiceFragment extends Fragment {

    private EditText dob,invoiceNumber,PONumber,gstPercentage,Remark,cGST_Percentage,sGST_Percentage,iGST_Percentage;
    private Spinner Company,Vendor;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private Button serviceButton;
    private LinearLayout servicelayout;
    private RelativeLayout iGSTLayout;
    private LinearLayout cGSTsGSTLayout;
    private int servicecount=1;
    private Button Generate;
    private List<PoTemplateDataModel> poTemplateDataModelList;
    private List<VendorDataModel> vendorDataModelList;
    private ArrayList<String> companies=new ArrayList<String>();
    HashMap<String,String> company_companyid_Map = new HashMap<String, String>();
    HashMap<String,String> company_stateid_Map = new HashMap<String, String>();
    HashMap<String,String> companyid_company_Map = new HashMap<String, String>();

    private ArrayList<String> vendors=new ArrayList<String>();
    HashMap<String,String> vendor_vendorid_Map = new HashMap<String, String>();
    HashMap<String,String> vendor_stateid_Map = new HashMap<String, String>();
    HashMap<String,String> vendorid_vendor_Map = new HashMap<String, String>();

    private String gstType="";
    String company_state_id=new String();
    String vendor_state_id=new String();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_invoice, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here


        Generate = (Button)view.findViewById(R.id.generate);
        PONumber=(EditText)view.findViewById(R.id.po_number);
        Company=(Spinner)view.findViewById(R.id.company);
        Vendor=(Spinner)view.findViewById(R.id.vendor);
        gstPercentage=(EditText)view.findViewById(R.id.igst_percentage);
        Remark=(EditText)view.findViewById(R.id.remark);
        iGSTLayout=(RelativeLayout)view.findViewById(R.id.igst_layout);
        cGSTsGSTLayout=(LinearLayout)view.findViewById(R.id.sgst_cgst_layout);
        cGST_Percentage=(EditText)view.findViewById(R.id.cgst_percentage);
        sGST_Percentage=(EditText)view.findViewById(R.id.sgst_percentage);
        iGST_Percentage=(EditText)view.findViewById(R.id.igst_percentage);


        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob=(EditText)view.findViewById(R.id.invoice_date);
        dob.setText(sdf.format(myCalendar.getTime()));
        serviceButton=(Button)view.findViewById(R.id.service_button);
        servicelayout=(LinearLayout)view.findViewById(R.id.service_products_layout);
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //servicecount++;
                createView();
            }
        });
        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Submit Button clicked",Toast.LENGTH_LONG).show();
                getLocalData();

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

        getData();
    }

    private void getLocalData(){
        String invoicedate=new String(),invoice=new String(),po=new String(),vendor,gst=new String(),remark=new String(),company;
        String service[]=new String[servicecount];
        String description[]=new String[servicecount];
        String quantity[]=new String[servicecount];
        String price[]=new String[servicecount];
        Map<String,String> map=new HashMap<String,String>();

        if(dob!=null && dob.getText()!=null && dob.getText().length()>0){
            invoicedate=dob.getText().toString();
        }
//
        if(invoiceNumber!=null && invoiceNumber.getText()!=null && invoiceNumber.getText().length() > 0){
            invoice=invoiceNumber.getText().toString();
        }

        if(PONumber!=null && PONumber.getText()!=null && PONumber.getText().length()>0){
            po=PONumber.getText().toString();
        }
        if(gstPercentage!=null && gstPercentage.getText()!=null && gstPercentage.getText().length()>0){

            gst=gstPercentage.getText().toString();
        }
        if(Remark!=null && Remark.getText()!=null && Remark.getText().length()>0){
            remark=Remark.getText().toString();
        }


        vendor = Vendor.getSelectedItem().toString();

        company=Company.getSelectedItem().toString();

        map.put("vendorid",vendor_vendorid_Map.get(vendor));
        map.put("companyid",company_companyid_Map.get(company));

        map.put("vendorstateid",vendor_vendorid_Map.get(vendor));
        map.put("companystateid",company_companyid_Map.get(company));
        map.put("invoice_number",invoice);
        map.put("order_id",po);
        map.put("payment_due",invoicedate);
        map.put("remarks",remark);
        map.put("gst",gst);
        map.put("gsttype",gstType);
        if(company_state_id.equalsIgnoreCase(vendor_state_id)){
         String   cgstPercentage=cGST_Percentage.getText().toString();
         String sgstPercentage=   sGST_Percentage.getText().toString();
            map.put("cgst",cgstPercentage);
            map.put("sgst",sgstPercentage);
        }else{
            String igstPercentage=iGST_Percentage.getText().toString();
            map.put("igst",igstPercentage);
        }

        for(int i=1;i<= servicecount;i++){
            EditText serviceEdit = getView().findViewWithTag("service"+i);
            EditText descriptionEdit = getView().findViewWithTag("description"+i);
            EditText quantityEdit = getView().findViewWithTag("quantity"+i);
            EditText priceEdit = getView().findViewWithTag("price"+i);
            String servicekey="service["+(i-1)+"]";
            String descriptionkey="description["+(i-1)+"]";
            String quantitykey="quantity["+(i-1)+"]";
            String pricekey="price["+(i-1)+"]";

            map.put(servicekey,serviceEdit.getText().toString());
            map.put(descriptionkey,descriptionEdit.getText().toString());
            map.put(quantitykey,quantityEdit.getText().toString());
            map.put(pricekey,priceEdit.getText().toString());

            Toast.makeText(getContext(), "service value= i="+i+" "+serviceEdit.getText().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), "service key="+servicekey, Toast.LENGTH_LONG).show();

            //service[i]=
        }
        Toast.makeText(getContext(), "Companyid="+map.get("companystateid"), Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(), "Vendorid="+map.get("vendorstateid"), Toast.LENGTH_LONG).show();
        postInvoiceGenerateData(map);
    }

    private void createView(){
        servicecount++;
        View v = new View(getContext());
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        ));

        v.setBackgroundColor(Color.parseColor("#B3B3B3"));


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                (int) getResources().getDimension(R.dimen.edit_width),
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins((int) getResources().getDimension(R.dimen.margin),
                (int) getResources().getDimension(R.dimen.margin),
                0,
                (int) getResources().getDimension(R.dimen.margin));

        RelativeLayout relativeLayout=new RelativeLayout(getContext());


        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams paramsLeft = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        //paramsLeft.bottomMargin=(int) getResources().getDimension(R.dimen.margin);

        //relativeLayout.setBackgroundColor((int)getResources().getColor(R.color.colorAccent));
        relativeLayout.setLayoutParams(params2);
//        relativeLayout.getLayoutParams().width=(int) getResources().getDimension(R.dimen.edit_width);
//        relativeLayout.getLayoutParams().height=RelativeLayout.LayoutParams.WRAP_CONTENT;
//


        TextView textView=new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        //textView.setHint("Service");
        //textView.setHintTextColor((int)getResources().getColor(R.color.grey));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setText("Service");

        textView.setLayoutParams(paramsLeft);

        //textView.setBackgroundColor((int)getResources().getColor(R.color.grey));
//        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(0,0,0,0);

        EditText edittTxt = new EditText(getContext());
        //edittTxt.setTextSize((int)getResources().getDimension(R.dimen.text_size));
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        edittTxt.setHint("Service");
        edittTxt.setTag("service"+servicecount);
        Toast.makeText(getContext(),"tag="+edittTxt.getTag().toString(),Toast.LENGTH_LONG).show();
        edittTxt.setId(0);
        edittTxt.setHintTextColor((int)getResources().getColor(R.color.grey));

//
//
        edittTxt.setWidth((int) getResources().getDimension(R.dimen.edit_width));
        relativeLayout.addView(v);
        relativeLayout.addView(edittTxt);
        relativeLayout.addView(textView);

//
       params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        edittTxt.setLayoutParams(params);
        edittTxt.setBackground(getContext().getResources().getDrawable(R.drawable.edit_bg));
//
        servicelayout.addView(relativeLayout);







        View vDescription = new View(getContext());
        vDescription.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        ));

        vDescription.setBackgroundColor(Color.parseColor("#c8c8c8"));


        RelativeLayout.LayoutParams paramsDescription = new RelativeLayout.LayoutParams (
                (int) getResources().getDimension(R.dimen.edit_width),
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout relativeLayoutDescription=new RelativeLayout(getContext());
        RelativeLayout.LayoutParams paramsDescription2 = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        paramsDescription2.setMargins((int) getResources().getDimension(R.dimen.margin),
                (int) getResources().getDimension(R.dimen.margin),
                (int) getResources().getDimension(R.dimen.margin),
                (int) getResources().getDimension(R.dimen.margin));


        RelativeLayout.LayoutParams paramsDescriptionLeft = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

//        paramsDescriptionLeft.setMargins((int) getResources().getDimension(R.dimen.margin),
//                (int) getResources().getDimension(R.dimen.margin),
//                (int) getResources().getDimension(R.dimen.margin),
//                (int) getResources().getDimension(R.dimen.margin));

        relativeLayoutDescription.setLayoutParams(paramsDescription2);


        TextView textViewDescription=new TextView(getContext());
        textViewDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        //textViewDescription.setHint("Description");
        textViewDescription.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textViewDescription.setLayoutParams(paramsDescriptionLeft);
        textViewDescription.setText("Description");
        //textViewDescription.setTextColor((int)getResources().getColor(R.color.grey));
        //textViewDescription.setHintTextColor((int)getResources().getColor(R.color.grey));

        EditText edittTxtDescription = new EditText(getContext());

        edittTxtDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        edittTxtDescription.setHint("Description");
        edittTxtDescription.setTag("description"+servicecount);
        edittTxtDescription.setHintTextColor((int)getResources().getColor(R.color.grey));

        edittTxtDescription.setWidth((int) getResources().getDimension(R.dimen.edit_width));
        //relativeLayoutDescription.addView(vDescription);
        relativeLayoutDescription.addView(edittTxtDescription);
        relativeLayoutDescription.addView(textViewDescription);

        paramsDescription.setMargins((int) getResources().getDimension(R.dimen.margin),
                (int) getResources().getDimension(R.dimen.margin),
                0,
                (int) getResources().getDimension(R.dimen.margin));

        paramsDescription.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        edittTxtDescription.setLayoutParams(paramsDescription);
        edittTxtDescription.setBackground(getContext().getResources().getDrawable(R.drawable.edit_bg));

        servicelayout.addView(relativeLayoutDescription);






//Quantity

        View vQuantity = new View(getContext());
        vQuantity.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        ));

        vQuantity.setBackgroundColor(Color.parseColor("#B3B3B3"));


        RelativeLayout.LayoutParams paramsQuantity = new RelativeLayout.LayoutParams (
                (int) getResources().getDimension(R.dimen.edit_width),
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout relativeLayoutQuantity=new RelativeLayout(getContext());
        RelativeLayout.LayoutParams paramsQuantity2 = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout.LayoutParams paramsQuantityLeft = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        relativeLayoutQuantity.setLayoutParams(paramsQuantity2);


        TextView textViewQuantity=new TextView(getContext());
        textViewQuantity.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        //textViewQuantity.setHint("Quantity");
        textViewQuantity.setText("Quantity");
        textViewQuantity.setTypeface(textView.getTypeface(), Typeface.BOLD);
        //textViewQuantity.setHintTextColor((int)getResources().getColor(R.color.grey));
        textViewQuantity.setLayoutParams(paramsQuantityLeft);
        //textViewQuantity.setTextColor((int)getResources().getColor(R.color.grey));
        EditText edittTxtQuantity = new EditText(getContext());

        edittTxtQuantity.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        edittTxtQuantity.setHint("Quantity");
        edittTxtQuantity.setTag("quantity"+servicecount);
        edittTxtQuantity.setHintTextColor((int)getResources().getColor(R.color.grey));
        //edittTxtQuantity.setPadding(30,0,0,0);

        edittTxtQuantity.setWidth((int) getResources().getDimension(R.dimen.edit_width));
        //relativeLayoutQuantity.addView(vQuantity);
        relativeLayoutQuantity.addView(edittTxtQuantity);
        relativeLayoutQuantity.addView(textViewQuantity);

        paramsQuantity.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        edittTxtQuantity.setLayoutParams(paramsDescription);
        edittTxtQuantity.setBackground(getContext().getResources().getDrawable(R.drawable.edit_bg));

        servicelayout.addView(relativeLayoutQuantity);




        //Price

        View vPrice = new View(getContext());
        vPrice.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        ));

        vPrice.setBackgroundColor(Color.parseColor("#B3B3B3"));


        RelativeLayout.LayoutParams paramsPrice = new RelativeLayout.LayoutParams (
                (int) getResources().getDimension(R.dimen.edit_width),
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout relativeLayoutPrice=new RelativeLayout(getContext());
        RelativeLayout.LayoutParams paramsPrice2 = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        RelativeLayout.LayoutParams paramsPriceLeft = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        relativeLayoutPrice.setLayoutParams(paramsPrice2);


        TextView textViewPrice=new TextView(getContext());
        textViewPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        //textViewPrice.setHint("Price");
        textViewPrice.setText("Price");
        textViewPrice.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textViewPrice.setLayoutParams(paramsPriceLeft);
        textViewPrice.setGravity(Gravity.CENTER_VERTICAL);

        //textViewPrice.setTextColor((int)getResources().getColor(R.color.grey));
        //textViewPrice.setTypeface(null, Typeface.BOLD);
        textViewPrice.setTypeface(textView.getTypeface(), Typeface.BOLD);


        EditText edittTxtPrice = new EditText(getContext());

        edittTxtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        edittTxtPrice.setHint("Price");
        edittTxtPrice.setTag("price"+servicecount);
        edittTxtPrice.setHintTextColor((int)getResources().getColor(R.color.grey));
        edittTxtPrice.setTextColor((int)getResources().getColor(R.color.grey));

        edittTxtPrice.setWidth((int) getResources().getDimension(R.dimen.edit_width));
        //relativeLayoutPrice.addView(vPrice);
        relativeLayoutPrice.addView(edittTxtPrice);
        relativeLayoutPrice.addView(textViewPrice);

        paramsPrice.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        edittTxtPrice.setLayoutParams(paramsDescription);
        edittTxtPrice.setBackground(getContext().getResources().getDrawable(R.drawable.edit_bg));
        Toast.makeText(getContext(), "servicetag "+edittTxt.getTag().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(), "descriptiontag "+edittTxtDescription.getTag().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(), "quantitytag "+edittTxtQuantity.getTag().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),"pricetag "+ edittTxtPrice.getTag().toString(), Toast.LENGTH_LONG).show();

        servicelayout.addView(relativeLayoutPrice);
    }

    private void postInvoiceGenerateData(Map<String,String> map){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<InvoiceGeneratePostResponse> call=service.postInvoiceGenerate(map);
        call.enqueue(new Callback<InvoiceGeneratePostResponse>() {
            @Override
            public void onResponse(Call<InvoiceGeneratePostResponse> call, Response<InvoiceGeneratePostResponse> response) {
                Toast.makeText(getContext(),"Succes",Toast.LENGTH_LONG).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack();
            }

            @Override
            public void onFailure(Call<InvoiceGeneratePostResponse> call, Throwable t) {

            }
        });
    }
    private void getData(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<InvoiceGenerateGetResponse> call=service.getInvoiceGenerateData();
        call.enqueue(new Callback<InvoiceGenerateGetResponse>() {
            @Override
            public void onResponse(Call<InvoiceGenerateGetResponse> call, Response<InvoiceGenerateGetResponse> response) {
                poTemplateDataModelList=response.body().getPoTemplateDataModelList();
                vendorDataModelList=response.body().getVendorDataModelList();
                setVendors(vendorDataModelList);
                setCompanies(poTemplateDataModelList);
                setSpinnerListener();
            }

            @Override
            public void onFailure(Call<InvoiceGenerateGetResponse> call, Throwable t) {

            }
        });
    }
    private void setSpinnerListener(){
        Vendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getSpinnersData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getSpinnersData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void showCgstSgstView(){
        iGSTLayout.setVisibility(View.GONE);
        cGSTsGSTLayout.setVisibility(View.VISIBLE);
    }
    private void showIgstView(){
        iGSTLayout.setVisibility(View.VISIBLE);
        cGSTsGSTLayout.setVisibility(View.GONE);
    }
    private void getSpinnersData(){
        String company=Company.getSelectedItem().toString();
        String vendor=Vendor.getSelectedItem().toString();
        Toast.makeText(getContext(), "Company="+company+" Vendor="+vendor, Toast.LENGTH_LONG).show();
        company_state_id=company_stateid_Map.get(company);
        vendor_state_id=vendor_stateid_Map.get(vendor);
        Toast.makeText(getContext(), "Companyid="+company_state_id+" Vendorid="+vendor_state_id, Toast.LENGTH_LONG).show();
        if(company_state_id.equalsIgnoreCase(vendor_state_id)){
            gstType="match";
            showCgstSgstView();
        }else{
            gstType="";
            showIgstView();
        }
    }

    private void setVendors(List<VendorDataModel> vendorDataModelList){
        for(int i=0;i<vendorDataModelList.size();i++){
            if(vendorDataModelList.get(i)!=null && vendorDataModelList.get(i).getVendor_name()!=null && vendorDataModelList.get(i).getId()!=null){
                vendors.add(vendorDataModelList.get(i).getVendor_name());
                vendor_vendorid_Map.put(vendorDataModelList.get(i).getVendor_name(),vendorDataModelList.get(i).getId());
                vendorid_vendor_Map.put(vendorDataModelList.get(i).getId(),vendorDataModelList.get(i).getVendor_name());
                vendor_stateid_Map.put(vendorDataModelList.get(i).getVendor_name(),vendorDataModelList.get(i).getState_id());
            }
        }
        setVendorSpinnerData();
    }

    private void setCompanies(List<PoTemplateDataModel> poTemplateDataModelList){
        for(int i=0;i<poTemplateDataModelList.size();i++){
            if(poTemplateDataModelList.get(i)!=null && poTemplateDataModelList.get(i).getTitle()!=null){
                companies.add(poTemplateDataModelList.get(i).getTitle());
                company_companyid_Map.put(poTemplateDataModelList.get(i).getTitle(),poTemplateDataModelList.get(i).getId());
                companyid_company_Map.put(poTemplateDataModelList.get(i).getId(),poTemplateDataModelList.get(i).getTitle());
                company_stateid_Map.put(poTemplateDataModelList.get(i).getTitle(),poTemplateDataModelList.get(i).getState_id());
            }
        }
        setCompanySpinnerData();
    }

    private void setCompanySpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,companies);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Company.setAdapter(aa);
        Company.setSelection(1);
    }
    private void setVendorSpinnerData(){
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,vendors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Vendor.setAdapter(aa);
        Vendor.setSelection(0);

    }

}
