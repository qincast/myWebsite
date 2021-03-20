package com.example.logindemo.controller;

import java.util.HashMap;

public class MapTest {
    public static void main(String args[]){
        int nums[]={1,2,3,4,5,6,7,8,1};
        int value=0;
        int max=0;
        HashMap map=new HashMap<Integer, Integer>();
        Integer i=0;
        for(int num:nums){
            i= (Integer) map.get(num);
            if(i==null){
                map.put(num,1);
            }
            else{
                map.put(num,i+1);
            }
            for(Object key:map.keySet()){
                if((Integer)map.get(key)>max){
                    max=(Integer) map.get(key);
                    value=(Integer)key;
                }
            }
            int left=0;
            int right=nums.length-1;
            while(nums[left]!=value){
                left++;
            }
            while(nums[right]!=value){
                right--;
            }
            int[] res=new int[right-left+1];
            for(int j=0;j<right-left+1;j++){
                res[j]=nums[right+j];
            }
            //return res;
        }
    }
}
