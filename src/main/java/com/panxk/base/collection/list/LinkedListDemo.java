package com.panxk.base.collection.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-23
 **/
public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");

        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        linkedList.forEach(linked ->{
            System.out.println(linked);
        });

        System.out.println(linkedList.get(0));
    }
}
