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
import com.sict.app.travellite_app.model.tour;

import java.util.List;

public class toptourfavorite_adapter extends RecyclerView.Adapter<toptourfavorite_adapter.ViewHolder> {
     private List<tour> list;
     private Context context;

    public toptourfavorite_adapter(List<tour> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toptourfavorite, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txt_name.setText(list.get(position).getName());
       Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getImage().getImage1()).into(holder.img);
       holder.txt_favorite.setText(String.valueOf(list.get(position).getFavorite()+" yêu thích"));
       holder.txt_money.setText(String.valueOf("$"+list.get(position).getMoney().getAdult()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_money, txt_favorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name);
            txt_favorite = (TextView)itemView.findViewById(R.id.txt_favotrite);
            txt_money = (TextView)itemView.findViewById(R.id.txt_money);
        }
    }
}
