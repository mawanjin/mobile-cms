<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>


</head>
<body>
<div class="register">
    <form action="${ctx}/auth/reg.do" method="post" class="reg_form">
        <input type="hidden" name="code" value="${code}"/>
        <table class="login_box">
            <tr class="ln1">
                <th>手机：</th>
                <td><input type="text" name="mobilePhone" id="mobilePhone" class="ipt_1" placeholder="请填写正确的手机号码"/></td>
            </tr>
            <tr class="ln1">
                <th>密码：</th>
                <td><input type="password" name="password" class="ipt_1" id="password" placeholder="请填写密码"/></td>
            </tr>
            <tr class="ln1">
                <th>重复输入：</th>
                <td><input type="password" class="ipt_1" name="rePassword" id="repassword" placeholder="请重新确认密码"/></td>
            </tr>
            <tr class="ln1">
                <th>邀请人：</th>
                <td><input type="text" class="ipt_1" name="invite" placeholder="邀请人手机"/></td>
            </tr>

            <tr class="ln1">
                <th>验证码：</th>
                <td><input type="text" name="verifyCode" id="verifyCode" class="ipt_2" placeholder="请填写验证码"/>
                    <input type="button" value="发送验证码" class="btn_2 ora send"
                           href="${ctxRoot}/front/send_verify_code.do"/>

                    <p class="idcode ln2 rest_second"></p></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input type="checkbox" id="agreeReg"/>
                    同意“ <a href="${ctxRoot}/front/cms/show.do?id=2">签个到用户注册协议</a>”
                </td>
            </tr>


            <tr>
                <td colspan="2"><input type="submit" value="立即注册" class="btn_1 cya"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
