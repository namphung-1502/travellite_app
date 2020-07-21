package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.media.Image;
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
import com.sict.app.travellite_app.model.hotel;

import java.util.List;

public class favorite_hotel_adapter extends RecyclerView.Adapter<favorite_hotel_adapter.ViewHolder> {
    private List<hotel> list;
    private Context context;
    OnCallBack onCallBack;
    OnDetailHotel detailHotel;

    public void setDetailHotel(OnDetailHotel detailHotel) {
        this.detailHotel = detailHotel;
    }

    public void setListener(OnCallBack listener) {
        this.onCallBack = listener;
    }
    public favorite_hotel_adapter(List<hotel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_hotel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txt_name.setText(list.get(position).getName());
         holder.txt_place.setText(list.get(position).getPlace());
         holder.txt_cost.setText(String.valueOf("$"+list.get(position).getCost()));
         Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_place,txt_cost;
        Button btn_quite_favorite, btn_detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_hotel);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name_hotel);
            txt_place = (TextView) itemView.findViewById(R.id.txt_place);
            txt_cost = (TextView)itemView.findViewById(R.id.txt_cost);
            btn_quite_favorite = (Button)itemView.findViewById(R.id.btn_favotrite);
            btn_quite_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCallBack.OnItemClick(getPosition());
                }
            });
            btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   detailHotel.DetailHotel(getPosition());
                }
            });
        }
    }
    public interface OnCallBack {
        void OnItemClick(int i);
    }
    public interface OnDetailHotel{
        void DetailHotel(int i);
    }
}
