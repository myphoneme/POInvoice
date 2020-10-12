package com.phoneme.poinvoice.ui.invoice.adapter;

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
import com.phoneme.poinvoice.ui.invoice.model.ClientDataModel;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceRowModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {
    private Context mcontext;
    private OnItemClickListener listener;
    private List<ClientDataModel> clientDataModelList;
    private SimpleDateFormat formatter;

    public ClientListAdapter(Context context) {
        this.mcontext = context;
    }

    public ClientListAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        this.mcontext = context;
    }

    public ClientListAdapter(Context context, OnItemClickListener listener, List<ClientDataModel> clientDataModelList) {
        this.listener = listener;
        this.mcontext = context;
        this.clientDataModelList = clientDataModelList;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.adapter_client_list_new_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        //return this.invoiceRowModelList.size();
        if (clientDataModelList != null && !clientDataModelList.isEmpty() && clientDataModelList.size() > 0) {
            return this.clientDataModelList.size();
        }

        return 0;
    }

    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.setData2(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName, Address, Email, State, GSTIN, PAN;
        //private ProjectModel projectModel;
        private TextView edit, invoiceNumber, date, Project, TotalAmount, Vendor, percentagePOReceived, poReceived, OrderId;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button editButton, poUploadButton;

        public ViewHolder(View v) {
            super(v);
            editButton = (Button) v.findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
//            poUploadButton=(Button)v.findViewById(R.id.add_new_po_button);
//            poUploadButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick2(getAdapterPosition());
//                }
//            });
            clientName = (TextView) v.findViewById(R.id.client_name);
            Address = (TextView) v.findViewById(R.id.address);
            Email = (TextView) v.findViewById(R.id.email);
            State = (TextView) v.findViewById(R.id.state);
            GSTIN = (TextView) v.findViewById(R.id.gstin);
            PAN = (TextView) v.findViewById(R.id.pan);
//            invoiceNumber.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick3(getAdapterPosition());
//                }
//            });
//
//            date=(TextView)v.findViewById(R.id.date);
//            Project=(TextView)v.findViewById(R.id.project);
//            TotalAmount=(TextView)v.findViewById(R.id.total_amount);
//            Vendor =(TextView)v.findViewById(R.id.vendor_name);
//            percentagePOReceived=(TextView)v.findViewById(R.id.percentage_po_received);
//            poReceived=(TextView)v.findViewById(R.id.po_received);
//            OrderId=(TextView)v.findViewById(R.id.order_id);

        }

        public void setData2(int position) {
            if (clientDataModelList.get(position) != null) {
                clientName.setText(clientDataModelList.get(position).getClient_name());
                Address.setText(clientDataModelList.get(position).getAddress());
                if (clientDataModelList.get(position).getEmail() != null && !clientDataModelList.get(position).getEmail().isEmpty() && clientDataModelList.get(position).getEmail().length() > 0) {
                    Email.setText(clientDataModelList.get(position).getEmail());
                } else {
                    Email.setText("");

                }
                if (clientDataModelList.get(position).getState_name() != null && !clientDataModelList.get(position).getState_name().isEmpty() && clientDataModelList.get(position).getState_name().length() > 0) {
                    State.setText(clientDataModelList.get(position).getState_name());
                } else {
                    State.setText("");
                }
                if (clientDataModelList.get(position).getGSTIN() != null && !clientDataModelList.get(position).getGSTIN().isEmpty() && clientDataModelList.get(position).getGSTIN().length() > 0) {
                    GSTIN.setText(clientDataModelList.get(position).getGSTIN());
                } else {
                    GSTIN.setText("");
                }

                if (clientDataModelList.get(position).getPan() != null && !clientDataModelList.get(position).getPan().isEmpty() && clientDataModelList.get(position).getPan().length() > 0) {
                    PAN.setText(clientDataModelList.get(position).getPan());
                } else {
                    PAN.setText("");
                }

            }

//            Float gst_amount=0f;
//            if(invoiceRowModelList.get(position).getCgst_amount()!=null && !invoiceRowModelList.get(position).getCgst_amount().isEmpty() && invoiceRowModelList.get(position).getCgst_amount().length()>0){
//                gst_amount=gst_amount+   Float.parseFloat(invoiceRowModelList.get(position).getCgst_amount());
//            }
//            if(invoiceRowModelList.get(position).getSgst_amount()!=null && !invoiceRowModelList.get(position).getSgst_amount().isEmpty() && invoiceRowModelList.get(position).getSgst_amount().length()>0){
//                gst_amount=gst_amount+Float.parseFloat(invoiceRowModelList.get(position).getSgst_amount());
//            }
//            BigDecimal rounded = new BigDecimal(""+gst_amount).setScale(2, RoundingMode.HALF_UP);
//
//
//            //int gst_amount=Integer.parseInt(invoiceRowModelList.get(position).getCgst_amount())+Integer.parseInt(invoiceRowModelList.get(position).getSgst_amount());
//
//            //this.date.setText(invoiceRowModelList.get(position).getDuedate());
//            this.Project.setText("\u20B9"+gst_amount);//To be removed
//            this.TotalAmount.setText("\u20B9"+invoiceRowModelList.get(position).getGrand_total());
//            this.invoiceNumber.setText(invoiceRowModelList.get(position).getInvoice_number());
//            this.Vendor.setText(invoiceRowModelList.get(position).getVendor_name());
//            this.percentagePOReceived.setText(invoiceRowModelList.get(position).getPercentage_po_received()+"%");
//            this.poReceived.setText("PO/IT-EXP/2020-21/17");
//            this.OrderId.setText(invoiceRowModelList.get(position).getOrder_id());
//
//
//            try {
//                Date date = formatter.parse(invoiceRowModelList.get(position).getDuedate());
//                SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
//                this.date.setText(dt1.format(date));
//
//            }catch(ParseException e){
//
//            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemClick2(int position);

        void onItemClick3(int position);
    }

    public void setNewData(List<ClientDataModel> clientDataModelList){
        this.clientDataModelList = clientDataModelList;
    }
}
