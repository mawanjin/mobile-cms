<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        广告管理
        <small>
            <i class="icon-double-angle-right"></i>
            广告内容
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/ad/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="广告标题">
                    <a class="btn submitbtn">
                        <i class="icon-search "></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/ad/delete.do" method="post">
                    <a class="btn btn-primary"
                       href="${ctx}/ad/edit.do">
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
                                <th>#</th>
                                <th>广告标题</th>
                                <th>链接地址</th>
                                <th>创建时间</th>
                                <th>广告描述</th>
                                <th>统计(引用次数/查看次数)</th>
                                <th></th>
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
                                    <td>${item.id}</td>
                                    <td>${item.title}</td>
                                    <td><a href="${item.picUrl}" target="_blank">${item.picUrl}</a>
                                    <td>
                                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>${item.description}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${ empty item.adStats}">
                                               0次/0次
                                            </c:when>
                                            <c:otherwise>
                                                ${item.adStats.refCount}次/${item.adStats.visitCount}次
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    </td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/ad/edit.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/ad/delete.do?id=${item.id}" class="red" title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>
                                            <a href="${ctx}/ad/show.do?id=${item.id}" class="blue" title="查看"><i
                                                    class="icon-external-link bigger-120"></i></a>

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
