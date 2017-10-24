package com.johj;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author:zhou_wenwei
 * mail:
 * date:2017/10/24
 * description:
 */
@RunWith(SpringRunner.class)
public class TestForeach {
    @Test
    public synchronized void testForeach(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for(String s : list){
            if("1".equals(s)){
                list.remove(s);
            }
        }
        for(String s : list){
            System.out.println(s);
        }
        ConcurrentHashMap<Integer,String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        stringConcurrentHashMap.put(1,"1");
        stringConcurrentHashMap.put(2,"2");
        stringConcurrentHashMap.clear();
        stringConcurrentHashMap.put(2,"11112");
        for(Map.Entry<Integer,String> entry: stringConcurrentHashMap.entrySet()){
            entry.getKey();
            System.out.println("key : "+entry.getKey()+" value:"+entry.getValue());

        }
    }
}
