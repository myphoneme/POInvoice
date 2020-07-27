package com.phoneme.poinvoice.ui.po.fragment;

import android.app.DatePickerDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phoneme.poinvoice.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateInvoiceFragment extends Fragment {

    private EditText dob;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private Button serviceButton;
    private LinearLayout servicelayout;
    private int servicecount=1;

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
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob=(EditText)view.findViewById(R.id.invoice_date);
        dob.setText(sdf.format(myCalendar.getTime()));
        serviceButton=(Button)view.findViewById(R.id.service_button);
        servicelayout=(LinearLayout)view.findViewById(R.id.service_products_layout);
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createView();
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
}
