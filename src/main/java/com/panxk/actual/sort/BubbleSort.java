package com.panxk.actual.sort;

/**
 * @description: 冒泡排序
 * @author: Mr.pxk
 * @create: 2020-05-08
 **/
public class BubbleSort {

    public static void main(String[] args) {

        //数组
        int[] arr = new int[]{3, 8, 2, 5, 6, 1};
        int len = arr.length;
        int temp;

        for (int i = 0; i < len -1 ; i++) {
            for (int j = 0; j < len - i - 1; j++ ) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
