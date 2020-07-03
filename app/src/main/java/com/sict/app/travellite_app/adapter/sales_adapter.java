package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.model.tour_sale;

import java.util.List;

public class sales_adapter extends RecyclerView.Adapter<sales_adapter.ViewHolder> {
    private Context context;
    private List<tour_sale> list;
    OnCallBack onCallBack;

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    public sales_adapter(Context context, List<tour_sale> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getList_image().getImage1()).into(holder.img);

        holder.txt_name.setText(list.get(position).getName());
        holder.txt_sale.setText(String.valueOf("Khuyến mãi -"+list.get(position).getSale()+"%"));
        int rest = (list.get(position).getMoney().getAdult()*list.get(position).getSale())/100;
        holder.txt_rest.setText(String.valueOf("$"+rest));
        holder.txt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBack.OnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_sale, txt_rest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt_sale = (TextView)itemView.findViewById(R.id.txt_sale);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name_tour);
            txt_rest = (TextView)itemView.findViewById(R.id.txt_rest);
        }
    }
    public interface OnCallBack {
        void OnItemClick(int i);
    }
}
