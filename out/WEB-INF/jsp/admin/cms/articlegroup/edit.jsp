<%@ page import="com.joinsoft.mobile.cms.service.content.CmsContentType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        内容管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑多图文
        </small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form action="${ctx}/articlegroup/save.do" method="post" id="from">
            <input type="hidden" id="id" name="id" value="${id}"/>
            <input type="hidden" id="dlgResult" name="dlgResult"
                   <c:if test="${not empty existIds}">value="${existIds},"</c:if>
                    />
            <a class="btn btn-purple ajax" id="add_article"
               href="${ctx}/dialog.do?childAjax=false&type=<%=CmsContentType.Article%>,<%=CmsContentType.Poll%>&multi=true&existIds=${existIds}">
                <i class="icon-plus icon-large"></i>添加图文
            </a>
            <a class="btn btn-primary submitbtn">
                <i class="icon-ok bigger-110"></i>保存
            </a>
            <hr/>
            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>内容标题</th>
                            <th>最后修改时间</th>
                            <th>序号</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${articleGroupDtoList}">
                            <tr>
                                <input type="hidden" name="existIds" value="${item.contentNode.id}"/>
                                <td>${item.contentNode.id}</td>
                                <td>${item.contentNode.title}</td>
                                <td>
                                    <fmt:formatDate value="${item.contentNode.lastModified}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td><input name="orderIndex_${item.contentNode.id}" type="text" value="${item.order}"
                                           class="required"/></td>
                                <td>
                                    <a href="${ctx}/articlegroup/remove.do?id=${item.node.id}&article=${item.contentNode.id}"
                                       title="移除" class="danger delete">
                                        <i class="icon-trash bigger-120"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    dialog_success = function (article_list) {
        X.showDialog("正在操作", "正在保存数据...", 'wait');
        $("#from").submit();
    }
</script>
</body>
</html>