package com.joinsoft.online.signin.ws.csb;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * xingsen@join-cn.com
 */
public class ApiResponse {
    private String message;
    private boolean success;
    private JsonNode data;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getData() {
        return data;
    }

    public static ApiResponse success() {
        return ApiResponse.success(null);
    }

    public static ApiResponse success(String message) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage(message);
        return response;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
