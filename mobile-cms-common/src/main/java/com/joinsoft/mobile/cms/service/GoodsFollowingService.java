package com.joinsoft.mobile.cms.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * Created by Administrator on 14-11-12.
 */
@Service
public class GoodsFollowingService {

    public String findGoods(String yundan, String box) {
        String url = "http://180.173.142.45/cmsService/admin_fetchHuo.action";
        HttpClient client = new HttpClient();
        HttpMethod method = null;
        try {
            url += "?yundan=" + URLEncoder.encode(yundan, "utf-8") + "&box=" + URLEncoder.encode(box, "utf-8") + "";
            method = new GetMethod(url);
            client.executeMethod(method);
            return new String(method.getResponseBodyAsString().getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            return "";
        } finally {
            method.releaseConnection();
        }
    }
}
