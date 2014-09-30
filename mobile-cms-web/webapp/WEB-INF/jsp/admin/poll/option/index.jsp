<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        <a href="${ctx}/poll/index.do">
        投票管理
        </a>
        <small>
            <i class="icon-double-angle-right"></i>
            投票选项列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12 h3">${poll.node.title}</div>
            <div class="col-xs-12">
                <form:form action="${ctx}/poll/option/index.do" method="post"
                           class="form-inline">
                    <input type="hidden" name="Q_EQ_poll.node.id" value="${poll.id}">
                    <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="选项标题">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/poll/option/delete.do" method="post">
                    <a class="btn btn-primary ajax"
                       href="${ctx}/poll/option/edit.do?poll_id=${poll.id}">
                        <i class="icon-plus icon-large"></i>添加
                    </a>
                    <a class="btn submitbtn">
                        <i class="icon-remove icon-large"></i>删除
                    </a>
                    <hr/>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>选项标题</th>
                                <th>类型</th>
                                <c:if test="${poll.type eq 'SurveyScore'}">
                                    <th>分数</th>
                                </c:if>
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
                                    <td>${item.title}</td>
                                    <td>${item.type.text}</td>
                                    <c:if test="${poll.type eq 'SurveyScore'}">
                                        <td>${item.score}</td>
                                    </c:if>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/poll/option/edit.do?id=${item.id}" title="编辑" class="green ajax"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/poll/option/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>
                                            <c:if test="${'Text' != item.type}">
                                            &nbsp;
                                            <a href="${ctx}/poll/option/value/index.do?option_id=${item.id}" class="red" title="选项内容">
                                                <i class="icon-user-md bigger-120">编辑选项内容</i></a>&nbsp;
                                            </c:if>
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