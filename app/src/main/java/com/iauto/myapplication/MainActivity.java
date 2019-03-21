package com.iauto.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.iauto.myapplication.Fragment.Fragment1;
import com.iauto.myapplication.Fragment.Fragment2;
import com.iauto.myapplication.Fragment.Fragment3;
import com.iauto.myapplication.Fragment.Fragment4;


/**
 * Created by Coder-pig on 2015/8/29 0028.
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;

    //Fragment Object

    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private Fragment4 fg4;

    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_channel.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_channel:
                fg1 = null;
                fg1 = new Fragment1();
                fTransaction.add(R.id.ly_content,fg1);
                break;
            case R.id.rb_message:
                fg2 = null;
                fg2 = new Fragment2();
                fTransaction.add(R.id.ly_content,fg2);
                break;
            case R.id.rb_better:
                fg3 = null;
                fg3 = new Fragment3();
                fTransaction.add(R.id.ly_content,fg3);
                break;
            case R.id.rb_setting:
                fg4 = null;
                fg4 = new Fragment4();
                fTransaction.add(R.id.ly_content,fg4);
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

}