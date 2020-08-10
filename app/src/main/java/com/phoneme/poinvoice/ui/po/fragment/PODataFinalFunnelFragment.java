package com.phoneme.poinvoice.ui.po.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.ui.invoice.InvoiceViewModel;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFinalPOGetResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListFunnelPOGetResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PODataFinalFunnelFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceViewModel =
                ViewModelProviders.of(this).get(InvoiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_final_invoice, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");
        String organisation = getArguments().getString("organisation");
        if(organisation.equalsIgnoreCase("2")||organisation.equalsIgnoreCase("4")||organisation.equalsIgnoreCase("5")){
            getFinalData(id);
        }else{
            getFunnelData(id);
        }
    }

    private void getFinalData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListFinalPOGetResponse> call=service.getGeneratedListFinalPOData(id);
        call.enqueue(new Callback<GeneratedListFinalPOGetResponse>() {
            @Override
            public void onResponse(Call<GeneratedListFinalPOGetResponse> call, Response<GeneratedListFinalPOGetResponse> response) {

            }

            @Override
            public void onFailure(Call<GeneratedListFinalPOGetResponse> call, Throwable t) {

            }
        });
    }

    private void getFunnelData(String id){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GeneratedListFunnelPOGetResponse> call=service.getGeneratedListFunnelPOData(id);
        call.enqueue(new Callback<GeneratedListFunnelPOGetResponse>() {
            @Override
            public void onResponse(Call<GeneratedListFunnelPOGetResponse> call, Response<GeneratedListFunnelPOGetResponse> response) {

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
}
