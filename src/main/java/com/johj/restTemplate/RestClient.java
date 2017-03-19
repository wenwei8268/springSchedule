package com.johj.restTemplate;
/**
 * Created by wenweizww on 2017/3/16.
 * <p>
 * Copyright (c) 2016-2020 com.johj.restTemplate  Inc.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of
 *  Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with com.johj.restTemplate.
 **/


import com.johj.common.utils.JsonUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@com
 * date:2017/3/16
 * description: 
 */
public class RestClient {
    // TODO: 2017/2/27 获取到lims-ext中的用户token;增加https请求；改变用户名称
    private String getAccessToken() {
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("system.properties");
        Project proj = null;
        Properties pro = new Properties();
        ResponseEntity responseEntity = null;
        Map map = null;
        try {
            pro.load(is);
            String limsExtUrl = pro.getProperty("limsext.url");
            RestTemplate restTemplate = new RestTemplate();
            String url = limsExtUrl + "/api/token-auth/";
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.parseMediaType("applcation/json;charset=UTF-8");
            headers.setContentType(mediaType);
            headers.add("accept", MediaType.APPLICATION_JSON.toString());
            UserToken baseUser = new UserToken();
            baseUser.setUsername("jack");
            baseUser.setPassword("qwer1234");
            HttpEntity<String> httpEntity = new HttpEntity<>(toJsonString(baseUser), headers);
            responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            map = JsonUtils.toObjectByJson(responseEntity.getBody().toString(), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return map.get("token").toString();
    }
}
