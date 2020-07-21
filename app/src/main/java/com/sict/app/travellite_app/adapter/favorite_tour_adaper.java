package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.model.tour;

import java.util.List;

public class favorite_tour_adaper extends RecyclerView.Adapter<favorite_tour_adaper.ViewHolder> {
    List<tour> list;
    Context context;
    OnCallBack onCallBack;
    OnDetailTour detailTour;

    public void setDetailTour(OnDetailTour detailTour) {
        this.detailTour = detailTour;
    }

    public void setListener(OnCallBack listener) {
        this.onCallBack = listener;
    }
    public favorite_tour_adaper(List<tour> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_tour,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_cost.setText(String.valueOf("$"+list.get(position).getMoney().getAdult()));
        holder.txt_name.setText(list.get(position).getName());
        Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getImage().getImage1()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_cost, txt_detail;
        Button btn_quite_favorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name);
            txt_cost = (TextView)itemView.findViewById(R.id.txt_money);
            btn_quite_favorite = (Button)itemView.findViewById(R.id.btn_favotrite);
            btn_quite_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   onCallBack.OnItemClick(getPosition());
                }
            });
            txt_detail = (TextView) itemView.findViewById(R.id.btn_detail);
            txt_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailTour.detailTour(getPosition());
                }
            });
        }
    }
    public interface OnCallBack {
        void OnItemClick(int i);
    }
    public interface OnDetailTour{
        void detailTour(int i);
    }
}
