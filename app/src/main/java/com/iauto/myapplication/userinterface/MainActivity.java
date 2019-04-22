package com.iauto.myapplication.userinterface;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapView;
import com.iauto.myapplication.R;
import com.iauto.myapplication.fragment.*;
import com.iauto.myapplication.other.SurroundingInfo;


public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private LocationClient mLocationClient;
    private MapView mMapView;
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
    private Button selectbutton;
    private FragmentManager fManager;
    private SurroundingInfo surroundingInfo;

    private Handler refreshList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surroundingInfo = new SurroundingInfo();
        mMapView = new MapView(this);
        editText = findViewById(R.id.editText);
        fManager = getFragmentManager();
        mLocationClient = new LocationClient(this);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        //获取第一个单选按钮，并设置其为选中状态
        radioButton = (RadioButton) findViewById(R.id.jingdian);
        radioButton.setChecked(true);

        selectbutton = findViewById(R.id.selectbutton);
        selectbutton.setOnClickListener(new View.OnClickListener() {
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
                        fTransaction.commit();
                        //首次进入系统 即可预先查询fragment3 和 fragment的内容
                        //0  3 代表之进行查询不显示
                        surroundingInfo.getInfo(editText.getText().toString(), refreshList,0,"酒店");
                        surroundingInfo.getInfo(editText.getText().toString(), refreshList,3,"景点");

                        break;
                    case 2:
                        fg2 = null;
                        fg2 = new Fragment2();
                        fTransaction = fManager.beginTransaction();
                        fTransaction.replace(R.id.content,fg2);
                        fTransaction.commit();

                        break;
                    case 3:
                        //在fragment3 界面进行查询，查询结果实时显示
                        surroundingInfo.getInfo(editText.getText().toString(), refreshList,1,"酒店");

                        break;
                    case 4:
                        //在fragment4 界面进行查询，查询结果实时显示
                        surroundingInfo.getInfo(editText.getText().toString(), refreshList,2,"景点");

                        break;
                    default:

                }
            }
        });

        refreshList = new Handler(this.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what == 0){
                    //do nothing
                    //表明是预查询
                }
                if(message.what == 3){
                    //do nothing
                    //表明是预查询
                }
                if(message.what == 1) {
                    //更新fragment3
                    fg3 = null;
                    fg3 = new Fragment3();
                    fTransaction = fManager.beginTransaction();
                    fTransaction.replace(R.id.content, fg3);
                    fTransaction.commit();
                }
                if(message.what == 2){
                    //更新fragment4
                    fg4 = null;
                    fg4 = new Fragment4();
                    fTransaction = fManager.beginTransaction();
                    fTransaction.replace(R.id.content,fg4);
                    fTransaction.commit();
                }
                return false;
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
                if(fg1 == null) {
                    fg1 = new Fragment1();
                }FLAG = 1;
                fTransaction.replace(R.id.content,fg1);
                break;
            case R.id.jiaotong:
                if(fg2 ==null){
                    fg2 = new Fragment2(mLocationClient,new MapView(this));
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