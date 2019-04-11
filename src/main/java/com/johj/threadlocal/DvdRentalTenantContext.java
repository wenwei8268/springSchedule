package com.johj.threadlocal;

/**
 * @author:wenwei
 * @date:2019/04/01
 * @description:
 */
public class DvdRentalTenantContext {
    private static ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public void setContext(String tenantId) {
        CONTEXT.set(tenantId);
    }

    public void getContext() {
        CONTEXT.get();
    }

    public void remove() {
        CONTEXT.remove();
    }
}
