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
import com.phoneme.poinvoice.ui.po.model.VendorDataModel;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.ViewHolder> {
    private Context mcontext;
    private  OnItemClickListener listener;
    private List<VendorDataModel> vendorDataModelList;
    private SimpleDateFormat formatter;
    public VendorListAdapter(Context context){
        this.mcontext=context;
    }
    public VendorListAdapter(Context context, OnItemClickListener listener){
        this.mcontext=context;
        this.listener=listener;
    }

    public VendorListAdapter(Context context, OnItemClickListener listener, List<VendorDataModel> vendorDataModelList){
        this.mcontext=context;
        this.listener=listener;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.vendorDataModelList=vendorDataModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,VendorName,Address,Email,State,GSTIN,Pam;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button editButton;

        public ViewHolder(View v) {
            super(v);
            editButton=(Button)v.findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            VendorName=(TextView)v.findViewById(R.id.vendor_name);
            Address=(TextView)v.findViewById(R.id.address);
            Email=(TextView)v.findViewById(R.id.email);
            State=(TextView)v.findViewById(R.id.state);
            GSTIN=(TextView)v.findViewById(R.id.gstin);
            Pam=(TextView)v.findViewById(R.id.pam);
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
        private void setData2(int position){
            this.VendorName.setText(vendorDataModelList.get(position).getVendor_name());
            this.Address.setText(vendorDataModelList.get(position).getAddress());
            this.Email.setText(vendorDataModelList.get(position).getEmail());
            this.State.setText(vendorDataModelList.get(position).getState());
            this.GSTIN.setText(vendorDataModelList.get(position).getGSTIN());
            this.Pam.setText(vendorDataModelList.get(position).getPAM());


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_vendor_list_new_ui,viewGroup,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
        if(position==(this.vendorDataModelList.size()-1)){
            Toast.makeText(mcontext,"Last item", Toast.LENGTH_LONG).show();
        }
          vh.setData2(position);



    }
    @Override
    public int getItemCount(){
        return     this.vendorDataModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
    }

}
