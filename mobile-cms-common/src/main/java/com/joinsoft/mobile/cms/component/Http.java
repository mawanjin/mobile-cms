package com.joinsoft.mobile.cms.component;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * xingsen@join-cn.com
 */
@Component
public class Http {
    private Logger logger = LoggerFactory.getLogger(Http.class);

    @Resource
    private EasyJson easyJson;

    public JsonNode get(String url) {
        try {
            logger.info("http get {}", url);
            Content t = Request.Get(url).connectTimeout(5000)
                    .socketTimeout(5000).addHeader("User-Agent", "WeiXinCms")
                    .execute().returnContent();
            String json = new String(t.asBytes(), "UTF-8");
            JsonNode jsonNode = easyJson.readTree(json);
            if (jsonNode.has("errcode") && jsonNode.get("errcode").asInt() != 0) {
                throw new RuntimeException(json);
            }
            return jsonNode;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode post(String url, String data) {
        try {
            logger.info("http post {} data {}", url, data);
            Content t = Request.Post(url)
                    .bodyString(data, ContentType.APPLICATION_JSON).execute().returnContent();
            String json = new String(t.asBytes(), "UTF-8");
            JsonNode jsonNode = easyJson.readTree(json);
            if (jsonNode.has("errcode") && jsonNode.get("errcode").asInt() != 0) {
                throw new RuntimeException(json);
            }
            return jsonNode;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonNode multipartPost(String url, HttpEntity entity) {
        try {
            logger.info("http multipartPost {}", url);
            Content t = Request.Post(url)
                    .body(entity).execute().returnContent();
            String json = new String(t.asBytes(), "UTF-8");
            JsonNode jsonNode = easyJson.readTree(json);
            if (jsonNode.has("errcode") && jsonNode.get("errcode").asInt() != 0) {
                throw new RuntimeException(json);
            }
            return jsonNode;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
