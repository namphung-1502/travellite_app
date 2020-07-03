package com.sict.app.travellite_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.app.travellite_app.R;
import com.sict.app.travellite_app.adapter.area_adapter;
import com.sict.app.travellite_app.model.area;

import java.util.ArrayList;
import java.util.List;

public class domestic_fragment extends Fragment {
    private RecyclerView rcv_domestic;
    private List<area> list;
    private area_adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.domestic_layout, container,false);
        rcv_domestic = (RecyclerView)view.findViewById(R.id.rcv_domestic);
        list = new ArrayList<>();
        list.add(new area("Phú Quốc",R.drawable.phuquoc,34 ));
        list.add(new area("Nha Trang",R.drawable.nhatrang,52 ));
        list.add(new area("Quy Nhơn",R.drawable.quynhon,27 ));
        list.add(new area("Đà Lạt",R.drawable.dalat,50 ));
        list.add(new area("Đà Nẵng",R.drawable.danang,22 ));
        list.add(new area("Sa Pa",R.drawable.sapa,31 ));
        adapter = new area_adapter(getContext(),list);
        rcv_domestic.setAdapter(adapter);
        rcv_domestic.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcv_domestic.setHasFixedSize(true);
        return view;
    }
}
