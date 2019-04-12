package com.iauto.myapplication.other;

import java.util.LinkedList;


public class HotelInfo {
    LinkedList<String> linkedList = null;

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

    public LinkedList<String> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<String> linkedList) {
        this.linkedList = linkedList;
    }
}
