package com.iauto.myapplication.fragment;
/**
 * created by {Paul}
 * on 19-4-25
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.iauto.myapplication.R;
import com.iauto.myapplication.other.SingleClass;
import com.iauto.myapplication.other.SurroundingInfo;
import com.iauto.myapplication.userinterface.DetailedHotelActivity;

import java.util.LinkedList;

//交通模块
public class Fragment3 extends Fragment {
    String[] array = null;
    SingleClass singleClass = SingleClass.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout3, container, false);
        final EditText editText3 = view.findViewById(R.id.editText3);
        Button button3 = view.findViewById(R.id.selectbutton3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurroundingInfo surroundingInfo = new SurroundingInfo();
                surroundingInfo.getInfo(editText3.getText().toString(), "酒店", 3);
            }
        });
        final ListView listView = (ListView) view.findViewById(R.id.list);
        final LinkedList<String> linkedList = singleClass.getLinkedListHotel();
        if (singleClass.getLinkedListHotel() != null) {
            try {
                if (!linkedList.get(0).contains("(")) {
                    array = new String[]{"在此位置尚未找到酒店.请检查"};
                } else {
                    array = new String[]{linkedList.get(0), linkedList.get(1), linkedList.get(2), linkedList.get(3), linkedList.get(4),
                            linkedList.get(5), linkedList.get(6), linkedList.get(7), linkedList.get(8), linkedList.get(9),
                            linkedList.get(10), linkedList.get(11), linkedList.get(12), linkedList.get(13), linkedList.get(14),
                            linkedList.get(15), linkedList.get(16), linkedList.get(17), linkedList.get(18), linkedList.get(19)};
                }
            } catch (Exception e) {
                array = new String[]{"出错!"};
            }

        }
        if (singleClass.getLinkedListHotel() == null) {
            array = new String[]{"北京府右街宾馆", "连升商务酒店", "和家宾馆连锁(北京安贞医院店)", "北京顺义宾馆", "中油宾馆", "锦江之星酒店(北京广渠门店)", "汉庭酒店(北京前门大街店)",
                    "和家宾馆连锁(北京北四环店)", "汉庭酒店(北京西直门新店)", "锦江之星酒店(北京广安门店)", "IU酒店(北京西客站六里桥东地铁站店)", "北京军都大酒店(军都旅游度假村)", "巴比伦时尚酒店",
                    "北京角楼国际青年旅舍(天安门店)", "连升商务酒店", "和家宾馆连锁(北京安贞医院店)", "北京顺义宾馆", "中油宾馆", "锦江之星酒店(北京广渠门店)", "汉庭酒店(北京前门大街店)",
                    "和家宾馆连锁(北京北四环店)", "汉庭酒店(北京西直门新店)", "锦江之星酒店(北京广安门店)", "IU酒店(北京西客站六里桥东地铁站店)", "北京军都大酒店(军都旅游度假村)", "巴比伦时尚酒店",
                    "北京角楼国际青年旅舍(天安门店)", "汉庭酒店(北京亚运村鸟巢店)", "锦江之星酒店(北京马家堡店)", "桔子酒店(北京劲松东店)", "飘HOME连锁酒店(酒仙桥店)", "贯通现代酒店(前门店)"};
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (linkedList != null) {
                    //从字符串中筛选出酒店名称以及地址
                    String item = linkedList.get(i).split("：")[1].split("\n")[0];
                    String addr = linkedList.get(i).split("：")[3].split("\n")[0];
                    Intent intent = new Intent(getContext(), DetailedHotelActivity.class);
                    intent.putExtra("item", item);
                    intent.putExtra("addr", addr);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "不显示默认酒店的信息，请查询后再进行此操作", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }

}