package com.johj.javasist;

/**
 * @author:wenwei
 * @date:2019/04/10
 * @description:
 */
public class TestDCclass {
    public static void main(String[] args) {
        TestDCclass test = new TestDCclass();
        test.testIsAssignedFrom1();
        test.testIsAssignedFrom2();
        test.testIsAssignedFrom3();
        test.testInstanceOf1();
        test.testInstanceOf2();
//        test.testDc(TestDCclass.class);
    }

    public void testIsAssignedFrom1() {
        System.out.println(String.class.isAssignableFrom(Object.class));
    }

    public void testIsAssignedFrom2() {
        System.out.println(Object.class.isAssignableFrom(Object.class));
    }

    public void testIsAssignedFrom3() {
        System.out.println(Object.class.isAssignableFrom(String.class));
    }

    public void testInstanceOf1() {
        String ss = "";
        System.out.println(ss instanceof Object);
    }

    public void testInstanceOf2() {
        Object o = new Object();
        System.out.println(o instanceof Object);
    }

    public void testDc(Class<?> cl) {
        System.out.println(TestDCclass.DC.class.isAssignableFrom(cl));
    }

    public static interface DC {

    }
}
