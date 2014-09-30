package com.joinsoft.online.signin.ws.csb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joinsoft.framework.util.Json;
import com.joinsoft.mobile.cms.component.EasyJson;
import com.joinsoft.mobile.cms.entity.TbTrafficDetail;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import com.joinsoft.mobile.cms.entity.enumerate.TrafficDetailStatus;
import com.joinsoft.mobile.cms.repository.TrafficDetailRepository;
import com.joinsoft.mobile.cms.repository.UserProfileRepository;
import javassist.bytecode.ExceptionTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Component
public class TrafficService {
    protected ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(TrafficService.class);
    @Value("${config.traffic_api.order}")
    private String trafficApi;
    @Value("${config.traffic_api.task_id}")
    private Long taskId;
    @Value("${config.traffic_api.access_key}")
    private String accessKey;

    @Resource
    private TrafficDetailRepository trafficDetailRepository;
    @Resource
    private UserProfileRepository userProfileRepository;


    @Transactional
    public void order(String mobile, Integer flow, Long orderNum) {
        OrderParam orderParam = new OrderParam();
        orderParam.setUserAccount(mobile);
        orderParam.setFlow(flow);
        orderParam.setMobile(mobile);
        orderParam.setOrderNum(String.format("%s", orderNum.longValue()));
        ApiResponse response = call(orderParam);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getMessage());
        }

    }

    protected ApiResponse call(Object jsonNode) {
        try {
            ApiRequest request = new ApiRequest(taskId);
            request.setCode(request.encrypt(accessKey));
            JsonNode param = objectMapper.convertValue(jsonNode, JsonNode.class);
            request.setParam(param);
            logger.info("requestParmas:{}", Json.toJson(param).toString());
            Response response = Request.Post(trafficApi)
                    .bodyString(objectMapper.writeValueAsString(request), ContentType.APPLICATION_JSON)
                    .execute();
            String json = new String(response.returnContent().asBytes(), "UTF-8");
            logger.info("response:{}", json);
            return objectMapper.readValue(json, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String callBack(InputStream callback) {
        ApiResponse returnResponse = new ApiResponse();
        try {
            ApiResponse response = objectMapper.readValue(callback, ApiResponse.class);
            logger.info("callback response:{}",objectMapper.writeValueAsString(response));
            returnResponse.setSuccess(false);
            if (response.isSuccess()) {
                Long orderNumber = Long.parseLong(response.getData().get("orderNum").asText().trim());
                TbTrafficDetail detail = trafficDetailRepository.findOne(orderNumber);
                if (null == detail) {
                    throw new RuntimeException("orderNum有错误");
                }
                detail.setStatus(TrafficDetailStatus.Executed);
                detail.setEffectTime(new Date());
                trafficDetailRepository.save(detail);
                returnResponse.setSuccess(true);
            } else {
                throw new RuntimeException("返回的success为false");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnResponse.setMessage(e.getMessage());
        }
        return objectMapper.valueToTree(returnResponse).toString();
    }

    @Transactional
    public void applyTrafficFromTelecom() {
        PageRequest pageRequest = new PageRequest(0, 2);
        List<TbTrafficDetail> trafficDetails = trafficDetailRepository.findTrafficForApply(pageRequest, TrafficDetailStatus.Applied);
        for (TbTrafficDetail trafficDetail : trafficDetails) {
            Long userId = trafficDetail.getUserId();
            TbUserProfile userProfile = userProfileRepository.findByUser(userId);
            if (null == userProfile || StringUtils.isEmpty(userProfile.getMobile())) {
                throw new RuntimeException("手机号码不存在");
            }
            this.order(userProfile.getMobile(), trafficDetail.getTraffic().multiply(new BigDecimal("-1")).intValue(), trafficDetail.getId());
            trafficDetail.setStatus(TrafficDetailStatus.Processing);
        }
        trafficDetailRepository.save(trafficDetails);
    }
}
