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
import com.phoneme.poinvoice.ui.po.model.PoTemplateDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class POTemplateAdapter extends RecyclerView.Adapter<POTemplateAdapter.ViewHolder>{
    private Context mcontext;
    private OnItemClickListener listener;
    private List<PoTemplateDataModel> poTemplateDataModelList;
    private String base_url_image="http://support.phoneme.in/assets/images/userimage/";
    public POTemplateAdapter(Context context){
        this.mcontext=context;
    }
    public POTemplateAdapter(Context context,OnItemClickListener listener){
        this.mcontext=context;
        this.listener=listener;
    }

    public POTemplateAdapter(Context context, OnItemClickListener listener, List<PoTemplateDataModel> poTemplateDataModelList){
        this.mcontext=context;
        this.listener=listener;
        this.poTemplateDataModelList=poTemplateDataModelList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, company_name, allocated_users, status, createdat;
        //private ProjectModel projectModel;
        private TextView edit,Title,State,TemplateName,AddressLine1,AddressLine2,AddressLine3,GSTNO;
        private CardView cardView;
        private ImageView imageView;
        //private SimpleDraweeView projectLogo;
        private RelativeLayout relativeLayoutView;
        private Button editButton;
        private ImageView Logo;

        public ViewHolder(View v) {
            super(v);
            editButton=(Button)v.findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            Title=(TextView)v.findViewById(R.id.title);
            State=(TextView)v.findViewById(R.id.state);
            TemplateName=(TextView)v.findViewById(R.id.template_name);
            AddressLine1=(TextView)v.findViewById(R.id.address_line1);
            AddressLine2=(TextView)v.findViewById(R.id.address_line2);
            AddressLine3=(TextView)v.findViewById(R.id.address_line3);
            GSTNO=(TextView)v.findViewById(R.id.gst_no);
            Logo=(ImageView)v.findViewById(R.id.logo);
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
            this.Title.setText(poTemplateDataModelList.get(position).getTitle());
            this.State.setText(poTemplateDataModelList.get(position).getState());
            this.TemplateName.setText(poTemplateDataModelList.get(position).getTemplate_name());
            this.AddressLine1.setText(poTemplateDataModelList.get(position).getAddress_line1());
            this.AddressLine2.setText(poTemplateDataModelList.get(position).getAddress_line2());
            this.AddressLine3.setText(poTemplateDataModelList.get(position).getAddress_line3());
            this.GSTNO.setText(poTemplateDataModelList.get(position).getGSTN_NO());

            Picasso.with(mcontext).load(base_url_image+poTemplateDataModelList.get(position).getLogo()).into( this.Logo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.adapter_po_template_new_ui,viewGroup,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder vh, int position){
//        vh.setData(this.projectModelList.get(position),position);
        vh.setData2(position);



    }
    @Override
    public int getItemCount(){
        return this.poTemplateDataModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick2(int position);
    }

    public void setNewData(List<PoTemplateDataModel> poTemplateDataModelList){
        this.poTemplateDataModelList=poTemplateDataModelList;
    }
}
