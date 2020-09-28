package com.phoneme.poinvoice.ui.invoice.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.phoneme.poinvoice.ui.invoice.adapter.InvoiceFinalFunnelItemAdapter;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceProductsDataModel;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceFinalInvoiceGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceFunnelInvoiceGetResponse;
import com.phoneme.poinvoice.ui.po.fragment.PODataFinalFunnelFragment;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.Context.NOTIFICATION_SERVICE;

public class FinalInvoiceFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    private RecyclerView recyclerView;

    private InvoiceFinalInvoiceGetResponse finalData;
    private InvoiceFunnelInvoiceGetResponse funnelData;
    private TextView Company,FromCompany,ToCompany,InvoicePoData,TermsConditions,totalWithoutGst,CGSTPERCENTAMOUNT, SGSTPERCENTAMOUNT;
    private TextView GrandTotal;
    private List<InvoiceProductsDataModel> invoiceProductsDataModels;

    private ImageView Logo;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";

    private ProgressDialog pDialog;

    public static final int progress_bar_type = 0;

    private Button downloadPDFButton;

    private String filename=new String();
    //private static String file_url="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    //private static String link1="http://support.phoneme.in/invoiceapis/Po/funnelpopdf?id=29";
    //private static String link2= "http://support.phoneme.in/Po/funnelpopdf?id=29";
    private static String file_url=new String();

    private String folderPath= Environment.getExternalStorageDirectory() + "/Download/MyPDFs/";

    private static final int PERMISSION_STORAGE_CODE=1000;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceViewModel =
                ViewModelProviders.of(this).get(InvoiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_final_funnel_invoice, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String id = getArguments().getString("id");
        String name= getArguments().getString("name");
        Toast.makeText(getContext(),"id="+id, Toast.LENGTH_LONG).show();
        Company=(TextView)view.findViewById(R.id.company);
        Logo=(ImageView)view.findViewById(R.id.logo);
        FromCompany=(TextView)view.findViewById(R.id.from_company);
        ToCompany=(TextView)view.findViewById(R.id.to_company);
        InvoicePoData=(TextView)view.findViewById(R.id.invoice_po_data);
        TermsConditions=(TextView)view.findViewById(R.id.terms_conditions);
        totalWithoutGst=(TextView)view.findViewById(R.id.total_without_gst);
        CGSTPERCENTAMOUNT = (TextView) view.findViewById(R.id.cgst_percent_amount);
        SGSTPERCENTAMOUNT = (TextView) view.findViewById(R.id.sgst_percent_amount);
        GrandTotal=(TextView)view.findViewById(R.id.grand_total);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_items);
        downloadPDFButton=(Button)view.findViewById(R.id.downloadpdfbutton);

        if (name.equalsIgnoreCase("2") || name.equalsIgnoreCase("4") || name.equalsIgnoreCase("5")) {
            getFinalData(id);
        } else {
            getFunnelData(id);
        }
        downloadPDFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBrowseClick(view,id);
                //gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id=8");
                //gethtml("http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id);
                //gethtml("http://support.phoneme.in/invoiceapis/invoice/funnelinvoicepdf?id="+id);

                if (name.equalsIgnoreCase("2") || name.equalsIgnoreCase("4") || name.equalsIgnoreCase("5")) {
                    gethtml("http://support.phoneme.in/invoiceapis/invoice/finalinvoicepdf?id="+id);
                } else {
                    gethtml("http://support.phoneme.in/invoiceapis/invoice/funnelinvoicepdf?id="+id);
                }

                //converttoPdf("url");
            }
        });
    }

    public void onBrowseClick(View v,String id) {
    //    String url = "http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id=8";
        String url = "http://support.phoneme.in/invoiceapis/Po/funnelpopdf2?id="+id;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        // Note the Chooser below. If no applications match,
        // Android displays a system message.So here there is no need for try-catch.
        startActivity(Intent.createChooser(intent, "Browse with"));

    }

    private void converttoPdf(String string){

        PdfConverter converter = PdfConverter.getInstance();
        File file = new File(Environment.getExternalStorageDirectory().toString(), "filepdf.pdf");
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
                toConvertHtmlStringToPdfAPI();
                System.out.println("resulthtml="+result);
                Toast.makeText(getContext(), "gethtml", Toast.LENGTH_LONG).show();
//                tv.setText(result);
            }
        });
    }

//    private void createPdf() {
//
//        PdfDocument document = new PdfDocument();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(b.getWidth(), b.getHeight(), 1).create();
//        PdfDocument.Page page = document.startPage(pageInfo);
//
//        Canvas canvas = page.getCanvas();
//
//
//        Paint paint = new Paint();
//        paint.setColor(Color.parseColor("#ffffff"));
//        canvas.drawPaint(paint);
//
//
//        Bitmap bitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);
//
//        paint.setColor(Color.BLUE);
//        canvas.drawBitmap(bitmap, 0, 0, null);
//        document.finishPage(page);
//        File filePath = new File(path);
//        try {
//            document.writeTo(new FileOutputStream(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
//        }
//
//        // close the document
//        document.close();
//
//        openPdf(path);// You can open pdf after complete
//    }
//
//    private void takeScreenShot() {
//
//        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Signature/");
//
//        if (!folder.exists()) {
//            boolean success = folder.mkdir();
//        }
//
//        path = folder.getAbsolutePath();
//        path = path + "/" + signature_pdf_ + System.currentTimeMillis() + ".pdf";// path where pdf will be stored
//
//        View u = findViewById(R.id.scroll);
//        NestedScrollView z = (NestedScrollView) findViewById(R.id.scroll); // parent view
//        totalHeight = z.getChildAt(0).getHeight();// parent view height
//        totalWidth = z.getChildAt(0).getWidth();// parent view width
//
//        //Save bitmap to  below path
//        String extr = Environment.getExternalStorageDirectory() + "/Signature/";
//        File file = new File(extr);
//        if (!file.exists())
//            file.mkdir();
//        String fileName = signature_img_ + ".jpg";
//        myPath = new File(extr, fileName);
//        imagesUri = myPath.getPath();
//        FileOutputStream fos = null;
//        b = getBitmapFromView(u, totalHeight, totalWidth);
//
//        try {
//            fos = new FileOutputStream(myPath);
//            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        createPdf();// create pdf after creating bitmap and saving
//
//    }

    private void getFinalData(String id) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //InvoiceFinalInvoiceGetResponse
        Call<InvoiceFinalInvoiceGetResponse> call=service.getInvoiceListFinalPOData(id);
        call.enqueue(new Callback<InvoiceFinalInvoiceGetResponse>() {
            @Override
            public void onResponse(Call<InvoiceFinalInvoiceGetResponse> call, Response<InvoiceFinalInvoiceGetResponse> response) {
                finalData=response.body();
                setFinallData(finalData);
            }

            @Override
            public void onFailure(Call<InvoiceFinalInvoiceGetResponse> call, Throwable t) {

            }
        });
    }

    private void getFunnelData(String id) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //InvoiceFunnelInvoiceGetResponse
        Call<InvoiceFunnelInvoiceGetResponse> call=service.getInvoiceListFunnelPOData(id);
        call.enqueue(new Callback<InvoiceFunnelInvoiceGetResponse>() {
            @Override
            public void onResponse(Call<InvoiceFunnelInvoiceGetResponse> call, Response<InvoiceFunnelInvoiceGetResponse> response) {
               funnelData=response.body();
                setFunnelData(funnelData);
            }

            @Override
            public void onFailure(Call<InvoiceFunnelInvoiceGetResponse> call, Throwable t) {

            }
        });

    }

    private void setFunnelData(InvoiceFunnelInvoiceGetResponse data) {
        String fromAddressdata = new String(), toAddressdata = new String(),invoice_no=new String();
        String po_invoice_data = new String(),termsConditions=new String(),total_without_gst=new String();
        String cgst_percentage_amount = new String(), sgst_percentage_amount = new String();
        String grandTotal=new String();

        fromAddressdata="From\n"+data.getInvoiceListDataModelList().get(0).getTemplate_name()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line1()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line2()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line3()+"\n";

//        toAddressdata="To\n"+data.getVendorDataModelList().get(0).getVendor_name()+"\n"
//                +data.getVendorDataModelList().get(0).getAddress();

        toAddressdata="To\n"+data.getVendorDataModelList().get(0).getClient_name()+"\n"
                +data.getVendorDataModelList().get(0).getAddress();

        po_invoice_data="Invoice#"+data.getInvoiceDataModelList().get(0).getInvoice_number()+"\n"
                +"Po No:"+data.getInvoiceDataModelList().get(0).getOrder_id()+"\n"
                +"Invoice Date:"+data.getInvoiceDataModelList().get(0).getDuedate();

        total_without_gst="Total without GST: ₹"+data.getInvoiceDataModelList().get(0).getTotal();
        termsConditions=data.getInvoiceDataModelList().get(0).getRemarks();
        cgst_percentage_amount = "CGST @" + data.getInvoiceDataModelList().get(0).getCgst() + "% ₹" + data.getInvoiceDataModelList().get(0).getCgst_amount();
        sgst_percentage_amount = "SGST @" +data.getInvoiceDataModelList().get(0).getSgst() + "% ₹" + data.getInvoiceDataModelList().get(0).getSgst_amount();
        grandTotal="Grand Total: ₹"+data.getInvoiceDataModelList().get(0).getGrand_total();

        Company.setText(data.getInvoiceListDataModelList().get(0).getTemplate_name());
        FromCompany.setText(fromAddressdata);
        ToCompany.setText(toAddressdata);
        InvoicePoData.setText(po_invoice_data);
        TermsConditions.setText(termsConditions);
        totalWithoutGst.setText(total_without_gst);
        CGSTPERCENTAMOUNT.setText(cgst_percentage_amount);
        SGSTPERCENTAMOUNT.setText(sgst_percentage_amount);
        GrandTotal.setText(grandTotal);

        invoiceProductsDataModels=data. getInvoiceProductsDataModels();
        Picasso.with(getContext()).load(base_url_image+data.getInvoiceListDataModelList().get(0).getLogo()).into( this.Logo);
        setAdapter(invoiceProductsDataModels);
    }

    private void setFinallData(InvoiceFinalInvoiceGetResponse data) {
        String fromAddressdata = new String(), toAddressdata = new String(),invoice_no=new String();
        String po_invoice_data = new String(),termsConditions=new String(),total_without_gst=new String();
        String cgst_percentage_amount = new String(), sgst_percentage_amount = new String();
        String grandTotal=new String();

        fromAddressdata="From\n"+data.getInvoiceListDataModelList().get(0).getTemplate_name()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line1()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line2()+"\n"
                +data.getInvoiceListDataModelList().get(0).getAddress_line3()+"\n";

//        toAddressdata="To\n"+data.getVendorDataModelList().get(0).getVendor_name()+"\n"
//                +data.getVendorDataModelList().get(0).getAddress();

        toAddressdata="To\n"+data.getVendorDataModelList().get(0).getClient_name()+"\n"
                +data.getVendorDataModelList().get(0).getAddress();
        po_invoice_data="Invoice#"+data.getInvoiceDataModelList().get(0).getInvoice_number()+"\n"
                +"Po No:"+data.getInvoiceDataModelList().get(0).getOrder_id()+"\n"
                +"Invoice Date:"+data.getInvoiceDataModelList().get(0).getDuedate();

        total_without_gst="Total without GST: ₹"+data.getInvoiceDataModelList().get(0).getTotal();
        termsConditions=data.getInvoiceDataModelList().get(0).getRemarks();
        cgst_percentage_amount = "CGST @" + data.getInvoiceDataModelList().get(0).getCgst() + "% ₹" + data.getInvoiceDataModelList().get(0).getCgst_amount();
        sgst_percentage_amount = "SGST @" +data.getInvoiceDataModelList().get(0).getSgst() + "% ₹" + data.getInvoiceDataModelList().get(0).getSgst_amount();
        grandTotal="Grand Total: ₹"+data.getInvoiceDataModelList().get(0).getGrand_total();

        Company.setText(data.getInvoiceListDataModelList().get(0).getTemplate_name());
        FromCompany.setText(fromAddressdata);
        ToCompany.setText(toAddressdata);
        InvoicePoData.setText(po_invoice_data);
        TermsConditions.setText(termsConditions);
        totalWithoutGst.setText(total_without_gst);
        CGSTPERCENTAMOUNT.setText(cgst_percentage_amount);
        SGSTPERCENTAMOUNT.setText(sgst_percentage_amount);
        GrandTotal.setText(grandTotal);

        invoiceProductsDataModels=data. getInvoiceProductsDataModels();
        Picasso.with(getContext()).load(base_url_image+data.getInvoiceListDataModelList().get(0).getLogo()).into( this.Logo);
        setAdapter(invoiceProductsDataModels);
    }

    private void setAdapter(List<InvoiceProductsDataModel> invoiceProductsDataModels){
        InvoiceFinalFunnelItemAdapter adapter=new InvoiceFinalFunnelItemAdapter(getContext(),invoiceProductsDataModels);
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
                    //new PODataFinalFunnelFragment.DownloadFileFromURL().execute(file_url);
                }else{
                    //Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();
                }
        }
    }

    public void toConvertHtmlStringToPdfAPI(){
        //String strApiKey = "af6f46c4-471c-4ce8-8b70-3c78e8ec796b";//Web api Parameter
        String strApiKey="7bfe5430-580d-4d84-8047-a0bee8763a8a";
        String strValue = "<h1>An <strong>Example</strong>HTML String</h1>"; //Web api Parameter
        String webUrl = "http://api.html2pdfrocket.com/pdf";//Web Api Url
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("apiKey", strApiKey);
        params.put("value", strValue);
        webUrl = "http://api.html2pdfrocket.com/pdf";
        String mUrl = webUrl;
        getPdfFromApi(params);


    }

    private void  getPdfFromApi(HashMap<String,String> map) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstancepdf().create(GetDataService.class);
        Call<Object> call=service.postPdffile(map);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response1) {
                try {
                    if (response1 != null) {
                        byte[] respone=(byte[]) response1.body();
                        Random r = new Random();
                        int i1 = r.nextInt(80 - 65) + 65;
                        String fileName = "sample" + i1 + ".pdf";
                        File root = new File(Environment.getExternalStorageDirectory(), "HtmlStringToPDF");
                        if (!root.exists()) {
                            root.mkdirs();
                        }
                        if (root.exists()) {


                            //File gpxfile = new File(root, "sample" + i1 + ".pdf");
                            File gpxfile = new File(root, "abcc.pdf");
                            OutputStream op = new FileOutputStream(gpxfile);
                            gpxfile.setWritable(true);
                            op.write(respone);
                            op.flush();
                            op.close();


                        }
                        //tvConversionContent.setText(response.toString());
                        Toast.makeText(getContext(), "Content Write To the file name sample" + i1 + ".pdf in WebPageToImage Directory", Toast.LENGTH_LONG).show();
                        System.out.print("Response ----------------------" + respone.toString());

                    }
                } catch (Exception e) {
                    Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
        //InvoiceFunnelInvoiceGetResponse
//        Call<InvoiceFunnelInvoiceGetResponse> call=service.getInvoiceListFunnelPOData(id);
//        call.enqueue(new Callback<InvoiceFunnelInvoiceGetResponse>() {
//            @Override
//            public void onResponse(Call<InvoiceFunnelInvoiceGetResponse> call, Response<InvoiceFunnelInvoiceGetResponse> response) {
//                funnelData=response.body();
//                setFunnelData(funnelData);
//            }
//
//            @Override
//            public void onFailure(Call<InvoiceFunnelInvoiceGetResponse> call, Throwable t) {
//
//            }
//        });

    }

}

