package com.panxk.actual.sort;

/**
 * @description:
 * @author: Mr.pxk 选择排序
 * @create: 2020-05-08
 **/
public class SelectionSort {

    public static void main(String[] args) {

        //数组
        int[] arr = new int[]{3, 8, 2, 5, 6, 1};
        int len = arr.length;
        int minIndex , temp;

        for (int i = 0; i < len-1; i++) {
            minIndex = i;

            for (int j = i + 1; j < len; j++ ) {
                //相邻两个元素对比
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //值交换
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        System.out.println(arr.toString());
    }

}
