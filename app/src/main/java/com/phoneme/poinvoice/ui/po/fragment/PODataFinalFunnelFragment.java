package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.phoneme.poinvoice.ui.po.adapter.POFinalFunnelItemAdapter;
import com.phoneme.poinvoice.ui.po.model.POItemData;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFinalPOGetResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFunnelPOGetResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PODataFinalFunnelFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    private RecyclerView recyclerView;
    private GeneratedListFinalPOGetResponse finalData;
    private GeneratedListFunnelPOGetResponse funnelData;
    private TextView Company, FromCompany, ToCompany, TermsConditions, TotalWithoutGST, CGSTPERCENTAMOUNT, SGSTPERCENTAMOUNT;
    private TextView GrandTotal;
    private ImageView Logo;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";
    private List<POItemData> poItemDataList;

    //private PODataModel poDataModel;

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
        String id = getArguments().getString("id");
        String organization = getArguments().getString("organization");

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


        if (organization.equalsIgnoreCase("2") || organization.equalsIgnoreCase("4") || organization.equalsIgnoreCase("5")) {
            getFinalData(id);
        } else {
            getFunnelData(id);
        }
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
}
