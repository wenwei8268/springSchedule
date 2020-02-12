package com.johj.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author:wenwei
 * @date:2020/02/12
 * @description:
 */
public class ReflectDemo3 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> c = Class.forName("com.johj.reflect.Student");

        Constructor<?> constructor = c.getConstructor();
        Object object = constructor.newInstance();
        System.out.println(object);

        //name 私有的变量
        Field field = c.getDeclaredField("name");
        field.setAccessible(true);
        field.set(object, "wenwei");

        //age
        Field fieldDefault = c.getDeclaredField("age");
        fieldDefault.setAccessible(true);
        fieldDefault.set(object, 30);

        //address公有
        Field fieldPublic = c.getDeclaredField("address");
        fieldPublic.set(object, "hangzhou");

        System.out.println(object);
    }
}
