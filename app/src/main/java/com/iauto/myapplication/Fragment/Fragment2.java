package com.iauto.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.mapapi.map.MapView;
import com.iauto.myapplication.R;
//酒店模块
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout2,container,false);
        MapView mapView = (MapView) view.findViewById(R.id.bmapView);

        return view;
    }
}
