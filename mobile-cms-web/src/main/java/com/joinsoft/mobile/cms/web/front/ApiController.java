package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.mobile.cms.service.mobile.MobileService;
import com.joinsoft.online.signin.ws.csb.ApiRequest;
import com.joinsoft.online.signin.ws.csb.ApiResponse;
import com.joinsoft.online.signin.ws.csb.TrafficService;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(value = FrontController.PORTAL_PREFIX + "/api/*")
public class ApiController extends FrontController {
    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Resource
    private MobileService mobileService;
    @Resource
    private TrafficService trafficService;

    @RequestMapping(value = "callback")
    public void check(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response,
                      HttpServletRequest request) {
        try {
            if (request.getMethod().equalsIgnoreCase("get")) {
                if (mobileService.checkSignature(signature, timestamp, nonce)) {
                    logger.info("check success {}", echostr);
                    write(response, echostr);
                    return;
                }
                write(response, "");
                return;
            }
            if (request.getMethod().equalsIgnoreCase("post")) {
                String message = mobileService.onMessage(request.getInputStream());
                logger.info("onMessage {}", message);
                write(response, message);
                return;
            }
            write(response, "");
            return;
        } catch (Exception e) {
            logger.error(e.toString(), e);
            write(response, "");
        }
    }

    protected void write(HttpServletResponse response, String data) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("apply")
    public void applyTraffic(HttpServletRequest request, HttpServletResponse response) {
        try {
            String message = trafficService.callBack(request.getInputStream());
            write(response, message);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
