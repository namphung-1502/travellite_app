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

public class foreign_fragment extends Fragment {
    private RecyclerView rcv_foreign;
    private List<area> list;
    private area_adapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.foreign, container,false);
        rcv_foreign = (RecyclerView)view.findViewById(R.id.rcv_foreign);
        list = new ArrayList<>();
        list.add(new area("Las Vegas, Mỹ",R.drawable.my,27 ));
        list.add(new area("Rome, Italia",R.drawable.italia,32 ));
        list.add(new area("Lyon, Pháp",R.drawable.lyon,50 ));
        list.add(new area("Berlin, Đức",R.drawable.berlin,45 ));
        list.add(new area("Bruges, Bỉ",R.drawable.bruges,68 ));
        list.add(new area("Whitby, Anh",R.drawable.whitby,31 ));
        adapter = new area_adapter(getContext(),list);
        rcv_foreign.setAdapter(adapter);
        rcv_foreign.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcv_foreign.setHasFixedSize(true);
        return view;
    }

}
