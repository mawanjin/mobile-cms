package com.joinsoft.mobile.cms.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 14-11-11.
 */
@Service
@Transactional
public class SailingDateService {
    public String findPortScope(String sport, String port) {
        String url = "http://180.173.87.191/cmsService/admin_fetchChuanQi.action";
        try {
            url += "?sport=" + URLEncoder.encode(sport, "utf-8") + "&port=" + URLEncoder.encode(port, "utf-8") + "";
        } catch (Exception e) {
            return "";
        }
        return common(url);
    }

    public String findPort(String mort) {
        String url = "http://180.173.87.191/cmsService/admin_fetchChuanQi.action";
        try {
            url += "?mport=" + URLEncoder.encode(mort, "utf-8") + "";
        } catch (Exception e) {
            return "";
        }
        return common(url);
    }

    public String findShips(String fight, String ship) {
        String url = "http://180.173.87.191/cmsService/admin_fetchChuanQi.action";
        try {
            url += "?fight=" + URLEncoder.encode(fight, "utf-8") + "&ship=" + URLEncoder.encode(ship, "utf-8") + "";
        } catch (Exception e) {
            return "";
        }
        return common(url);
    }

    private String common(String url) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            client.executeMethod(method);
            return new String(method.getResponseBodyAsString().getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return "";
    }
}
