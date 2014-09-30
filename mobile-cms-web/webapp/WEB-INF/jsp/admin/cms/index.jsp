<%@ page import="com.joinsoft.mobile.cms.service.content.CmsContentType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:set var="articleGroup" value="<%=CmsContentType.ArticleGroup.name().toLowerCase()%>"></c:set>
<div class="page-header">
    <h1>
        内容管理
        <small>
            <i class="icon-double-angle-right"></i>
            内容列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/${type}/index.do" method="post"
                   class="form-inline">
            <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="内容标题">
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
                        <th>内容标题</th>
                        <th>创建人</th>
                        <th>类型</th>
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
                            <td>${item.authorName}</td>
                            <td>${item.typeDescription}</td>
                            <td>
                                <fmt:formatDate value="${item.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <a href="${ctx}/${type}/edit.do?id=${item.id}" title="编辑" class="green"><i
                                            class="icon-edit bigger-120"></i></a>
                                    <a href="${ctx}/${type}/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                            class="icon-trash bigger-120"></i></a>
                                    <a href="${ctxRoot}/front/cms/show.do?id=${item.id}" class="blue"
                                       onclick="window.open(this.href, 'child', 'height=600, width=480, left=' + (screen.width - 480)/2); return false;"
                                       target="_blank" title="预览"><i
                                            class="icon-external-link bigger-120"></i></a>
                                    <c:if test="${type eq articleGroup}">
                                        <a href="${ctx}/${type}/mass.do?id=${item.id}" class="ajax" title="群发消息"><i
                                                class="icon-envelope bigger-120"></i></a>
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

</body>
</html>