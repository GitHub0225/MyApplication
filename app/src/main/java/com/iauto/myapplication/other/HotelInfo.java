package com.iauto.myapplication.other;

import java.util.LinkedList;


public class HotelInfo {
    //存放酒店信息
    LinkedList<String> linkedListhotel = null;
    //存放景点信息
    LinkedList<String> linkedListView = null;

    private HotelInfo() {
    }

    private static volatile HotelInfo instance = null;

    public static HotelInfo getInstance() {
        if (instance == null) {
            synchronized (HotelInfo.class) {
                if (instance == null) {
                    instance = new HotelInfo();
                }
            }
        }
        return instance;
    }

    public LinkedList<String> getLinkedListhotel() {
        return linkedListhotel;
    }

    public void setLinkedListhotel(LinkedList<String> linkedListhotel) {
        this.linkedListhotel = linkedListhotel;
    }

    public LinkedList<String> getLinkedListView() {
        return linkedListView;
    }

    public void setLinkedListView(LinkedList<String> linkedListView) {
        this.linkedListView = linkedListView;
    }
}
