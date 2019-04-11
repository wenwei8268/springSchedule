package com.johj.java9;


import com.google.gson.annotations.Since;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 http://www.infoq.com/cn/news/2016/09/JavaOne-2016-Keynote-JShell
 */

public class Jshell {

    /**
     *
     * @param args
     * @since 1.9
     */
    public static void main(String[] args ){
        new Wenwei().eat();
    }

    /**
     * These useful methods are used to create a new Non-Empty Immutable Map with one element to 10 elements.
     */
//    private void immutableHashMap(){
//        //Immutable map  @since 1.9
//        Map<Integer,String> immutableMap = Map.of(1,"test1",2,"tests");
//        Stream.of(immutableMap).iterator().forEachRemaining(System.out::print);
//        // @since 1.8
//        Map<Integer,String> hashMap = new HashMap<>();
//        hashMap.put(1,"one");
//        hashMap.put(2,"two");
//        hashMap.put(3,"three");
//        Map immutableHashMap = Collections.unmodifiableMap(hashMap);
//        Stream.of(immutableHashMap).iterator().forEachRemaining(System.out::println);
//        System.out.println();
//    }
}
