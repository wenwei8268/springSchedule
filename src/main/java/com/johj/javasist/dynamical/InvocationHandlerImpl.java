package com.johj.javasist.dynamical;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author:wenwei
 * @date:2019/04/11
 * @description:
 */
public class InvocationHandlerImpl implements InvocationHandler {
    Object subject;

    public InvocationHandlerImpl(Subject proxy) {
        this.subject = proxy;
    }

    /**
     *
     * @param proxy 代理的对象，
     * @param method 代理的方法
     * @param args 参数细节
     * @return 返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin to invoke");
        System.out.println("method "+method);
        Object returnVal = method.invoke(subject,args);
        System.out.println("done invoke");
        return returnVal;
    }
}
