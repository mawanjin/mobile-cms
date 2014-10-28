package com.joinsoft.mobile.cms.web;

import com.joinsoft.framework.web.UIHandlerExceptionResolver;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import com.joinsoft.mobile.cms.web.front.FrontController;
import com.joinsoft.mobile.cms.web.front.security.AccessTokenController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
public class CmsHandlerExceptionResolver extends UIHandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(CmsHandlerExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object o, Exception ex) {
        if (o instanceof HandlerMethod) {
            Object bean = ((HandlerMethod) o).getBean();
        //    if (FrontController.class.isAssignableFrom(bean.getClass()) ||
        //            AccessTokenController.class.isAssignableFrom(bean.getClass())) {
                logger.warn("前端页面错误");
                Map<String, Object> model = new HashMap<String, Object>();
                String nextUrl = (String) request.getAttribute("nextUrl");
                //for weixin web brower cache
                if (nextUrl != null && nextUrl.indexOf("?") != -1) {
                    nextUrl += "&_r=" + System.currentTimeMillis();
                }
                if (nextUrl != null && nextUrl.indexOf("?") == -1) {
                    nextUrl += "?_r=" + System.currentTimeMillis();
                }
                if (nextUrl != null) {
                    model.put("nextUrl", nextUrl);
                    logger.info("nextUrl {}", nextUrl);
                }
                model.put("errorMessage", StringUtils.isEmpty(ex.getMessage()) ? ex.toString() : ex.getMessage());
                logger.error(ex.toString(), ex);
                return new ModelAndView("forward:/jsp/500.jsp", model);
          //  }
         //   if (AdminController.class.isAssignableFrom(bean.getClass())) {
           // }
        }
        return super.resolveException(request, response, o, ex);
    }
}
