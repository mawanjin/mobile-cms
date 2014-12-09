<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.subject.Subject" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>登录</title>

    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <%@include file="/WEB-INF/jsp/common/meta.jsp" %>
    <script>
        $(function () {
            $(".change_verify_code").click(function (event) {
                event.preventDefault();
                $("#verify_code_pic").attr("src", "${ctxRoot}/VerifyCodeServlet?r=" + Math.random());
            });
        });
    </script>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="red">Mobile</span>
                            <span class="white">CMS系统</span>
                        </h1>
                        <h4 class="blue">&copy; 海口南青</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        请输入您的用户名密码
                                    </h4>
                                    <%
                                        String verifyCodeError = (String) request.getAttribute("verifyCodeError");
                                        if (null != verifyCodeError) {
                                            out.print("验证码有误");
                                        } else {
                                            Subject subject = SecurityUtils.getSubject();
                                            if (subject != null) {
                                                String root = request.getContextPath();
                                                if (subject.hasRole("admin")) {
                                                    response.sendRedirect(root + "/admin/index.do");
                                                }
                                            }
                                            String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                                            if (error != null) {
                                                out.print("登录失败，请重试.");
                                            }
                                        }
                                    %>
                                    <div class="space-6"></div>

                                    <form action="${ctxRoot}/jsp/login.jsp" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control"
                                                                   placeholder="用户名"/>
															<i class="icon-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control"
                                                                   placeholder="密码"/>
															<i class="icon-lock"></i>
														</span>
                                            </label>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="text" name="verifyCode" class="col-md-4 form-group"
                                                           placeholder="验证码"/>
                                                      <a href="#" class="change_verify_code">
                                                          <img src="${ctxRoot}/VerifyCodeServlet" id="verify_code_pic"
                                                               class="img-responsive col-md-4"
                                                               alt="Responsive image">
                                                      </a>
                                                    <a href="#" class="change_verify_code col-md-4 ">换一张</a>
                                                    </span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>

                                                <input type="submit"
                                                       class="width-35 pull-right btn btn-sm btn-primary">
                                                <i class="icon-key"></i>
                                                </input>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                </div>
                                <!-- /widget-main -->
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /login-box -->

                    </div>
                    <!-- /position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${ctxRoot}/static/framework/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
</body>
</html>
