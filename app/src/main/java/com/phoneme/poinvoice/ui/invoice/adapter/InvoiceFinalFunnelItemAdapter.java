package com.phoneme.poinvoice.ui.invoice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.invoice.model.InvoiceProductsDataModel;
import com.phoneme.poinvoice.ui.po.model.POItemData;

import java.util.List;

public class InvoiceFinalFunnelItemAdapter extends RecyclerView.Adapter<InvoiceFinalFunnelItemAdapter.ViewHolder> {
    private Context mcontex;
//    private List<POItemData> poItemDataList;
    List<InvoiceProductsDataModel> poItemDataList ;
    public InvoiceFinalFunnelItemAdapter(Context context, List<InvoiceProductsDataModel> poitemDatalist){
        this.mcontex=context;
        this.poItemDataList=poitemDatalist;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item,item_description,quantity,unitPrice,subTotal;
        public ViewHolder(View v) {
            super(v);
            item=(TextView) v.findViewById(R.id.item);
            item_description=(TextView)v.findViewById(R.id.item_description);
            quantity=(TextView)v.findViewById(R.id.qty);
            unitPrice=(TextView)v.findViewById(R.id.unit_price);
            subTotal=(TextView)v.findViewById(R.id.subtotal);
        }
        private void setData2(int position){
            item.setText(poItemDataList.get(position).getProduct());
            item_description.setText(poItemDataList.get(position).getDescription());
            quantity.setText(poItemDataList.get(position).getQty());
            unitPrice.setText("₹"+poItemDataList.get(position).getPrice());
            subTotal.setText("₹"+poItemDataList.get(position).getSubtotal());
        }
    }

        @Override
        public int getItemCount(){
            return this.poItemDataList.size();
        }


        @Override
        public InvoiceFinalFunnelItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view= LayoutInflater.from(this.mcontex).inflate(R.layout.adapter_invoice_final_funnel_item_list,viewGroup,false);
            return new InvoiceFinalFunnelItemAdapter.ViewHolder(view);
        }

    public void onBindViewHolder(InvoiceFinalFunnelItemAdapter.ViewHolder vh, int position){
        vh.setData2(position);
    }

}
