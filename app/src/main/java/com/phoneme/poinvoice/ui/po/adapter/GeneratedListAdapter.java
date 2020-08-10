package com.phoneme.poinvoice.ui.po.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class GeneratedListAdapter extends RecyclerView.Adapter<GeneratedListAdapter.ViewHolder> {
    private Context mcontext;
    private OnItemClickListener listener;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";
    private List<PODataModel> poDataModelList;
    public GeneratedListAdapter(Context context){
        this.mcontext=context;
    }

    public GeneratedListAdapter(Context context,OnItemClickListener listener){
        this.mcontext=context;
        this.listener=listener;
    }

    public GeneratedListAdapter(Context context, OnItemClickListener listener, List<PODataModel> poDataModelList){
        this.mcontext=context;
        this.listener=listener;
        this.poDataModelList=poDataModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,Date,Vendor,TotalAmount,Subject,GST,percentagePaymentReceived,poNO,Invoice;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button addPaymentButton,addInvoice;

        public ViewHolder(View v) {
            super(v);
            addPaymentButton=(Button)v.findViewById(R.id.add_payment);
            addInvoice=(Button)v.findViewById(R.id.add_invoice);
            addPaymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            addInvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick2(getAdapterPosition());
                }
            });
            Date=(TextView)v.findViewById(R.id.date);
            Vendor=(TextView)v.findViewById(R.id.vendor);
            TotalAmount=(TextView)v.findViewById(R.id.total_amount);
            Subject=(TextView)v.findViewById(R.id.subject);
            GST=(TextView)v.findViewById(R.id.gst);
            percentagePaymentReceived=(TextView)v.findViewById(R.id.percentage_payment_received);
            percentagePaymentReceived.setVisibility(View.GONE);
            poNO=(TextView)v.findViewById(R.id.po_no);
            poNO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick3(getAdapterPosition());
                }
            });
            imageView=(ImageView)v.findViewById(R.id.organisation);
            Invoice=(TextView)v.findViewById(R.id.invoice);
//            title=(TextView)v.findViewById(R.id.name);
//            company_name=(TextView)v.findViewById(R.id.company_name);
//            imageView=(ImageView)v.findViewById(R.id.image);
//            relativeLayoutView=(RelativeLayout)v.findViewById(R.id.relativelayoutview);
//            projectLogo=(SimpleDraweeView)v.findViewById(R.id.project_logo_image);
//            relativeLayoutView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick2(getAdapterPosition());
//                }
//            });
            //cardView=(CardView)v.findViewById(R.id.cardid);
        }

        private void setData2(int position){
            int gst_amount=0;
            if(poDataModelList.get(position).getGsttype().equalsIgnoreCase("match")){
                gst_amount=Integer.parseInt(poDataModelList.get(position).getCgst_amount());
                gst_amount=gst_amount+Integer.parseInt(poDataModelList.get(position).getSgst_amount());
            }else{
                gst_amount=Integer.parseInt(poDataModelList.get(position).getIgst_amount());
            }


//            if(poDataModelList.get(position).getCgst_amount()!=null){
//
//            }
//            if(poDataModelList.get(position).getSgst_amount()!=null){
//
//            }


            this.Date.setText(poDataModelList.get(position).getPo_date());
            this.Vendor.setText(poDataModelList.get(position).getVendor_name());
            this.TotalAmount.setText("\u20B9 "+poDataModelList.get(position).getGrand_total());
            this.Subject.setText(poDataModelList.get(position).getSubject());
            this.GST.setText("\u20B9"+gst_amount);
            this.percentagePaymentReceived.setText("1%");
            this.poNO.setText(poDataModelList.get(position).getPo_number());
            this.Invoice.setText(poDataModelList.get(position).getInvoice_no());

            Picasso.with(mcontext).load(base_url_image+poDataModelList.get(position).getCompany_logo()).into( this.imageView);
        }
    }

    @Override
    public GeneratedListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_generated_list2,viewGroup,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(GeneratedListAdapter.ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
        vh.setData2(position);



    }
    @Override
    public int getItemCount(){
        return this.poDataModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
        void onItemClick3(int position);
    }
}
