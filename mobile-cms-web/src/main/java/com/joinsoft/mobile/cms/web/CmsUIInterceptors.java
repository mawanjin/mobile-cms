package com.joinsoft.mobile.cms.web;

import com.joinsoft.framework.basic.NotifyService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.repository.UserRepository;
import com.joinsoft.framework.web.UIInterceptors;
import com.joinsoft.mobile.cms.component.Http;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.service.LoginoutService;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import com.joinsoft.mobile.cms.web.front.security.AccessTokenController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * xingsen@join-cn.com
 */
public class CmsUIInterceptors extends UIInterceptors {
    private Logger logger = LoggerFactory.getLogger(CmsUIInterceptors.class);

    @Resource
    private MobileSecurityService securityService;
    @Resource
    private NotifyService notifyService;
    @Resource
    private MobileService mobileService;
    @Resource
    private Http http;
    @Resource
    private UserRepository userRepository;

    @Resource
    private LoginoutService loginoutService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try{

            if(securityService==null)return true;
        String openid = request.getParameter("openId");
        String time = request.getParameter("time");
        if (!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(time)) {
            String signature = new String(DigestUtils.md5Hex(String.format("%s@%s", openid, time)));
            if (signature.equals(request.getParameter("signature"))) {
                //查找用户是否存在，如果不存在，增加用户
                User user = userRepository.findByLoginName(openid);
                if (null == user) {
                    securityService.registerUser(openid);
                }
                UsernamePasswordToken token = new UsernamePasswordToken(openid, openid);
                SecurityUtils.getSubject().login(token);
            }

        }
        if (handler instanceof HandlerMethod &&
                (((HandlerMethod) handler).getBean() instanceof AccessTokenController)) {
            if (securityService.isAdmin()) {
                return true;
            }
            if (request.getRequestURI().indexOf("/auth") != -1) {
                return true;
            }
//            if (!securityService.isBind()) {
//                response.sendRedirect(request.getContextPath() + "/front/at/auth/login.do");
//                return false;
//            }

            if (securityService!=null&&securityService.getLoginUser() != null) {
                //用户已经登录则执行正常逻辑
                return true;
            }

            if (request.getMethod().equalsIgnoreCase("post") && !isLogin(request)) {
                logger.warn("用户请求过期");
                request.getRequestDispatcher("/jsp/500.jsp").forward(request, response);
                return false;
            }

            if (request.getMethod().equalsIgnoreCase("get")) {
                String code = request.getParameter("code");
                //如果用户没有code则跳转
//                if (StringUtils.isEmpty(code)) {
//                    //用户没有拿到code则跳转到微信
//                    String reqURI = URLEncoder.encode(getFullUrl(request), "UTF-8");
//
//                    String url = mobileService.buildGetCodeUrl(reqURI);
//                    logger.info("redirect url {}", url);
//                    response.sendRedirect(url);
//                    return false;
//                }
//               /
            }
        }   }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle0(HttpServletRequest request, HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {
        String requestUri = request.getServletPath();
        if(securityService==null)return;
        User loginUser = securityService.getLoginUser();
        if(request.getSession().getAttribute("loginUser")==null&&loginUser!=null){
            loginSta(request,loginUser);
        }

        if (loginUser != null) {
            request.setAttribute("siderNavList", securityService.buildMenu(requestUri));
            request.setAttribute("backProgressList", notifyService.getReadyAndRunningTask());
            request.setAttribute("notificationsList", notifyService.getNotifyList());
            request.getSession().setAttribute("loginUser", securityService.getLoginUser());


        }
    }

    void loginSta(HttpServletRequest request,User loginUser){
        // 插入数据库登录日志
        TbLoginout tbLoginout = new TbLoginout();
        tbLoginout.setIp(request.getRemoteAddr());
        tbLoginout.setLoginName(loginUser.getLoginName());
        tbLoginout.setName(loginUser.getName());
        Calendar calendar =  Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tbLoginout.setLoginTime(sdf.format(calendar.getTime()));

        loginoutService.save(tbLoginout);
    }

    protected boolean isLogin(HttpServletRequest request) {
        return request.getServletPath().indexOf("/auth") != -1;
    }

    protected String getFullUrl(HttpServletRequest req) {
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();          // d=789

        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
