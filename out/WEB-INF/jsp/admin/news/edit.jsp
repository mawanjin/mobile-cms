<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/cms.css"/>
    <script src="${ctxRoot}/static/tinymce/tinymce.min.js"></script>
</head>
<body>
<div class="page-header">
    <h1>
        新闻编辑
        <small>
            <i class="icon-double-angle-right"></i>
            编辑内容
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/news/save.do" method="post" class="form-horizontal validate_frm">
            <form:hidden path="id"/>
            <div class="form-group">
                <form:label path="title" class="col-sm-2 control-label no-padding-right">标题:</form:label>
                <div class="col-sm-10">
                    <form:input class="col-sm-12 required" path="title"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="content" class="col-sm-2 control-label no-padding-right">具体内容:</form:label>
                <div class="col-sm-10">
                    <form:textarea class="col-sm-12 rich_article" rows="20" path="content"/>
                </div>
            </div>

            <div class="clearfix form-actions">
                <input type="submit" id="submit" style="display: none"/>

                <div class="col-md-offset-9 col-md-3">
                    <a class="btn btn-primary submitbtn">
                        <i class="icon-ok bigger-110"></i>保存
                    </a>
                    &nbsp;
                    <a class="btn history" type="button">
                        <i class="icon-arrow-left bigger-110"></i>返回
                    </a>
                </div>
            </div>

        </form:form>
    </div>
</div>

</body>
</html>