package com.iauto.myapplication.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.iauto.myapplication.R;
import com.iauto.myapplication.other.SingleClass;
import com.iauto.myapplication.other.SurroundingInfo;

import java.util.LinkedList;

//详细景点
public class Fragment4 extends Fragment {
    String[] array = null;
    SingleClass singleClass = SingleClass.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout4, container, false);
        final EditText editText4 = view.findViewById(R.id.editText4);
        Button button4 = view.findViewById(R.id.selectbutton4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurroundingInfo surroundingInfo = new SurroundingInfo();
                surroundingInfo.getInfo(editText4.getText().toString(),"景点",4);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.list);
        if(singleClass.getLinkedListView()!=null) {
            try {
                LinkedList<String> linkedList = singleClass.getLinkedListView();
                array = new String[]{linkedList.get(0), linkedList.get(1), linkedList.get(2), linkedList.get(3), linkedList.get(4),
                        linkedList.get(5), linkedList.get(6), linkedList.get(7), linkedList.get(8), linkedList.get(9),
                        linkedList.get(10), linkedList.get(11), linkedList.get(12), linkedList.get(13), linkedList.get(14),
                        linkedList.get(15), linkedList.get(16), linkedList.get(17), linkedList.get(18), linkedList.get(19)};
            }catch (Exception e) {
                array = new String[]{"\n你\n","\n他喵\n","\n的确定\n","\n这个地方\n","\n能找到酒店\n","\n恩？\n","\n嗯嗯？\n","\n嗯嗯嗯？\n","\n换个地方查找吧，山炮！ \n"};
            }

        }else {
            array = new String[] {"北京府右街宾馆", "连升商务酒店", "和家宾馆连锁(北京安贞医院店)", "北京顺义宾馆", "中油宾馆", "锦江之星酒店(北京广渠门店)", "汉庭酒店(北京前门大街店)",
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
                System.out.println("单击");
            }
        });

        return view;
    }

}