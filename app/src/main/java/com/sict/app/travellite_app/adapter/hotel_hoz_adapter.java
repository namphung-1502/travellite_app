package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.media.Image;
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

import org.w3c.dom.Text;

import java.util.List;

public class hotel_hoz_adapter extends RecyclerView.Adapter<hotel_hoz_adapter.ViewHolder> {
    private Context context;
    private List<hotel> list;

    public hotel_hoz_adapter(Context context, List<hotel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_hoz,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txt_place.setText(list.get(position).getPlace());
       holder.txt_name.setText(list.get(position).getName());

        Glide.with(context).load(list.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_place;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name_hotel);
            txt_place = (TextView)itemView.findViewById(R.id.txt_place);
        }
    }
}
