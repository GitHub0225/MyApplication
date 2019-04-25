package com.iauto.myapplication.other;
/**
 * created by {Paul}
 * on 19-4-25
 */
import java.util.LinkedList;
public class SingleClass {
    //存放酒店信息
    LinkedList<String> linkedListhotel = null;
    //存放景点信息
    LinkedList<String> linkedListView = null;

    private SingleClass() {
    }

    private static volatile SingleClass instance = null;

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
