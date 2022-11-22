package com.tu.study.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tuyongjian
 * @date 2022/8/29 15:56
 */

public class OtpUtil {

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("abca"));
        System.out.println(month());
    }

    private static int lengthOfLongestSubstring(String str){
        int size = str.length();
        int right = 0;
        int left = 0;
        Set<Character> windows = new HashSet<>();
        while (right<size){
            char c = str.charAt(right);
            right++;
            while (windows.contains(c)){
                windows.remove(str.charAt(left));
                left++;
            }
            windows.add(c);
        }
        return Math.max(0,windows.size());
    }

    private static int month(){
        int current = 65919;
        int addmonth = 3500;
        int everyMonth = 7350;
        int monthSum = 0;
        while (current > everyMonth){
            current = current-everyMonth+addmonth;
            ++monthSum;
        }
        System.out.println(current);
        return monthSum;
    }
}
