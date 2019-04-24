package com.iauto.myapplication.userinterface;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
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
    private FragmentTransaction fTransaction;
    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private Fragment4 fg4;
    private FragmentManager fManager;
    public static Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initGPS();
        mMapView = new MapView(this);
        fManager = getFragmentManager();
        mLocationClient = new LocationClient(this);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        //获取第一个单选按钮，并设置其为选中状态
        radioButton = (RadioButton) findViewById(R.id.jingdian);
        radioButton.setChecked(true);

        handler = new Handler(this.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                //隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                if(message.what == 0){
                }
                if(message.what == 3) {
                    //更新fragment3
                    fg3 = null;
                    fg3 = new Fragment3();
                    fTransaction = fManager.beginTransaction();
                    fTransaction.replace(R.id.relay, fg3);
                    fTransaction.commit();
                }
                if(message.what == 4){
                    //更新fragment4
                    fg4 = null;
                    fg4 = new Fragment4();
                    fTransaction = fManager.beginTransaction();
                    fTransaction.replace(R.id.relay,fg4);
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
//        editText.getText().clear();
        switch (checkedId){
            case R.id.jingdian:
                if(fg1 == null) {
                    fg1 = new Fragment1();
                }
                if(fg2 != null){
                    fg2.onResume();
                }
                fTransaction.replace(R.id.relay,fg1);
                break;
            case R.id.jiaotong:
                if(fg2 == null){
                    fg2 = new Fragment2(mLocationClient);
                }
                fTransaction.replace(R.id.relay,fg2);
            break;
            case R.id.jiudian:
                if(fg3 == null){
                    fg3 = new Fragment3();
                }
                fTransaction.replace(R.id.relay,fg3);
                break;
            case R.id.zhoubian:
                if(fg4 == null){
                    fg4 = new Fragment4();
                }
                fTransaction.replace(R.id.relay,fg4);
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

//    private void initGPS(){
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //判断GPS是否开启，没有开启，则开启
//        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            openGPSDialog();
//        }
//    }
//    private void openGPSDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("请打开GPS连接")
//                .setIcon(R.mipmap.ic_launcher_round)
//                .setMessage("为了提高定位的准确度，更好的为您服务，请打开GPS")
//                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //跳转到手机打开GPS页面
//                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        //设置完成后返回原来的界面
//                        startActivityForResult(intent,0);
//                    }
//                })
//                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                }).show();
//    }
//


}