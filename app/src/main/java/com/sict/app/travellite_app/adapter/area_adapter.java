package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.model.area;

import java.util.List;

public class area_adapter extends RecyclerView.Adapter<area_adapter.ViewHolder> {
    private Context context;
    private List<area> list;

    public area_adapter(Context context, List<area> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_favorite, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txt_name.setText(list.get(position).getName());
         holder.txt_count_hotel.setText(String.valueOf(list.get(position).getCount_hotel()+" khách sạn"));
         holder.img.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_count_hotel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_place);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name_place);
            txt_count_hotel = (TextView)itemView.findViewById(R.id.txt_count_hotel);
        }
    }
}
