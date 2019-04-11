package com.johj.javasist.dynamical;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author:wenwei
 * @date:2019/04/11
 * @description: the example for jdk proxy
 */
public class DynamicProxyDemonstration {


    public static void main(String[] args) {
        Subject realSubject = new RealSubject();


        InvocationHandler invocationHandler = new InvocationHandlerImpl(realSubject);

        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();

        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        String s = subject.sayHi("hello world");
        subject.sayHello("fuck the world");
        System.out.println(s);
        createProxyClassFile();
    }


    private static void createProxyClassFile(){
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name,new Class[]{Subject.class});
        FileOutputStream out =null;
        try {
            out = new FileOutputStream(name+".class");
            System.out.println((new File("hello")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
