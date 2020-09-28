package com.phoneme.poinvoice.ui.po.fragment;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PdfConverter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.InvoiceViewModel;
import com.phoneme.poinvoice.ui.po.adapter.POFinalFunnelItemAdapter;
import com.phoneme.poinvoice.ui.po.model.POItemData;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFinalPOGetResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFunnelPOGetResponse;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.NOTIFICATION_SERVICE;

public class PODataFinalFunnelFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    private RecyclerView recyclerView;
    private GeneratedListFinalPOGetResponse finalData;
    private GeneratedListFunnelPOGetResponse funnelData;
    private TextView Company, FromCompany, ToCompany, TermsConditions, TotalWithoutGST, CGSTPERCENTAMOUNT, SGSTPERCENTAMOUNT,purchaseOrder;
    private TextView GrandTotal;
    private Button downloadPDFButton;
    private ImageView Logo;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";
    private List<POItemData> poItemDataList;

    //private PODataModel poDataModel;


    private ProgressDialog pDialog;

    public static final int progress_bar_type = 0;

    private String filename=new String();
    //private static String file_url="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    //private static String link1="http://support.phoneme.in/invoiceapis/Po/funnelpopdf?id=29";
    //private static String link2="http://support.phoneme.in/Po/funnelpopdf?id=29";
    private static String file_url=new String();

    private String folderPath= Environment.getExternalStorageDirectory() + "/Download/MyPDFs/";
    //private String folderPath= Environment.getExternalStorageDirectory() + "/Download/";
    private static final int PERMISSION_STORAGE_CODE=1000;
    private String file_name=new String();
    private String organization,id;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceViewModel =
                ViewModelProviders.of(this).get(InvoiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_final_funnel_po, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
         organization = getArguments().getString("organization");

//        String id = "11";
//        String organization = "2";

        Company = (TextView) view.findViewById(R.id.company);
        FromCompany = (TextView) view.findViewById(R.id.from_company);
        ToCompany = (TextView) view.findViewById(R.id.to_company);
        TermsConditions = (TextView) view.findViewById(R.id.terms_conditions);
        TotalWithoutGST = (TextView) view.findViewById(R.id.total_without_gst);
        CGSTPERCENTAMOUNT = (TextView) view.findViewById(R.id.cgst_percent_amount);
        SGSTPERCENTAMOUNT = (TextView) view.findViewById(R.id.sgst_percent_amount);
        GrandTotal=(TextView)view.findViewById(R.id.grand_total);
        Logo=(ImageView)view.findViewById(R.id.logo);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_items);
        downloadPDFButton=(Button)view.findViewById(R.id.downloadpdfbutton);
        purchaseOrder=(TextView)view.findViewById(R.id.purchase_order);
        downloadPDFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                file_url="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
//
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                    }else{
                        if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
                            gethtml("http://support.phoneme.in/invoiceapis/Po/finalpopdf2?id="+id);
                        } else {
                            gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id);
                        }
                    }
                }else{
                    if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
                        gethtml("http://support.phoneme.in/invoiceapis/Po/finalpopdf2?id="+id);
                    } else {
                        gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id);
                    }
                }

//                if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
//                    gethtml("http://support.phoneme.in/invoiceapis/Po/finalpopdf2?id="+id);
//                } else {
//                    gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id);
//                }

            }
        });

        if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
            getFinalData(id);
        } else {
            getFunnelData(id);
        }
    }

    private void converttoPdf(String string){

        PdfConverter converter = PdfConverter.getInstance();
        //File file = new File(Environment.getExternalStorageDirectory().toString(), "filepdf.pdf");
        File file = new File(Environment.getExternalStorageDirectory().toString(), file_name+".pdf");
        String htmlString = "<html><body><p>WHITE (default)</p></body></html>";
        //converter.convert(getContext(), htmlString, file);
        converter.convert(getContext(), string, file);

    }

    private void gethtml(String url){
        final String html="";
        Ion.with(getContext()).load(url).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                //html=result;
                converttoPdf(result);
                //toConvertHtmlStringToPdfAPI();
                System.out.println("resulthtml="+result);
                Toast.makeText(getContext(), "Getting ready for Pdf", Toast.LENGTH_LONG).show();
//                tv.setText(result);
            }
        });
    }

    public void onBrowseClick(View v,String id) {
        String url = "http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id=8";
        //String url = "http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        // Note the Chooser below. If no applications match,
        // Android displays a system message.So here there is no need for try-catch.
        startActivity(Intent.createChooser(intent, "Browse with"));

    }

    private void getFinalData(String id) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListFinalPOGetResponse> call = service.getGeneratedListFinalPOData(id);
        call.enqueue(new Callback<GeneratedListFinalPOGetResponse>() {
            @Override
            public void onResponse(Call<GeneratedListFinalPOGetResponse> call, Response<GeneratedListFinalPOGetResponse> response) {
                finalData = response.body();
                setFinalData(finalData);
            }

            @Override
            public void onFailure(Call<GeneratedListFinalPOGetResponse> call, Throwable t) {

            }
        });
    }

    private void getFunnelData(String id) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListFunnelPOGetResponse> call = service.getGeneratedListFunnelPOData(id);
        call.enqueue(new Callback<GeneratedListFunnelPOGetResponse>() {
            @Override
            public void onResponse(Call<GeneratedListFunnelPOGetResponse> call, Response<GeneratedListFunnelPOGetResponse> response) {
                funnelData = response.body();
                setFunnelData(funnelData);
            }

            @Override
            public void onFailure(Call<GeneratedListFunnelPOGetResponse> call, Throwable t) {

            }
        });

    }

    private void getFunnelPDFData(String id) {

    }


    private void setFunnelData(GeneratedListFunnelPOGetResponse data) {
        String fromAddressdata = new String(), toAddressdata = new String(), terms_conditions = new String();
        String totalWithoutGst = new String(), cgst_percentage_amount = new String(), sgst_percentage_amount = new String();
        String grandTotal=new String();
        fromAddressdata = data.getOrgDataModel().getTemplate_name() + "\n" + data.getOrgDataModel().getAddress_line1()
                + "\n" + data.getOrgDataModel().getAddress_line2() + "\n"
                + data.getOrgDataModel().getAddress_line3() + "\n"
                + "GSTN:" + data.getOrgDataModel().getGSTN_NO();

        toAddressdata = "To" + "\n" + data.getVendorDataModel().getVendor_name() + "\n"
                + data.getVendorDataModel().getAddress() + "\n"
                + "GSTN:" + data.getVendorDataModel().getGSTIN();

        terms_conditions = "Terms Conditions \n" + data.getPoDataModelList().get(0).getTermsconditions();
        totalWithoutGst = "Total without GST:₹" + data.getPoDataModelList().get(0).getTotal();
        cgst_percentage_amount = "CGST @" + data.getPoDataModelList().get(0).getCgst() + " ₹" + data.getPoDataModelList().get(0).getCgst_amount();
        sgst_percentage_amount = "CGST @" + data.getPoDataModelList().get(0).getSgst() + " ₹" + data.getPoDataModelList().get(0).getSgst_amount();
        grandTotal="Grand Total: ₹"+ data.getPoDataModelList().get(0).getGrand_total();
        String po_number=data.getPoDataModelList().get(0).getPo_number();
        purchaseOrder.setText("Purchase Order:"+po_number);
        file_name=po_number.replace("/","_");
        FromCompany.setText(fromAddressdata);
        ToCompany.setText(toAddressdata);
        TermsConditions.setText(terms_conditions);
        TotalWithoutGST.setText(totalWithoutGst);
        CGSTPERCENTAMOUNT.setText(cgst_percentage_amount);
        SGSTPERCENTAMOUNT.setText(sgst_percentage_amount);
        GrandTotal.setText(grandTotal);
        Picasso.with(getContext()).load(base_url_image+data.getOrgDataModel().getLogo()).into( this.Logo);
        poItemDataList=data.getPoItemDataList();
        setAdapter(poItemDataList);
        //Logo


    }

    private void setFinalData(GeneratedListFinalPOGetResponse data) {
        String fromAddressdata = new String(), toAddressdata = new String(), terms_conditions = new String();
        String totalWithoutGst = new String(), cgst_percentage_amount = new String(), sgst_percentage_amount = new String();
        String grandTotal=new String();
        fromAddressdata = data.getOrgDataModel().getTemplate_name() + "\n" + data.getOrgDataModel().getAddress_line1()
                + "\n" + data.getOrgDataModel().getAddress_line2() + "\n"
                + data.getOrgDataModel().getAddress_line3() + "\n"
                + "GSTN:" + data.getOrgDataModel().getGSTN_NO();

        toAddressdata = "To" + "\n" + data.getVendorDataModel().getVendor_name() + "\n"
                + data.getVendorDataModel().getAddress() + "\n"
                + "GSTN:" + data.getVendorDataModel().getGSTIN();

        terms_conditions = "Terms Conditions \n" + data.getPoDataModelList().get(0).getTermsconditions();
        totalWithoutGst = "Total without GST:₹" + data.getPoDataModelList().get(0).getTotal();
        cgst_percentage_amount = "CGST @" + data.getPoDataModelList().get(0).getCgst() + " ₹" + data.getPoDataModelList().get(0).getCgst_amount();
        sgst_percentage_amount = "CGST @" + data.getPoDataModelList().get(0).getSgst() + " ₹" + data.getPoDataModelList().get(0).getSgst_amount();
        grandTotal="Grand Total: ₹"+ data.getPoDataModelList().get(0).getGrand_total();
        String po_number=data.getPoDataModelList().get(0).getPo_number();
        purchaseOrder.setText("Purchase Order:"+po_number);
        file_name=po_number.replace("/","_");
        FromCompany.setText(fromAddressdata);
        ToCompany.setText(toAddressdata);
        TermsConditions.setText(terms_conditions);
        TotalWithoutGST.setText(totalWithoutGst);
        CGSTPERCENTAMOUNT.setText(cgst_percentage_amount);
        SGSTPERCENTAMOUNT.setText(sgst_percentage_amount);
        GrandTotal.setText(grandTotal);
        Picasso.with(getContext()).load(base_url_image+data.getOrgDataModel().getLogo()).into( this.Logo);
        poItemDataList=data.getPoItemDataList();
        setAdapter(poItemDataList);
    }

    private void setAdapter(List<POItemData> poItemDataList){
        POFinalFunnelItemAdapter adapter=new POFinalFunnelItemAdapter(getContext(),poItemDataList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearVertical);

    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                Toast.makeText(getContext(),"Submit Button clicked",Toast.LENGTH_LONG).show();

                URL url = new URL(f_url[0]);
                //filename=getFileName(f_url[0]);
                filename="convert.pdf";
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream(folderPath+ filename);
                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_LONG).show();

                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            //pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            //dismissDialog(progress_bar_type);
            //Toast.makeText(getApplicationContext(),"download complete"+filename,Toast.LENGTH_LONG).show();

            System.out.println("fileurl="+file_url);
            //createNotification(btnShowProgress,filename);
        }

    }

    public void createNotification(View view, String filename) {
        Notification noti = new Notification.Builder(getContext())
                .setContentTitle("Download")
                .setContentText("File "+filename+" downloaded").setSmallIcon(R.drawable.ic_launcher_background)
                .build();
//                .setContentIntent(pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    private void createDirectoryAndSaveFile(String folderPath) {

        File direct = new File(folderPath);

        if (!direct.exists()) {
            File wallpaperDirectory = new File(folderPath);
            wallpaperDirectory.mkdirs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case PERMISSION_STORAGE_CODE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //new DownloadFileFromURL().execute(file_url);
                    if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
                        gethtml("http://support.phoneme.in/invoiceapis/Po/finalpopdf2?id="+id);
                    } else {
                        gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id);
                    }
                }else{
                    //Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();
                }
        }
    }
}
