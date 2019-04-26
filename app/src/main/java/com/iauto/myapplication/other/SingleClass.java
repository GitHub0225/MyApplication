package com.iauto.myapplication.other;
/**
 * created by {Paul}
 * on 19-4-25
 */

import java.util.LinkedList;

public class SingleClass {
    //存放酒店信息
    LinkedList<String> linkedListHotel = null;
    //存放景点信息
    LinkedList<String> linkedListView = null;

    private static volatile SingleClass instance = null;

    public LinkedList<String> getLinkedListHotel() {
        return linkedListHotel;
    }

    public void setLinkedListHotel(LinkedList<String> linkedListHotel) {
        this.linkedListHotel = linkedListHotel;
    }

    public LinkedList<String> getLinkedListView() {
        return linkedListView;
    }

    public void setLinkedListView(LinkedList<String> linkedListView) {
        this.linkedListView = linkedListView;
    }
    public static SingleClass getInstance() {
        if (instance == null) {
            synchronized (SingleClass.class) {
                if (instance == null) {
                    instance = new SingleClass();
                }
            }
        }
        return instance;
    }
}
