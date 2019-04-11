package com.johj.threadlocal;


import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author:wenwei
 * @date:2019/04/01
 * @description:
 */
public class TenantFilter  {
    @Autowired
    private TenantStore tenantStore;
//    @Override
//    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
//
//    }
//
//    @Override
//    public String description() {
//        return null;
//    }
}
