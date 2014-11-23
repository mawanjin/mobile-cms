<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="user-scalable=no"/>
    <title></title>
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