package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.online.signin.ws.OnlineSso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(FrontController.PORTAL_PREFIX + "/*")
public class CommonController extends FrontController {
    @Resource
    private OnlineSso onlineSso;

    @RequestMapping("send_verify_code")
    public
    @ResponseBody
    JsonObject sendVerifyCode(String mobilePhone) {
        onlineSso.sendVerifyCode(mobilePhone);
        return JsonObject.success("发送成功");
    }
}
