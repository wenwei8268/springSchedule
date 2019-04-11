package com.johj.java8;
/**
 * Created by wenweizww on 2017/3/30.
 */


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2017/3/30
 * description:
 */
public class TestJava8 {

    public static void main(String[] args) {
//        System.out.println();
        //sortMap();
        //listStream();
//        stringJoin();
        ints();
    }



    public static void sortMap() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        Map<String, Integer> result = new LinkedHashMap<>();
        Map<String, Integer> result_value = new LinkedHashMap<>();

        //sort by key, a,b,c..., and put it into the "result" map
//        unsortMap.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByKey())
//                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        System.out.print(unsortMap.entrySet().stream()
                .count());
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result_value.put(x.getKey(), x.getValue()));


        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result_value);
    }

    public static void listStream() {
        String[] list = {"a","b","c"};
        Stream<String> stream = Arrays.stream(list);
        stream.forEach(x-> System.out.println(x));


        //System.out.println(count);

    }
    public static void stringJoin(){
        StringJoiner sj = new StringJoiner("/", "prefix-", "-suffix");
        sj.add("2016");
        sj.add("02");
        sj.add("26");
        String result = sj.toString();
        System.out.println(result);
        List<String> list = Arrays.asList("java", "python", "nodejs", "ruby");

        //java | python | nodejs | ruby
        String result_ = list.stream().map(x -> x).collect(Collectors.joining(" | "));
        System.out.println(result_);

    }

    public static void ints(){
        int x = 2 << 29;
        System.out.println(x);
        long lx = 2<<29;
        System.out.println(lx);
    }


}
