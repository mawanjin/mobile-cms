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
            <a href="${ctx}/poll/option/index.do?Q_EQ_poll.id=${pollOption.poll.id}">
                投票选项列表
            </a>
        </small>
        <small>
            <i class="icon-double-angle-right"></i>
            选项内容列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12 h3">${pollOption.title}</div>
            <div class="col-xs-12">
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/poll/option/value/delete.do" method="post" class="ajaxForm">
                    <a class="btn btn-primary ajax"
                       href="${ctx}/poll/option/value/edit.do?poll_option_id=${pollOption.id}">
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
                                <th>选项内容</th>
                                <th>类型</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${valueItems}">
                                <tr>
                                    <td class="center">
                                        <label><input type="checkbox" class="ace" name="ids" id="ids"
                                                      value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>
                                        <c:if test="${item.type eq 'String'}">
                                            ${item.value}
                                        </c:if>
                                        <c:if test="${item.type eq 'Pic'}">
                                            <img src="${vfsRoot}${item.value}" height="50">
                                        </c:if>
                                        <c:if test="${item.type eq 'Video'}">
                                            <a href="${item.value}" target="_blank">${item.value}</a>
                                        </c:if>
                                    </td>
                                    <td>${item.type.text}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/poll/option/value/edit.do?id=${item.id}" title="编辑" class="green ajax"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/poll/option/value/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>
                                        </div>

                                    </td>

                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>