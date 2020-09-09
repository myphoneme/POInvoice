package com.phoneme.poinvoice.ui.invoice.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.phoneme.poinvoice.ui.invoice.model.InvoiceManagementDataModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceResponseModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InvoiceManagementAdapter extends RecyclerView.Adapter<InvoiceManagementAdapter.ViewHolder> {
    private Context mcontext;
    private OnItemClickListener listener;
    private String[] colors = {"#6C18A4", "#FF7200", "#3827B4", "#5FB427"};
    private List<InvoiceManagementDataModel> InvoiceManagementDataModelList;
    public InvoiceManagementAdapter(Context context){
        this.mcontext=context;
    }

    public InvoiceManagementAdapter(Context context, OnItemClickListener listener){
        this.listener=listener;
        this.mcontext=context;
    }

    public InvoiceManagementAdapter(Context context, OnItemClickListener listener,List<InvoiceManagementDataModel> data){
        this.listener=listener;
        this.mcontext=context;
        this.InvoiceManagementDataModelList=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_invoice_management_new_ui,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return InvoiceManagementDataModelList.size();
    }

    public void onBindViewHolder(ViewHolder vh, int position){
        vh.setData(this.InvoiceManagementDataModelList.get(position),position);



    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Title, Value,Phoneme_value,Funnel_value, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,invoiceNumber;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button paymentButton,poUploadButton;

        public ViewHolder(View v) {
            super(v);
            cardView=(CardView)v.findViewById(R.id.card_id);
            Title=(TextView)v.findViewById(R.id.title);
            Value=(TextView)v.findViewById(R.id.value);
            Phoneme_value=(TextView)v.findViewById(R.id.phoneme_value);
            Funnel_value=(TextView)v.findViewById(R.id.funnel_value);
//            paymentButton=(Button)v.findViewById(R.id.payment_button);
//            paymentButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(getAdapterPosition());
//                }
//            });
//            poUploadButton=(Button)v.findViewById(R.id.add_new_po_button);
//            poUploadButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick2(getAdapterPosition());
//                }
//            });
//            invoiceNumber=(TextView)v.findViewById(R.id.invoice_number);
//            invoiceNumber.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick3(getAdapterPosition());
//                }
//            });
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
        public void setData(InvoiceManagementDataModel data,int position){
            Title.setText(data.getTitle());
            if(data.getValue()==null){
                Value.setText("\u20B9"+"0");
            }else{
                BigDecimal rounded = new BigDecimal(data.getValue()).setScale(2, RoundingMode.HALF_UP);
                Value.setText("\u20B9"+rounded);
            }



            if(data.getPhoneme_value()==null){
                Phoneme_value.setText("\u20B9"+"0");
            }else{
                BigDecimal rounded2 = new BigDecimal(data.getPhoneme_value()).setScale(2, RoundingMode.HALF_UP);
                Phoneme_value.setText("\u20B9"+rounded2);
            }
            if(data.getFunnel_value()==null){
                Funnel_value.setText("\u20B9"+"0");
            }else{
                BigDecimal rounded3 = new BigDecimal(data.getFunnel_value()).setScale(2, RoundingMode.HALF_UP);
                Funnel_value.setText("\u20B9"+rounded3);
            }
            cardView.setCardBackgroundColor(Color.parseColor(colors[position]));

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
        void onItemClick3(int position);
    }
}
