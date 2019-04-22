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

import java.util.LinkedList;
import java.util.List;



public class SurroundingInfo {
SingleClass singleClass = SingleClass.getInstance();
    public void getHotel(String city, final Handler handler, final int i, final String keyword){
        final LinkedList<String> linkedList = new LinkedList<>();
        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                System.out.println(Thread.currentThread().getId()+"   9");
                List<PoiInfo> list = poiResult.getAllPoi();
                if(list!=null){
                    if(keyword == "酒店"){

                    for (PoiInfo p: list) {
                        linkedList.add("\n酒店："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n"+"\n如需了解酒店详情或预订房间请点击此处\n");
                        System.out.println(p.getName());
                    }
                    singleClass.setLinkedListhotel(linkedList);
                    }
                    if(keyword == "景点"){

                    for (PoiInfo p: list) {
                        linkedList.add("\n景点："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n"+"\n如需了解景点详情请点击此处\n");
                        System.out.println(p.getName());
                        }
                    singleClass.setLinkedListView(linkedList);
                    }
                }
                Message message = new Message();
                if(i == 0){
                    message.what = 0;
                    handler.sendMessage(message);
                }
                if(i == 1){
                    message.what = 1;
                    handler.sendMessage(message);
                }
                if(i == 2){
                    message.what = 2;
                    handler.sendMessage(message);
                }
                if(i == 3){
                    message.what = 3;
                    handler.sendMessage(message);
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
        System.out.println(Thread.currentThread().getId()+"   9");



        mPoiSearch.destroy();


    }

}
