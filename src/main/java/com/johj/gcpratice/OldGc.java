package com.johj.gcpratice;
/**
 * Created by wenweizww on 2018/5/16.
 */


/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/5/16
 * description:
 */
public class OldGc {
    //-XX:+UseSerialGC
    //        -Xms200M
    //                -Xmx200M
    //                -Xmn32m
    // ..               -XX:SurvxivorRatio=8
    //            -XX:+PrintGCDetails

    public static void main(String[] args)
    {
        //模拟fullgc场景
        //老年代空间不足

        //按照上面的参数推算:老年代大小: 200 -32m = 168M


        while(true) {
            byte[] MAXOBJ = new byte[1024 * 1024 * 100]; // 100M

            byte[] MAXOBJ2 = new byte[1024 * 1024 *60]; // 60M
            MAXOBJ = null;

            byte[] MAXOBJ3 = new byte[1024 * 1024 * 100]; // 60M
        }
    }
}
