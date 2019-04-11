package com.johj.javasist.dynamical;

/**
 * @author:wenwei
 * @date:2019/04/11
 * @description:
 */
public class RealSubject implements Subject{
    public String sayHi(String s) {
        return s;
    }

    @Override
    public String sayHello(String s) {
        System.out.println("day day up "+s);
        return "sayHello"+s;
    }
}
