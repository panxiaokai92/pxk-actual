package com.panxk.base.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-23
 **/
public class NormalTest {


    public static void main(String[] args) {

        /*
         * --》数组元素个数为 3，但转换后的列表个数为 1。
         * Arrays.asList 第一个问题点：不能直接使用 Arrays.asList 来转换基本类型数组。
         * 其原因是：Arrays.asList 方法传入的是一个泛型 T 类型可变参数，最终 int 数组整体作为了一个对象成为了泛型类型 T
         */
        int[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
        System.out.println("list:" + list + ", size:" + list.size() + ",class:" + list.get(0).getClass());

        /*
         * 解决方法：
         * 1、使用 Java8 以上版本可以使用 Arrays.stream 方法来转换
         * 2、把 int 数组声明为包装类型 Integer 数组
         */
        int[] arr1 = { 1, 2, 3 };
        List list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        System.out.println("list:" + list + ", size:" + list1.size() + ",class:" + list1.get(0).getClass());

        Integer[] arr2 = { 1, 2, 3 };
        List list2 = Arrays.asList(arr2);
        System.out.println("list:" + list + ", size:" + list2.size() + ",class:" + list2.get(0).getClass());

        /*
         * Arrays.asList 返回的 List 不支持增删操作。Arrays.asList 返回的 List 并不是我们期望的 java.util.ArrayList，
         * 而是 Arrays 的内部类 ArrayList。
         *
         * 查看源码，我们可以发现 Arrays.asList 返回的 ArrayList 继承了 AbstractList，但是并没有覆写 add 和 remove 方法。
         */
        String[] arr3 = { "1", "2", "3" };
        List list3 = Arrays.asList(arr);
        arr3[1] = "4";
        try {
            list3.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("arr:" + Arrays.toString(arr3) + ", list:" + list3);


        /*
         * --> 对原始数组的修改会影响到我们获得的那个 List
         * ArrayList 其实是直接使用了原始的数组。
         */
        String[] arr5 = { "1", "2", "3" };
        List list4 = Arrays.asList(arr5);
        arr5[1] = "4";
        System.out.println("arr:" + Arrays.toString(arr5) + ", list:" + list4);

        /*
         * 解决：
         * 重新 new 一个 ArrayList 初始化 Arrays.asList 返回的 List 即可：
         */
        String[] arr6 = { "1", "2", "3" };
        List list6 = new ArrayList(Arrays.asList(arr6));
        arr6[1] = "4";
        try {
            list6.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("arr:" + Arrays.toString(arr6) + ", list:" + list6);


    }
}
