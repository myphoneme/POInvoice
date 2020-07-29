package com.phoneme.poinvoice.ui.invoice.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
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

import org.w3c.dom.Text;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {
    private Context mcontext;
    private OnItemClickListener listener;
    public InvoiceListAdapter(Context context){
        this.mcontext=context;
    }

    public InvoiceListAdapter(Context context,OnItemClickListener listener){
        this.listener=listener;
        this.mcontext=context;
    }

    @Override
    public InvoiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_invoice_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return 3;
    }

    public void onBindViewHolder(InvoiceListAdapter.ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
        vh.setData2(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,invoiceNumber,date,Project,TotalAmount,Client,percentagePOReceived,poReceived,OrderId;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button paymentButton,poUploadButton;

        public ViewHolder(View v) {
            super(v);
            paymentButton=(Button)v.findViewById(R.id.payment_button);
            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            poUploadButton=(Button)v.findViewById(R.id.add_new_po_button);
            poUploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick2(getAdapterPosition());
                }
            });
            invoiceNumber=(TextView)v.findViewById(R.id.invoice_number);
            invoiceNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick3(getAdapterPosition());
                }
            });

            date=(TextView)v.findViewById(R.id.date);
            Project=(TextView)v.findViewById(R.id.project);
            TotalAmount=(TextView)v.findViewById(R.id.total_amount);
            Client=(TextView)v.findViewById(R.id.client);
            percentagePOReceived=(TextView)v.findViewById(R.id.percentage_po_received);
            poReceived=(TextView)v.findViewById(R.id.po_received);
            OrderId=(TextView)v.findViewById(R.id.order_id);

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

        public void setData2(int position){
            this.date.setText("06 Dec 2020");
            this.Project.setText("Ticketing System1");
            this.TotalAmount.setText("\u20B9 61384");
            this.invoiceNumber.setText("758703");
            this.Client.setText("2");
            this.percentagePOReceived.setText("23.4%");
            this.poReceived.setText("PO/IT-EXP/2020-21/17");
            this.OrderId.setText("2514547");
        }

//        public void setData(ProjectModel item, int position) {
//            this.projectModel = item;
//            this.title.setText(Html.fromHtml(this.projectModel.getName()));
//
//            this.company_name.setText(Html.fromHtml(this.projectModel.getCompany_name()));
//            String colorText=textcolor.get(position%4);
//            this.company_name.setTextColor(Color.parseColor(colorText));
//            String color=backgroundcolor.get(position%4);
////            this.cardView.setBackgroundColor(Color.parseColor(color));
//            //this.relativeLayoutView.setBackgroundColor(Color.parseColor("#ffffff"));
//            this.relativeLayoutView.setBackgroundColor(Color.parseColor(color));
//            if(item.getImage()!=null && item.getImage().length()>0) {
//                System.out.println("imageurl=" + item.getImage());
//                Uri uri = Uri.parse(item.getImage());
//                projectLogo.setImageURI(uri);
//            }else{
//                System.out.println("no image imageurl=" + item.getImage());
//                Uri uri = Uri.parse("android.resource://com.phoneme.ticketing/drawable/icon1.png");
//                projectLogo.setImageURI(uri);
//            }
//        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
        void onItemClick3(int position);
    }
}
