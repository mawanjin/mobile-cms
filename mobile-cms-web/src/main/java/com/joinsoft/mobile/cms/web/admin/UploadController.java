package com.joinsoft.mobile.cms.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * User: mawanjin@join-cn.com
 * Date: 14/11/10
 * Time: 00:56
 */
@Controller
@RequestMapping("/upload/")
public class UploadController {
    @RequestMapping("avatar/")
    public void index(HttpServletRequest request){
        String a = request.getContextPath();
    }
}
