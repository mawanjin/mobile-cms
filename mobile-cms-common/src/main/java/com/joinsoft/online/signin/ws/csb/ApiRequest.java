package com.joinsoft.online.signin.ws.csb;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class ApiRequest {
    private Long taskId;
    private String code;
    private String time;
    private JsonNode param;

    public ApiRequest(Long taskId) {
        this.taskId = taskId;
        this.time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public ApiRequest() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public JsonNode getParam() {
        return param;
    }

    public void setParam(JsonNode param) {
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean validate(String accessKey) {
        return encrypt(accessKey).equals(code);
    }

    public String encrypt(String accessKey) {
        return DigestUtils.md5Hex(String.format("@%s@%s@%s@", taskId, accessKey, time));
    }
}
