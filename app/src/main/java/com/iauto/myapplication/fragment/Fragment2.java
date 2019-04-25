package com.iauto.myapplication.fragment;
/**
 * created by {Paul}
 * on 19-4-25
 */
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
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
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.iauto.myapplication.R;
import com.iauto.myapplication.Utils.overlayutil.DrivingRouteOverlay;


public class Fragment2 extends Fragment {
    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private RoutePlanSearch mSearch;
    private LocationClient mLocationClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("=========================================================================");
        View view = inflater.inflate(R.layout.layout2, container, false);
        mMapView = view.findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mSearch = RoutePlanSearch.newInstance();

        //实例化UiSettings类对象
        //通过设置enable为true或false 选择是否显示指南针
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

        mLocationClient.registerLocationListener(new MyLocationListener());
        //开启地图定位图层
        mLocationClient.start();
        //路线规划模块
        mSearch.setOnGetRoutePlanResultListener(listener);
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("上海", "新华联大厦");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("上海", "罗南新村");
        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));

        return view;
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

            builder.zoom(18).target(ll).targetScreen(new Point(540, 960));
            mBaiduMap.setMyLocationData(locData);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            //显示定位
            System.out.println(location.getLatitude() + "---" + location.getLongitude());
        }
    }

    @SuppressLint("ValidFragment")
    public Fragment2(LocationClient mLocationClient) {
        this.mLocationClient = mLocationClient;
    }

    public Fragment2() {
    }

    @Override
    public void onResume() {

        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);

        super.onDestroy();
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            //创建DrivingRouteOverlay实例
            DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);

            if (drivingRouteResult.getRouteLines().size() > 0) {
                //获取路径规划数据,(以返回的第一条路线为例）
                //为DrivingRouteOverlay实例设置数据
                overlay.setData(drivingRouteResult.getRouteLines().get(0));

                //在地图上绘制DrivingRouteOverlay
                overlay.addToMap();
            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

}