package com.johj.classload;

import com.sun.javafx.binding.Logging;

import java.util.Arrays;

/**
 * @author:wenwei
 * @date:2019/03/28
 * @description:
 */
public class TestClassLoader {

    public static void print(){
        System.out.println("class load of the TestCLass loader:{}"+TestClassLoader.class.getClassLoader());
        System.out.println("class load of the LogClass loader:{}"+ Logging.class.getClassLoader());
        System.out.println("class load of the Arrays loader :{}"+ Arrays.class.getClassLoader());
    }
    public static void main(String[] args){
        print();
       ClassLoader classLoader =  ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        //父加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //父父加载器
       ClassLoader classLoader2 =  classLoader1.getParent();
        System.out.println(classLoader2);

    }
}
