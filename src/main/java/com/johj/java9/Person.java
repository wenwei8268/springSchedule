package com.johj.java9;

/**
 * author:zhou_wenwei
 * mail:
 * date:2017/10/23
 * description: com.johj.java9
 */

public interface Person {
    default void eat(){
        System.out.println("all the person should eat somethings");
    }
    static  void likeEat(){
        System.out.println("most of us like eat");
    }
}
