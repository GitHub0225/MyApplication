package com.iauto.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private int FLAG;
    //Fragment Object
    private FragmentTransaction fTransaction;
    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private Fragment4 fg4;
    private EditText editText;
    private Button button;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();

        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        radioButton = (RadioButton) findViewById(R.id.jiaotong);
        radioButton.setChecked(true);
        editText = findViewById(R.id.editText);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(FLAG);
                switch (FLAG){
                    case 1:
                        fg1 = null;
                        fg1 = new Fragment1(editText.getText().toString());
                        fTransaction = fManager.beginTransaction();
                        fTransaction.replace(R.id.content,fg1);
                        break;
                    case 2:
                        fg2 = null;
                        fg2 = new Fragment2();
                        fTransaction = fManager.beginTransaction();
                        fTransaction.replace(R.id.content,fg2);
                    default:
                        break;
                }
                fTransaction.commit();
            }
        });
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        hideAllFragment(fTransaction);
        fTransaction = fManager.beginTransaction();
        switch (checkedId){
            case R.id.jingdian:
                fg1 = null;
                fg1 = new Fragment1();
                FLAG = 1;
                fTransaction.replace(R.id.content,fg1);
                break;
            case R.id.jiaotong:
                fg2 = null;
                fg2 = new Fragment2();
                FLAG = 2;
                fTransaction.replace(R.id.content,fg2);
                break;
            case R.id.jiudian:
                fg3 = null;
                fg3 = new Fragment3();
                FLAG = 3;
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                fTransaction.replace(R.id.relay,fg3);//在RelativeLayout中放置
                break;
            case R.id.zhoubian:
                fg4 = null;
                fg4 = new Fragment4();
                FLAG = 4;
                fTransaction.replace(R.id.content,fg4);
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