
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="page-header">
    <h1>
        新闻管理
        <small>
            <i class="icon-double-angle-right"></i>
            新闻列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/${type}/index.do" method="post"
                   class="form-inline">
            <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="标题">
            <a class="btn submitbtn">
                <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
            </a>

        </form:form>
        <div class="hr hr-15 dotted hr-double"></div>
        <form action="${ctx}/${type}/delete.do" method="post " class="ajaxForm">
            <a class="btn btn-primary"
               href="${ctx}/${type}/edit.do">
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
                        <th>#</th>
                        <th>标题</th>
                        <th>创建人</th>

                        <th>最后修改时间</th>
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
                            <td>${item.oper}</td>

                            <td>
                                <fmt:formatDate value="${item.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <a href="${ctx}/${type}/edit.do?id=${item.id}" title="编辑" class="green"><i
                                            class="icon-edit bigger-120"></i></a>
                                    <a href="${ctx}/${type}/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                            class="icon-trash bigger-120"></i></a>

                                    <a href="${ctx}/${type}/up.do?id=${item.id}" class="red delete" title="上线"><i
                                            class="icon-trash bigger-120"></i></a>

                                    <a href="${ctx}/${type}/down.do?id=${item.id}" class="red delete" title="下线"><i
                                            class="icon-trash bigger-120"></i></a>
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

</body>
</html>