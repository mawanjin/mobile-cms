package com.joinsoft.mobile.cms.web.admin.cms.listener;


import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.service.LoginoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by abcd on 14/12/30.
 */

public class MSessionListener implements HttpSessionListener {

    private Logger logger = LoggerFactory.getLogger(MSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("sessionCreated called.");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug("sessionDestroyed called.");

        ServletContext ctx=httpSessionEvent.getSession().getServletContext();
        LoginoutService loginoutService = (LoginoutService) WebApplicationContextUtils.getRequiredWebApplicationContext(ctx).getBean("loginoutService");

        User user = (User) httpSessionEvent.getSession().getAttribute("loginUser");
        if(user==null)return;
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Page<TbLoginout> page = loginoutService.findLastRecord(user.getLoginName(),new PageRequest(0,1,sort));
        if(page.getContent()==null||page.getContent().size()==0)return;
        TbLoginout loginout = page.getContent().get(0);
        Calendar calendar =  Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loginout.setLogoutTime(sdf.format(calendar.getTime()));
        loginoutService.save(loginout);
    }
}
