<%@ page import="com.joinsoft.mobile.cms.entity.enumerate.PollType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        投票管理
        <small>
            <i class="icon-double-angle-right"></i>
            投票列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/poll/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_node.title" value="${param['Q_LIKE_node.title']}" placeholder="投票标题">
                    <input type="hidden" name="Q_EQ_type" value="${param.Q_EQ_type}">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>
                    <br><br>
                    <ul class="nav nav-tabs" _target_input="Q_EQ_type">
                        <li class="<c:if test="${empty param.Q_EQ_type}">active</c:if>">
                            <a class="nav_tab_filter" href="">
                                全部
                            </a>
                        </li>
                        <c:forEach var="typeItem" items="<%=PollType.values()%>">
                            <li class="<c:if test="${param.Q_EQ_type eq typeItem}">active</c:if>">
                                <a class="nav_tab_filter" href="${typeItem}">
                                        ${typeItem.text}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>

                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/poll/delete.do" method="post">
                    <a class="btn btn-primary"
                       href="${ctx}/poll/edit.do">
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
                                <th>投票标题</th>
                                <th>类型</th>
                                <th>创建人</th>
                                <th>状态</th>
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
                                    <td>${item.authorName}</td>
                                    <td>${item.status.text}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/poll/edit.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctx}/poll/option/index.do?Q_EQ_poll.node.id=${item.id}" class="red" title="管理投票选项">
                                                <i class="icon-sitemap bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctx}/poll/security/index.do?Q_EQ_poll.node.id=${item.id}" title="数据安全" class="red"><i
                                                    class="icon-group bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctx}/poll/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctxRoot}/front/cms/show.do?id=${item.id}"
                                               onclick="window.open(this.href, 'child', 'height=600, width=480, left=' + (screen.width - 480)/2); return false;"
                                               class="blue" target="_blank" title="预览"><i
                                                    class="icon-external-link bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctx}/poll/result.do?Q_EQ_poll.node.id=${item.id}" class="blue" title="查看投票结果"><i
                                                    class="icon-eye-open bigger-120"></i></a>&nbsp;&nbsp;
                                            <a href="${ctx}/poll/reward/index.do?Q_EQ_poll.node.id=${item.id}" class="green" title="兑奖"><i
                                                    class="icon-gift bigger-120"></i></a>&nbsp;&nbsp;
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