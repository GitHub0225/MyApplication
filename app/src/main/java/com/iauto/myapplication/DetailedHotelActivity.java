package com.iauto.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailedHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_hotel);
        String item = getIntent().getStringExtra("item");
        System.out.println("当前所选item为："+item);
    }
}
