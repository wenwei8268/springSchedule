package com.johj.schedule;

import com.johj.mail.JavaMail;
import com.johj.redis.JedisServer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wenweizww on 2017/3/10.
 */
@Component
public class Schedule {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        //JavaMail javaMail = new JavaMail();
        JedisServer jedisServer = new JedisServer("localhost");
        String redistr = "";
        if (jedisServer.getData("wenwei") == null || "".equals(jedisServer.getData("wenwei"))) {
            redistr = "I" ;
        }else{
            redistr = jedisServer.getData("wenwei");
        }
        jedisServer.setData("wenwei", redistr + " love u");
        System.out.println(String.format("+++++++++" + jedisServer.getData("wenwei") + "第%s次记录；当前时间为%s", count0++, simpleDateFormat.format(new Date())));
    }
    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeAfterSleep() throws InterruptedException {
        JedisServer jedisServer = new JedisServer("localhost");

        System.out.println(String.format("===第%s次执行，当前时间为：%s", count1++, simpleDateFormat.format(new Date())));
    }

    @Scheduled(cron = "5 * * * * *")
    public void reportCurrentTimeCron() throws InterruptedException {
        System.out.println(String.format("+++++++第%s次执行，当前时间为：%s", count2++, simpleDateFormat.format(new Date())));
    }
}
