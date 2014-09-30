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
            编辑内容信息
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12">
                <form:form action="${ctx}/article/save.do" method="post" class="form-horizontal validate_frm"
                           enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">封面</form:label>
                        <div class="col-sm-10">
                            <input type="file" id="pic" class="col-sm-12 file" name="pic"
                                   width="225" height="125" accept="image/*" />
                        </div>
                        <div id="preview_pic">
                            <c:choose>
                                <c:when test="${not empty command.picPath}">
                                    <img src="${vfsRoot}${command.picPath}" id="img0" width="225" height="125">
                                </c:when>
                                <c:otherwise>
                                    <div class="center"><h2>请选择图片</h2></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">标题:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12 required" path="title" placeholder="标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="authorName" class="col-sm-2 control-label no-padding-right">作者:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12" path="authorName" placeholder="作者"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="sourceUrl" class="col-sm-2 control-label no-padding-right">原文地址:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12" path="sourceUrl" placeholder="原文地址"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="content" class="col-sm-2 control-label no-padding-right">描述:</form:label>
                        <div class="col-sm-10">
                            <form:textarea class="col-sm-12" rows="5" path="summary"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="content" class="col-sm-2 control-label no-padding-right">内容:</form:label>
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
    </div>
</div>

</body>
</html>