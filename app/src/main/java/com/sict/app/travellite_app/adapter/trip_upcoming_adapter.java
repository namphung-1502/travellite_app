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
import com.sict.app.travellite_app.model.bill;
import com.sict.app.travellite_app.model.tour;

import org.w3c.dom.Text;

import java.util.List;

public class trip_upcoming_adapter extends RecyclerView.Adapter<trip_upcoming_adapter.ViewHolder> {
    List<bill> list;
    Context context;
    OnCallBack onCallBack;

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    public trip_upcoming_adapter(List<bill> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load("http://10.0.2.2:8000/image/"+list.get(position).getTour().getImage().getImage2()).into(holder.img);
        holder.txt_name.setText(list.get(position).getTour().getName());
        holder.txt_date.setText(list.get(position).getDepartureDay());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_name, txt_date;
        Button btn_destroy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name_tour);
            txt_date = (TextView)itemView.findViewById(R.id.txt_departureday);
            btn_destroy = (Button)itemView.findViewById(R.id.btn_destroy);
            btn_destroy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCallBack.OnItemClick(getPosition());
                }
            });
        }
    }

    public interface OnCallBack {
        void OnItemClick(int i);
    }
}
