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
        int current = 45803;
        int addmonth = 2660;

        int monthSum = 0;
        int gjjsum = 971014;
        int ggjsumjs = 3623;

        int yhsum = 136888;
        int yhsumjs  = 388;
        int gjj = 6131;
        int gjjjs = 9;
        int yh = 1023;
        int yhjs = 2;
        while (current > (gjj+yh)){
            current = current-((gjj+yh))+addmonth;
            gjj = gjj - gjjjs;
            yh = yh - yhjs;
            ++monthSum;
            gjjsum = gjjsum-ggjsumjs;
            yhsum = yhsum - yhsumjs;
        }
        System.out.println(current);
        System.out.println(gjjsum);
        System.out.println(yhsum);
        return monthSum;
    }
}
