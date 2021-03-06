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

import org.w3c.dom.Text;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.ViewHolder> {
    private Context mcontext;
    private  OnItemClickListener listener;
    public VendorListAdapter(Context context){
        this.mcontext=context;
    }
    public VendorListAdapter(Context context, OnItemClickListener listener){
        this.mcontext=context;
        this.listener=listener;
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
            this.VendorName.setText("Tech Data Advanced Solutions");
            this.Address.setText("C/O- Schenker India Private Limited 273/2, Shahabad Mohamadpur, New Delhi, 110062");
            this.Email.setText("Amit.Kumar4@techdata.com");
            this.State.setText("Delhi");
            this.GSTIN.setText("07AAACO3686M1ZZ");
            this.Pam.setText("AAACO3686M");
        }
    }

    @Override
    public VendorListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_vendor_list,viewGroup,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
          vh.setData2(position);



    }
    @Override
    public int getItemCount(){
        return 3;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
    }
}
