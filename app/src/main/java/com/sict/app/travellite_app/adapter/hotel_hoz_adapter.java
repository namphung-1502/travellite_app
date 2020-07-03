package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
    OnCallBack onCallBack;
    OnDetailClick detailClick;

    public void setDetailClick(OnDetailClick detailClick) {
        this.detailClick = detailClick;
    }

    public void setListener(OnCallBack listener) {
        this.onCallBack = listener;
    }
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       holder.txt_place.setText(list.get(position).getPlace());
       holder.txt_name.setText(list.get(position).getName());

        Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getImage()).into(holder.img);
        holder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBack.OnItemClick(position);
            }
        });
        holder.txt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailClick.DetailClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_place;
        ImageButton btn_favorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txt_name = (TextView)itemView.findViewById(R.id.txt_name_hotel);
            txt_place = (TextView)itemView.findViewById(R.id.txt_place);
            btn_favorite = (ImageButton)itemView.findViewById(R.id.btn_favotrite);

        }
    }
    public interface OnCallBack {
        void OnItemClick(int i);
    }

    public interface OnDetailClick{
        void DetailClick(int i);
    }
}
