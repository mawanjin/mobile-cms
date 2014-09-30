<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="register">
    <form action="${ctx}/auth/restore.do" method="post" class="reg_form">
        <input type="hidden" name="code" value="${code}"/>
        <table class="login_box">
            <tr class="ln1">
                <th>手机号：</th>
                <td><input type="text" class="ipt_1" name="mobilePhone" id="mobilePhone" value="${mobilePhone}"
                           placeholder="请填写正确的手机号码"/></td>
            </tr>

            <tr class="ln1">
                <th>新密码：</th>
                <td><input type="password" class="ipt_1" name="newPassword" id="password" placeholder="输入新密码"/></td>
            </tr>
            <tr class="ln1">
                <th>确认密码：</th>
                <td><input type="password" class="ipt_1" name="rePassword" id="repassword" placeholder="确认新密码"/></td>
            </tr>
            <tr class="ln1">
                <th>验证码：</th>
                <td><input type="text" name="verifyCode" id="verifyCode" class="ipt_2" placeholder="请填写验证码"/>
                    <input type="button" value="发送验证码" class="btn_2 ora send"
                           href="${ctxRoot}/front/send_verify_code.do"/>

                    <p class="idcode ln2 rest_second"></p></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="确认修改" class="btn_1 cya"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
