package com.johj.redis;
/**
 * Created by wenweizww on 2017/3/13.
 * <p>
 * Copyright (c) 2016-2020 com.johj.redis  Inc.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of
 *   Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with com.johj.redis.
 **/


import redis.clients.jedis.Jedis;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2017/3/13
 * description: 
 */
public class JedisServer {
    public Jedis jedis;
    public JedisServer(String url){ jedis = new Jedis(url);
    }

    public void setData(String key, String data) {
        jedis.set(key, data);

    }
    public String getData(String key) {
        return jedis.get(key);
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }


}
