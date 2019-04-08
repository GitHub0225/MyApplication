package com.iauto.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.iauto.myapplication.Fragment.Fragment1;
import com.iauto.myapplication.Fragment.Fragment2;
import com.iauto.myapplication.Fragment.Fragment3;
import com.iauto.myapplication.Fragment.Fragment4;



public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    //设定当前Fragment，便于点击搜索时候传递flag
    private int FLAG;
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
        editText = findViewById(R.id.editText);
        fManager = getFragmentManager();

        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        radioButton = (RadioButton) findViewById(R.id.jingdian);
        radioButton.setChecked(true);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮之后隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
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
        editText.getText().clear();
        switch (checkedId){
            case R.id.jingdian:
                if(fg1 == null){
                    fg1 = new Fragment1();
                }
                FLAG = 1;
                fTransaction.replace(R.id.content,fg1);
                break;
            case R.id.jiaotong:
                if(fg2 == null){
                    fg2 = new Fragment2();
                }
                FLAG = 2;
                fTransaction.replace(R.id.content,fg2);
                break;
            case R.id.jiudian:
                if(fg3 == null){
                    fg3 = new Fragment3();
                }
                FLAG = 3;
                fTransaction.replace(R.id.content,fg3);
                break;
            case R.id.zhoubian:
                if(fg4 == null){
                    fg4 = new Fragment4();
                }
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