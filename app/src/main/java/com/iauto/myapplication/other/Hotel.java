package com.iauto.myapplication.other;

import android.app.Application;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.iauto.myapplication.MyApplication;

import java.util.LinkedList;
import java.util.List;



public class Hotel {

    public LinkedList<String> list1 = new LinkedList<>();

    public void getHotel(String city){
        final Application application = new MyApplication();

        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> list = poiResult.getAllPoi();
                if(list!=null){
                    for (PoiInfo p: list) {
                        list1.add(p.getName());

                        System.out.println(p.getName());
                    }
                    ((MyApplication) application).setLinkedList(list1);
                }


            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);

        mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city(city) //必填
                .keyword("酒店")
                .pageCapacity(20)//一夜显示多少
                .pageNum(0));//显示第几页
        mPoiSearch.destroy();


    }

}
