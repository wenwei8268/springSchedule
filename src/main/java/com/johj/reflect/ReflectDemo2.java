package com.johj.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author:wenwei
 * @date:2020/02/12
 * @description:
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //获取class对象
        Class<?> object = Class.forName("com.johj.reflect.Student");

        //返回一个所有的Constructor对象的数组
        Constructor[] constructors = object.getDeclaredConstructors();
        for (Constructor con : constructors) {
            System.out.println(con);
        }
        System.out.println("-----------");

        //返回公有的构造方法
        Constructor[] constructorDefault = object.getConstructors();
        for (Constructor constructor : constructorDefault) {
            System.out.println(constructor);
        }

        System.out.println("-----------");

        Constructor constructor = object.getConstructor();
        Object object1 = constructor.newInstance();
        System.out.println(object1);

        //通过反射 构造多参数的对象 公有方法
        System.out.println("------多参数的组合--");
        Constructor constructor1 = object.getConstructor(String.class, int.class, String.class);
        Object object3 = constructor1.newInstance("wenwei", 12, "wenwe");
        System.out.println(object3);

        //通过反射，构造单个参数 私有构造方法
        Constructor constructor2 = object.getDeclaredConstructor(String.class);
        //访问私有的构造方法，
        constructor2.setAccessible(true);
        Object object4 = constructor2.newInstance("wenwei");
        System.out.println(object4);

        System.out.println("-----");


        //访问成员变量  公共
        Field[] fields = object.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        //访问成员变量  私有+公有
        Field[] fields1 = object.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field);
        }

        Field field = object.getDeclaredField("address");
        System.out.println(field);

        field.set(object1,"addressField");//给object1的成员变量field赋值为addressField
        System.out.println(object1);

    }
}
