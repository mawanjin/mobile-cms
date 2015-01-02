package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.service.LoginoutService;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/*")
public class IndexController extends AdminController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private MobileSecurityService securityService;

    @Resource
    private LoginoutService loginoutService;

    @RequestMapping("index")
    public void index(HttpServletRequest request) {

    }

    @RequestMapping("dologout")
    public void dologout(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        //todo 插入数据库退出
        logger.debug("method dologout() called.");

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
