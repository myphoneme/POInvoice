package com.phoneme.poinvoice.ui.po.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.phoneme.poinvoice.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class POUploadFragment extends Fragment {

    private EditText dob;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_po, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob=(EditText)view.findViewById(R.id.po_date);
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
}
