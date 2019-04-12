package com.iauto.myapplication.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.baidu.mapapi.search.poi.PoiSearch;
import com.iauto.myapplication.MyApplication;
import com.iauto.myapplication.R;
import com.iauto.myapplication.other.HotelInfo;

//交通模块
public class Fragment3 extends Fragment {
    HotelInfo hotelInfo = HotelInfo.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout3, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
            if(hotelInfo.getLinkedList()!=null) {
                System.out.println(hotelInfo.getLinkedList().get(0));
                System.out.println(hotelInfo.getLinkedList().size());
            }

                String[] array = {"北京府右街宾馆", "连升商务酒店", "和家宾馆连锁(北京安贞医院店)", "北京顺义宾馆", "中油宾馆", "锦江之星酒店(北京广渠门店)", "汉庭酒店(北京前门大街店)",
                        "和家宾馆连锁(北京北四环店)", "汉庭酒店(北京西直门新店)", "锦江之星酒店(北京广安门店)", "IU酒店(北京西客站六里桥东地铁站店)", "北京军都大酒店(军都旅游度假村)", "巴比伦时尚酒店",
                        "北京角楼国际青年旅舍(天安门店)", "连升商务酒店", "和家宾馆连锁(北京安贞医院店)", "北京顺义宾馆", "中油宾馆", "锦江之星酒店(北京广渠门店)", "汉庭酒店(北京前门大街店)",
                        "和家宾馆连锁(北京北四环店)", "汉庭酒店(北京西直门新店)", "锦江之星酒店(北京广安门店)", "IU酒店(北京西客站六里桥东地铁站店)", "北京军都大酒店(军都旅游度假村)", "巴比伦时尚酒店",
                        "北京角楼国际青年旅舍(天安门店)", "汉庭酒店(北京亚运村鸟巢店)", "锦江之星酒店(北京马家堡店)", "桔子酒店(北京劲松东店)", "飘HOME连锁酒店(酒仙桥店)", "贯通现代酒店(前门店)"};
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
                listView.setAdapter(arrayAdapter);

        return view;
    }

}