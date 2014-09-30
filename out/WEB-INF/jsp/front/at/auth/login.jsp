<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="login_top">
    <img src="${ctxRoot}/static/front/images/loginTop.jpg"/>
</div>
<form action="${ctx}/auth/login.do" method="post" class="login_form">
    <input type="hidden" value="${code}" name="code"/>
    <table class="login_box">
        <tr class="ln1">
            <th>用户名：</th>
            <td><input type="text" name="mobilePhone" id="mobilePhone" class="ipt_1" placeholder="请填写正确的手机号码"/></td>
        </tr>
        <tr class="ln1">
            <th>密　码：</th>
            <td><input type="password" name="password" id="password" class="ipt_1" placeholder="请填写正确的密码"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登　录" class="btn_1 ora"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="注　册" class="btn_1 cya btn_a"
                       href="${ctx}/auth/reg.do"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <span><a href="${ctx}/auth/restore.do">忘记密码？</a></span>
            </td>
        </tr>
        <tr >
            <td colspan="2" style="text-align: center;">
                <span style="color:red;">上海热线手机账号请直接登录</span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
