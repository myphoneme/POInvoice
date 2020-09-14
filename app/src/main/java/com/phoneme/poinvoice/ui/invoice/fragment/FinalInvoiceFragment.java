package com.phoneme.poinvoice.ui.invoice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.InvoiceViewModel;
import com.phoneme.poinvoice.ui.invoice.adapter.InvoiceFinalFunnelItemAdapter;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceProductsDataModel;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceFinalInvoiceGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceFunnelInvoiceGetResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String id = getArguments().getString("id");
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


        if (name.equalsIgnoreCase("2") || name.equalsIgnoreCase("4") || name.equalsIgnoreCase("5")) {
            getFinalData(id);
        } else {
            getFunnelData(id);
        }
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
        FromCompany.setText("44"+fromAddressdata);
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
}
