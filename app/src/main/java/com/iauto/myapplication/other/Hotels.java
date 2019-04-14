package com.iauto.myapplication.other;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.LinkedList;
import java.util.List;



public class Hotels {
HotelInfo hotelInfo = HotelInfo.getInstance();
    public void getHotel(String city){
        final LinkedList<String> linkedList = new LinkedList<>();
        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> list = poiResult.getAllPoi();
                if(list!=null){
                    for (PoiInfo p: list) {
                        linkedList.add("\n酒店："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n");
                        System.out.println(p.getName());
                    }
                    hotelInfo.setLinkedList(linkedList);
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
