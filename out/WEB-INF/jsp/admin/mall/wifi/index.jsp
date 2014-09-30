<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        WIFI串码管理
        <small>
            <i class="icon-double-angle-right"></i>
            WIFI串码列表
        </small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/wifi/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_EQ_code" value="${param.Q_EQ_code}" placeholder="串码">
                    <a class="btn submitbtn">
                        <i class="icon-search"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="" method="post">
                    <a class="btn btn-primary ajax"
                       href="${ctx}/wifi/import.do">
                        <i class="icon-plus"></i>导入
                    </a>
                    <hr/>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>串码</th>
                                <th>状态</th>
                                <th>发送用户</th>
                                <th>导入时间</th>
                                <th>发送时间</th>
                                <th>操作</th>
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
                                    <td>${item.code}</td>
                                    <td>${item.status.title}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty item.feedUserName}">
                                               未发送
                                            </c:when>
                                            <c:otherwise>
                                                ${item.feedUserName}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty item.feedTime}">

                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate value="${item.feedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <a href="${ctx}/wifi/edit.do?id=${item.id} " class="blue ajax"
                                           title="修改"><i
                                                class="icon-edit bigger-120"></i></a>
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