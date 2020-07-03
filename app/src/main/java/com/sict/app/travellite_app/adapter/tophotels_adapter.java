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
import com.sict.app.travellite_app.model.hotel;

import java.util.List;

public class tophotels_adapter extends RecyclerView.Adapter<tophotels_adapter.ViewHolder> {
    private Context context;
    private List<hotel> list;

    public tophotels_adapter(Context context, List<hotel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topfavorite, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getImage()).into(holder.img);
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_place.setText(list.get(position).getPlace());
        holder.txt_favorite.setText(String.valueOf(list.get(position).getFavorite()+" yêu thích"));
        holder.txt_cost.setText(String.valueOf("$"+list.get(position).getCost()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_place, txt_favorite, txt_cost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         img = (ImageView)itemView.findViewById(R.id.img_hotel);
         txt_name = (TextView)itemView.findViewById(R.id.txt_name_hotel);
         txt_place = (TextView)itemView.findViewById(R.id.txt_place);
         txt_favorite = (TextView)itemView.findViewById(R.id.txt_favotrite);
         txt_cost = (TextView)itemView.findViewById(R.id.txt_cost);
        }
    }
}
