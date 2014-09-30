<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        统计管理
        <small>
            <i class="icon-double-angle-right"></i>
            流量详细
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/log/traffic.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_loginName" value="${param.Q_LIKE_loginName}" placeholder="用户">
                    <input type="text" name="Q_LIKE_cnName" value="${param.Q_LIKE_cnName}" placeholder="获取途径">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="#" method="post">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>用户</th>
                                <th>途径</th>
                                <th>流量</th>
                                <th>状态</th>
                                <th>获取时间</th>
                                <th>生效时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${page.content}">
                                <tr>
                                    <td class="center">
                                        <label><input type="checkbox" class="ace" name="ids" id="ids"
                                                      value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>${item.loginName}</td>
                                    <td>${item.cnName}</td>
                                    <td>${item.traffic}</td>
                                    <td>${item.status.title}</td>
                                    <td>
                                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty item.effectTime}">
                                                还未生效
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate value="${item.effectTime}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>


                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <tags:page page="${page}"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>