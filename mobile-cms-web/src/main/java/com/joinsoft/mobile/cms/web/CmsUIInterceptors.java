package com.joinsoft.mobile.cms.web;

import com.joinsoft.framework.basic.NotifyService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.repository.UserRepository;
import com.joinsoft.framework.web.UIInterceptors;
import com.joinsoft.mobile.cms.component.Http;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        String openid = request.getParameter("openId");
//        String time = request.getParameter("time");
//        if (!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(time)) {
//            String signature = new String(DigestUtils.md5Hex(String.format("%s@%s", openid, time)));
//            if (signature.equals(request.getParameter("signature"))) {
//                //查找用户是否存在，如果不存在，增加用户
//                User user = userRepository.findByLoginName(openid);
//                if (null == user) {
//                    securityService.registerUser(openid);
//                }
//                UsernamePasswordToken token = new UsernamePasswordToken(openid, openid);
//                SecurityUtils.getSubject().login(token);
//            }
//
//        }
//        if (handler instanceof HandlerMethod &&
//                (((HandlerMethod) handler).getBean() instanceof AccessTokenController)) {
//            if (securityService.isAdmin()) {
//                return true;
//            }
//            if (request.getRequestURI().indexOf("/auth") != -1) {
//                return true;
//            }
////            if (!securityService.isBind()) {
////                response.sendRedirect(request.getContextPath() + "/front/at/auth/login.do");
////                return false;
////            }
//
//            if (securityService.getLoginUser() != null) {
//                //用户已经登录则执行正常逻辑
//                return true;
//            }
//
//            if (request.getMethod().equalsIgnoreCase("post") && !isLogin(request)) {
//                logger.warn("用户请求过期");
//                request.getRequestDispatcher("/jsp/500.jsp").forward(request, response);
//                return false;
//            }
//
//            if (request.getMethod().equalsIgnoreCase("get")) {
//                String code = request.getParameter("code");
//                //如果用户没有code则跳转
//                if (StringUtils.isEmpty(code)) {
//                    //用户没有拿到code则跳转到微信
//                    String reqURI = URLEncoder.encode(getFullUrl(request), "UTF-8");
//
//                    String url = mobileService.buildGetCodeUrl(reqURI);
//                    logger.info("redirect url {}", url);
//                    response.sendRedirect(url);
//                    return false;
//                }
//                if (isLogin(request)) {
//                    //登录界面不做自动登录,防止死循环
//                    return true;
//                }
//                //根据openid登录
//                String openId = mobileService.getOpenId(code);
//                boolean isLogin = securityService.ifBindThenLogin(openId);
//                if (!isLogin) {
//                    //登录失败则认为用户还未绑定
//                    response.sendRedirect(request.getContextPath() + "/front/at/auth/login.do");
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @Override
    public void postHandle0(HttpServletRequest request, HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {
        String requestUri = request.getServletPath();
        User loginUser = securityService.getLoginUser();
        if (loginUser != null) {
            request.setAttribute("siderNavList", securityService.buildMenu(requestUri));
            request.setAttribute("backProgressList", notifyService.getReadyAndRunningTask());
            request.setAttribute("notificationsList", notifyService.getNotifyList());
            request.setAttribute("loginUser", securityService.getLoginUser());
        }
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
