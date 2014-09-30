<%@ page import="com.joinsoft.mobile.cms.entity.enumerate.ValueType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        规则管理
        <small>
            <i class="icon-double-angle-right"></i>
            规则内容
        </small>
    </h1>
</div>
<c:set value="<%=ValueType.Fixed%>" var="Fixed"/>
<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/action/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_cnName" value="${param.Q_LIKE_cnName}" placeholder="中文名称">
                    <a class="btn submitbtn">
                        <i class="icon-search"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/action/delete.do" method="post">
                    <a class="btn btn-primary"
                       href="${ctx}/action/edit.do">
                        <i class="icon-plus"></i>添加
                    </a>
                    <a class="btn submitbtn">
                        <i class="icon-remove"></i>删除
                    </a>
                    <hr/>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>规则名称</th>
                                <th>中文名称</th>
                                <th>是否有效</th>
                                <th>值类型</th>
                                <th>值内容</th>
                                <th>周期类型</th>
                                <th>次数</th>
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
                                    <td>${item.actionName}</td>
                                    <td>${item.cnName}</td>
                                    <th>
                                        <c:if test="${item.isValid}">有效</c:if>
                                        <c:if test="${not item.isValid}">无效</c:if>
                                    </th>
                                    <td>${item.valueType.title}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.valueType eq Fixed}">${item.minValue}</c:when>
                                            <c:otherwise>${item.minValue} -- ${item.maxValue}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${item.cycle.title}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty item.count}">
                                                无限次
                                            </c:when>
                                            <c:otherwise>
                                                ${item.count}次
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/action/edit.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/action/delete.do?id=${item.id}" class="red"
                                               title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>
                                            <a href="${ctx}/action/config.do?id=${item.id} " class="blue ajax"
                                               title="绑定配置"><i
                                                    class="icon-cog bigger-120"></i></a>
                                        </div>

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