<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" manifest="movie.appcache">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi"/>
    <title>签个到</title>
    <link rel="stylesheet" href="${ctxRoot}/static/front/css/style.css?r=<%=System.currentTimeMillis()%>">
    <script src='${ctxRoot}/static/framework/js/jquery-1.10.2.min.js'></script>
    <script type="text/javascript" charset="utf-8"
            src="${ctxRoot}/static/front/js_source/jquery.blockUI.js?r=<%=System.currentTimeMillis()%>"></script>
    <script type="text/javascript" charset="utf-8"
            src="${ctxRoot}/static/front/js_source/sign.js?r=<%=System.currentTimeMillis()%>"></script>
    <script type="text/javascript" charset="utf-8"
            src="${ctxRoot}/static/front/front_plugin.js?r=<%=System.currentTimeMillis()%>"></script>
    <sitemesh:head/>
</head>
<body>
<%--<div class="menu">--%>
    <%--<ul>--%>
        <%--<li class="m1"><a href="${ctxRoot}/front/cms/index.do"><span>活动</span></a></li>--%>
        <%--<li class="m2"><a href=""><span>我的</span></a></li>--%>
        <%--<li class="m3"><a href=""><span>怎么玩</span></a></li>--%>
        <%--<li class="m4"><a href="${ctxRoot}/front/at/my/sign.do"><span>签个到</span></a></li>--%>
    <%--</ul>--%>
<%--</div>--%>
<%--<ul class="submenu_1">--%>
    <%--<li><a href="${ctxRoot}/front/at/my/traffic.do" class="btn_a">流量历史</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/at/my/point.do" class="btn_a">积点历史</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/at/my/order.do" class="btn_a">兑换历史</a></li>--%>
<%--</ul>--%>
<%--<ul class="submenu_2">--%>
    <%--<li><a href="${ctxRoot}/front/cms/index.do" class="btn_a">热门活动</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/at/mall/index.do" class="btn_a">积点兑换</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/cms/show.do?id=1" class="btn_a">帮助Q&A</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/at/my/activity_sign.do" class="btn_a">活动签到</a></li>--%>
    <%--<li><a href="${ctxRoot}/front/at/agent/index.do" class="btn_a">agent</a></li>--%>
<%--</ul>--%>

<div class="page bgray">
    <sitemesh:body/>
    <div class="exc_inforbox">
        <img src="${ctxRoot}/static/front/images/btn_close.png" class="btn_quit"/>
        <img src="${ctxRoot}/static/front/images/icon_hint.png" class="hinticon"/>

        <h1 id="info_message"></h1>
    </div>
</div>

</body>
</html>