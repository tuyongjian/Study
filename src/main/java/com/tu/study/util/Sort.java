package com.tu.study.util;

/**
 * @author tuyongjian
 * @date 2023/1/30 9:44
 */

public class Sort {

    public static void main(String[] args) {
        int[] intArray = new int[]{1,2,3,4,5,2,4};
        bubbleSortOpt(intArray);
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

    }

    //冒泡排序
    private static void bubbleSortOpt(int[] arr){

        if(arr==null){
            throw new NullPointerException();
        }
        if(arr.length==1){
            return;
        }

        int temp = 0;

        for (int i = 0; i <arr.length ; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

    }
}
