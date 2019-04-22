package com.iauto.myapplication.userinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.iauto.myapplication.R;

public class DetailedHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_hotel);
        String item = getIntent().getStringExtra("item");
        String addr = getIntent().getStringExtra("addr");
        System.out.println("当前所选item为："+item);
        System.out.println("当前所选desr为："+addr);
        Toast.makeText(getApplicationContext(), item +"\n"+addr,Toast.LENGTH_SHORT).show();
    }
}
