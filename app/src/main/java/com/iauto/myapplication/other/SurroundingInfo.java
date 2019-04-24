package com.iauto.myapplication.other;

import android.os.Handler;
import android.os.Message;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.iauto.myapplication.userinterface.MainActivity;

import java.util.LinkedList;
import java.util.List;



public class SurroundingInfo {
SingleClass singleClass = SingleClass.getInstance();
    public void getInfo(String city, final String keyword, final int i){
        final LinkedList<String> linkedListHotel = new LinkedList<>();
        final LinkedList<String> linkedListView = new LinkedList<>();

        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> list = poiResult.getAllPoi();
                if(list != null){
                    if(keyword == "酒店"){

                    for (PoiInfo p: list) {
                        linkedListHotel.add("\n酒店："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n"+"\n如需了解酒店详情或预订房间请点击此处\n");
                        System.out.println(p.getName());
                    }
                        singleClass.setLinkedListhotel(null);
                        singleClass.setLinkedListhotel(linkedListHotel);
                    }
                    if(keyword == "景点"){

                    for (PoiInfo p: list) {
                        linkedListView.add("\n景点："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n"+"\n如需了解景点详情请点击此处\n");
                        System.out.println(p.getName());
                        }
                        singleClass.setLinkedListView(null);
                        singleClass.setLinkedListView(linkedListView);
                    }
                    if(i == 0){
                        MainActivity.handler.sendEmptyMessage(0);
                    }
                    if(i == 3){
                        MainActivity.handler.sendEmptyMessage(3);
                    }
                    if(i == 4){
                        MainActivity.handler.sendEmptyMessage(4);
                    }
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
                .keyword(keyword)
                .pageCapacity(20)//一夜显示多少
                .pageNum(0));//显示第几页

        mPoiSearch.destroy();


    }

}
