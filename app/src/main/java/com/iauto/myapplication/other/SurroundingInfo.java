package com.iauto.myapplication.other;
/**
 * created by {Paul}
 * on 19-4-25
 */
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

/**
 * 负责查询指定地点的周边酒店 景点情况,保存在单例类的链表中
 */
public class SurroundingInfo {
SingleClass singleClass = SingleClass.getInstance();
    public void getInfo(String city, final String keyword, final int flag){
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
                        //先进行置空操作保证单利类中链表数据为最新
                        singleClass.setLinkedListHotel(null);
                        singleClass.setLinkedListHotel(linkedListHotel);
                    }
                    if(keyword == "景点"){

                    for (PoiInfo p: list) {
                        linkedListView.add("\n景点："+p.getName()+"\n \n联系方式："+p.getPhoneNum()+"\n \n地址："+p.getAddress()+"\n"+"\n如需了解景点详情请点击此处\n");
                        System.out.println(p.getName());
                        }
                        //先进行置空操作保证单例类中链表数据为最新
                        singleClass.setLinkedListView(null);
                        singleClass.setLinkedListView(linkedListView);
                    }
                    if(flag == 0){
                        MainActivity.handler.sendEmptyMessage(0);
                    }
                    if(flag == 3){
                        //send Message 更新Fragment3
                        MainActivity.handler.sendEmptyMessage(3);
                    }
                    if(flag == 4){
                        //send Message 更新Fragment4
                        MainActivity.handler.sendEmptyMessage(4);
                    }
                }else {
                    //百度地图返回的POI为空,为空也没有什么办法了,但经测试尚未发现为空的情况.
                    //保证网络连接状况良好,GPS打开即可.
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
                .pageCapacity(20)//一页显示多少
                .pageNum(0));//显示第几页

        mPoiSearch.destroy();


    }

}
