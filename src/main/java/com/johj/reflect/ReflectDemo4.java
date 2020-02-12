package com.johj.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author:wenwei
 * @date:2020/02/12
 * @description:
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class cl = Class.forName("com.johj.reflect.Student");

        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
//            System.out.println(method);
        }

        // 注意参数的设置，需要设置参数类型
        Method method = cl.getMethod("method",String.class);
        method.setAccessible(true);
        Constructor constructor =  cl.getConstructor();
        Object object = constructor.newInstance();

       Object object1 =  method.invoke(object,"wenwei");
        System.out.println(object1);



    }
}
