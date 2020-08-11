package com.phoneme.poinvoice.ui.po.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.phoneme.poinvoice.ui.po.model.POItemData;

import org.w3c.dom.Text;

import java.util.List;

public class FinalFunnelItemAdapter extends RecyclerView.Adapter<FinalFunnelItemAdapter.ViewHolder> {
    private Context mcontex;
    private List<POItemData> poItemDataList;
    public FinalFunnelItemAdapter(Context context,List<POItemData> poitemDatalist){
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
            item.setText(poItemDataList.get(position).getItem());
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
        public FinalFunnelItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view= LayoutInflater.from(this.mcontex).inflate(R.layout.adapter_poitem_list,viewGroup,false);
            return new FinalFunnelItemAdapter.ViewHolder(view);
        }

    public void onBindViewHolder(FinalFunnelItemAdapter.ViewHolder vh, int position){
        vh.setData2(position);
    }

}
