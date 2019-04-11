package com.johj.threadlocal;

import lombok.Data;

/**
 * @author:wenwei
 * @date:2019/04/01
 * @description:
 */
@Data
public class TenantStore {
    private String tenantId;


    public void clear(){
        tenantId = null;
    }
}
