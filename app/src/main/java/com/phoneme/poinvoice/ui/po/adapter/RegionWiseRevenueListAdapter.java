package com.phoneme.poinvoice.ui.po.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.ui.po.model.PODataModel;
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class RegionWiseRevenueListAdapter extends RecyclerView.Adapter<RegionWiseRevenueListAdapter.ViewHolder> {
    private Context mcontext;
    private  OnItemClickListener listener;
    private List<VendorDataModel> vendorDataModelList;
    private List<PODataModel> poDataModelList;
    private SimpleDateFormat formatter;
    public RegionWiseRevenueListAdapter(Context context){
        this.mcontext=context;
    }
    public RegionWiseRevenueListAdapter(Context context,List<PODataModel> poDataModels){
        this.mcontext=context;
        this.poDataModelList=poDataModels;
    }

    public RegionWiseRevenueListAdapter(Context context, OnItemClickListener listener){
        this.mcontext=context;
        this.listener=listener;
    }

    public RegionWiseRevenueListAdapter(Context context, OnItemClickListener listener, List<VendorDataModel> vendorDataModelList){
        this.mcontext=context;
        this.listener=listener;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.vendorDataModelList=vendorDataModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,VendorName,Address,Email,State,GSTIN,Pam;
        private TextView Region,Revenue;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button editButton;

        public ViewHolder(View v) {
            super(v);
//            editButton=(Button)v.findViewById(R.id.edit_button);
//            editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(getAdapterPosition());
//                }
//            });
            Revenue=(TextView)v.findViewById(R.id.revenue);
            Region=(TextView)v.findViewById(R.id.region);
//            Email=(TextView)v.findViewById(R.id.email);
//            State=(TextView)v.findViewById(R.id.state);
//            GSTIN=(TextView)v.findViewById(R.id.gstin);
//            Pam=(TextView)v.findViewById(R.id.pam);
        }

        private void setData2(int position){
//            this.VendorName.setText(vendorDataModelList.get(position).getVendor_name());
//            this.Address.setText(vendorDataModelList.get(position).getAddress());
//            this.Email.setText(vendorDataModelList.get(position).getEmail());
//            this.State.setText(vendorDataModelList.get(position).getState());
//            this.GSTIN.setText(vendorDataModelList.get(position).getGSTIN());
//            this.Pam.setText(vendorDataModelList.get(position).getPAM());


        }
        private void setData3(int position){
            this.Region.setText(poDataModelList.get(position).getVendor_state_name());
            this.Revenue.setText(poDataModelList.get(position).getInvoice_amount());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_regionwiserevenue_list_new_ui,viewGroup,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
//        if(position==(this.vendorDataModelList.size()-1)){
//            Toast.makeText(mcontext,"Last item", Toast.LENGTH_LONG).show();
//        }
//          vh.setData2(position);
        vh.setData3(position);



    }
    @Override
    public int getItemCount(){
        //return     this.vendorDataModelList.size();
        return this.poDataModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
    }

}
