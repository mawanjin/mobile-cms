<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="mytable"
     style="width: 100%;height: 100%;background: #ffffff;font-family: 'trebuchet MS', 'Lucida sans', Arial;font-size: 12px;color: #444;">
    <h2 style="background:#6699cc;color:#ffffff;padding-top:10px;padding-bottom: 10px;padding-left: 5px;margin-bottom: 10px;">新闻中心</h2>
    <h4 style="width: 98%;height:40px;line-height: 40px;background: #f7f7f7;text-align: center;margin-bottom: 5px;font-size: 18px;">${new1.title}</h4>

    <div style="width: 98%;margin: 0 auto;border:0px solid red;">
        ${new1.content}
    </div>
</div>
</body>
</html>
