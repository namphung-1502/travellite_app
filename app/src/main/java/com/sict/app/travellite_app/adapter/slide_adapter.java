package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.model.place;

import java.util.List;

public class slide_adapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<place> list;

    public slide_adapter(Context context, List<place> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public slide_adapter(){super();}

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_show,container,false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageResource(list.get(position).getImage());
        TextView txt_place = (TextView)view.findViewById(R.id.txt_place);
        txt_place.setText(list.get(position).getName());
        TextView txt_district = (TextView)view.findViewById(R.id.txt_district);
        txt_district.setText(list.get(position).getLocation());
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
