package com.johj.restTemplate;



import com.johj.common.utils.JsonUtils;
import com.johj.common.utils.PropertiesUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class RestClient {
    // TODO: 2017/2/27 获取到lims-ext中的用户token;增加https请求；改变用户名称
    private String getAccessToken() {
        Properties properties=PropertiesUtils.loadProperties("");
        ResponseEntity responseEntity = null;
        Map map = null;
        try {

            String limsExtUrl = properties.getProperty("limsext.url");
            RestTemplate restTemplate = new RestTemplate();
            String url = limsExtUrl + "/api/token-auth/";
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.parseMediaType("applcation/json;charset=UTF-8");
            headers.setContentType(mediaType);
            headers.add("accept", MediaType.APPLICATION_JSON.toString());
            UserToken baseUser = new UserToken();
            baseUser.setUsername("jack");
            baseUser.setPassword("qwer1234");
            HttpEntity<String> httpEntity = new HttpEntity<>(JsonUtils.toJsonString(baseUser), headers);
            responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            map = JsonUtils.toObject(responseEntity.getBody().toString(), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return map.get("token").toString();
    }
}
