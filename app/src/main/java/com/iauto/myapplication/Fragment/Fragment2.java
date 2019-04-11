package com.iauto.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import com.baidu.mapapi.search.poi.PoiSearch;


public class Fragment2 extends Fragment {
    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private LocationClient mLocationClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);


        //实例化UiSettings类对象
        //通过设置enable为true或false 选 择是否显示指南针
        //定位初始化
        mMapView.showZoomControls(true);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(0);
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();

        return mMapView;
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.zoom(18).target(ll).targetScreen(new Point(540,960));
            mBaiduMap.setMyLocationData(locData);


            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            //显示定位
            System.out.println(location.getLatitude()+"---"+location.getLongitude());
        }
    }







    @SuppressLint("ValidFragment")
    public Fragment2(LocationClient mLocationClient,MapView mMapView) {
        this.mLocationClient = mLocationClient;
        this.mMapView = mMapView;
    }

    public Fragment2() {
    }
    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

}
