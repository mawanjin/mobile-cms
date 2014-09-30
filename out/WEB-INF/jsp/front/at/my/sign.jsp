<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script>
        <c:if test="${not empty result}">
        $(function () {
            $(".tank h1").css({display: "none"});
            $(".tank h2").fadeIn(3000);
            $(".tank h3").fadeIn(3000);
            $(".sign_button p").fadeIn(2000);
            $(".wave").css({display: "block"});
            $(".wave").animate({marginLeft: '-100em', marginTop: '-4.4em'}, 4000);
        });
        </c:if>
    </script>
</head>
<body>
<div class="page_sign">
    <div class="animation_sign">
        <div class="circle_light">
        </div>
        <div class="tank">
            <img src="${ctxRoot}/static/front/images/wave.png" class="wave"/>

            <h1 class="btn_a" href="${ctx}/my/do_sign.do">点击签到</h1>
            <c:if test="${not empty result}">
                <c:choose>
                    <c:when test="${result.pointVal > 0}">
                        <h2>${result.pointVal}点</h2>

                        <h3>积点领取</h3>
                        <c:set var="sign_message" value="今日领取积点${result.pointVal}点"/>
                    </c:when>
                    <c:when test="${result.trafficVal > 0}">
                        <h2>${result.trafficVal}M</h2>

                        <h3>流量领取</h3>
                        <c:set var="sign_message" value="今日领取流量${result.trafficVal}M"/>
                    </c:when>
                    <c:when test="${result.trafficVal ==0 and result.pointVal == 0}">
                        <h2>已签到</h2>
                        <%--<h2>${result.pointVal}</h2>--%>
                        <%--<h3>已签到</h3>--%>
                        <c:set var="sign_message" value="您已经签到"/>
                        <c:set var="sign_message" value="今日已签到"/>
                    </c:when>
                </c:choose>

            </c:if>
        </div>
    </div>
    <div class="sign_button">
        <p>${sign_message}</p>
    </div>
</div>
</body>
</html>
