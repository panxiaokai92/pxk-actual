package com.panxk.actual.sort;

/**
 * @description: 插入排序
 * @author: Mr.pxk
 * @create: 2020-05-08
 **/
public class InsertionSort {

    public static void main(String[] args) {
        //数组
        int[] arr = new int[]{3, 8, 2, 5, 6, 1};
        int len = arr.length;
        int preIndex , current;

        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];

            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex --;
            }

            arr[preIndex + 1] = current;
        }
    }

}
