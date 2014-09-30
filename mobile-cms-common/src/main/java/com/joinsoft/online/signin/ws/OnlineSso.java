package com.joinsoft.online.signin.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.joinsoft.framework.util.Json;
import com.joinsoft.online.signin.MessageException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Component
public class OnlineSso {
    private Logger logger = LoggerFactory.getLogger(OnlineSso.class);

    @Value("${config.online.sso.appid}")
    private String appId;
    @Value("${config.online.sso.serial_num}")
    private String serialNum;
    @Resource
    private SsoWsService ssoWsService;

    public boolean userExist(String mobilePhone) {
        Map<String, String> request = new HashMap<String, String>();
        request.put("Mobile_phone", mobilePhone);
        String reqJson = buildRequest(request);
        logger.info("request sso api {}", reqJson);
        String responseJson = ssoWsService.userExist(reqJson);
        logger.info("response sso api {}", responseJson);
        JsonNode result = Json.parse(responseJson);

        int status = result.get("status").asInt();
        if (status == 0) {
            return true;
        }
        if (status == 10001) {
            return false;
        }
        throw new RuntimeException(result.get("ret_msg").asText());
    }

    public void userReg(String mobilePhone, String password, String verifyCode) {
        Map<String, String> request = new HashMap<String, String>();
        request.put("Mobile_phone", mobilePhone);
        request.put("Mobile_pwd", password);
        request.put("Mobile_code", verifyCode);


        String reqJson = buildRequest(request);
        logger.info("request sso api {}", reqJson);
        String responseJson = ssoWsService.userReg(reqJson);
        logger.info("response sso api {}", responseJson);
        JsonNode result = Json.parse(responseJson);

        int status = result.get("status").asInt();
        if (status != 0) {
            throw new MessageException(result.get("ret_msg").asText());
        }
    }

    public String sendVerifyCode(String mobilePhone) {
        Map<String, String> request = new HashMap<String, String>();
        request.put("Mobile_phone", mobilePhone);
        request.put("Sms_Type", "3");

        String reqJson = buildRequest(request);
        logger.info("request sso api {}", reqJson);
        String responseJson = ssoWsService.regCode(reqJson);
        logger.info("response sso api {}", responseJson);
        JsonNode result = Json.parse(responseJson);

        int status = result.get("status").asInt();

        if (status != 0) {
            throw new MessageException(result.get("ret_msg").asText());
        }
        return result.get("ret_msg").asText();
    }

    public void resetPassword(String mobilePhone, String newPassword, String verifyCode) {
        Map<String, String> request = new HashMap<String, String>();
        request.put("Mobile_phone", mobilePhone);
        request.put("Mobile_pwd", newPassword);
        request.put("Mobile_code", verifyCode);

        String reqJson = buildRequest(request);
        logger.info("request sso api {}", reqJson);
        String responseJson = ssoWsService.restPwd(reqJson);
        logger.info("response sso api {}", responseJson);
        JsonNode result = Json.parse(responseJson);

        int status = result.get("status").asInt();
        if (status != 0) {
            throw new MessageException(result.get("ret_msg").asText());
        }
    }

    public String login(String mobilePhone, String password) {
        Map<String, String> request = new HashMap<String, String>();
        request.put("Mobile_phone", mobilePhone);
        request.put("Mobile_pwd", password);

        String reqJson = buildRequest(request);
        logger.info("request sso api {}", reqJson);
        String responseJson = ssoWsService.userLoginMW(reqJson);
        logger.info("response sso api {}", responseJson);
        JsonNode result = Json.parse(responseJson);

        int status = result.get("status").asInt();
        if (status == 0) {
            return result.get("token").asText();
        }
        if (status == 10001) {
            throw new MessageException("用户不存在");
        }
        if (status == 10002) {
            throw new MessageException("密码错误");
        }
        throw new MessageException(result.get("ret_msg").asText());
    }

    protected String buildRequest(Map<String, String> request) {
        request.put("App_no", appId);
        request.put("Serial_num", serialNum);
        StringBuilder sb = new StringBuilder();
        List<String> keys = Lists.newArrayList(request.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(String.format("%s=%s&", key, request.get(key)));
        }
        String rawParams = sb.substring(0, sb.length() - 1);
        String sign = DigestUtils.md5Hex(rawParams + serialNum);
        return rawParams + "&sign=" + sign;
    }
}
