package com.iauto.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.iauto.myapplication.R;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1,container,false);
        TextView textView1 = view.findViewById(R.id.dizhi);
        textView1.setText("MySql数据库内容");

        return view;
    }
}
