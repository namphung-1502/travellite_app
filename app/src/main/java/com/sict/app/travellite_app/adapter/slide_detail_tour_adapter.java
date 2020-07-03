package com.sict.app.travellite_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.model.detail_image;

public class slide_detail_tour_adapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] image;

    public slide_detail_tour_adapter(Context context, String[] image) {
        this.context = context;
        this.image = image;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_detail_tour, container,false);
        ImageView img = (ImageView)view.findViewById(R.id.img);
        Glide.with(context).load("http://10.0.2.2:8000/image/"+image[position]).into(img);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return image.length;
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
