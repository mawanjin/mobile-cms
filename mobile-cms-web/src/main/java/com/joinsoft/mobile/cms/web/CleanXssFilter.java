package com.joinsoft.mobile.cms.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xingsen@join-cn.com
 */
public class CleanXssFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (httpReq.getMethod().equalsIgnoreCase("post")) {
            //此处比较验证码，如果比较失败则不要执行doFilter
            String sessionVerifyCode = (String) ((HttpServletRequest) req).getSession().getAttribute("verifyCode");
            String formVerifyCode = req.getParameter("verifyCode");
            if (StringUtils.isEmpty(formVerifyCode)|| !formVerifyCode.toUpperCase().equals(sessionVerifyCode)) {
                req.setAttribute("verifyCodeError","验证码有误");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
                return ;
            }
        }
        httpReq.getSession().invalidate();
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
