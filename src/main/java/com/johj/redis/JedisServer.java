package com.johj.redis;



import redis.clients.jedis.Jedis;


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
