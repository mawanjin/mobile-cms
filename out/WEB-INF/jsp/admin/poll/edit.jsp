<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script src="${ctxRoot}/static/ckeditor/ckeditor.js"></script>
    <script src="${ctxRoot}/static/ckeditor/adapters/jquery.js"></script>
</head>
<body>
<div class="page-header">
    <h1>
        投票管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑投票信息
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12">
                <form:form action="${ctx}/poll/save.do" method="post" class="form-horizontal validate_frm"
                           enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">图片:</form:label>
                        <div class="col-sm-10">
                            <input type="file" id="pic" class="col-sm-12 file" name="pic"
                                   width="225" height="125"/>
                        </div>
                        <div id="preview_pic">
                            <c:choose>
                                <c:when test="${not empty command.picPath}">
                                    <img src="${vfsRoot}${command.picPath}" id="img0" width="225" height="125">
                                </c:when>
                                <c:otherwise>
                                    <div class="left"><h2>请选择图片</h2></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="col-sm-2 control-label  no-padding-right">投票标题:</label>
                        <div class="col-sm-10">
                            <form:input class="input-large required" path="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label  no-padding-right">类型:</label>
                        <div class="col-sm-10">
                            <form:select path="type" cssClass="input-large">
                                <form:options items="${typeItems}" itemLabel="text"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label  no-padding-right">状态:</label>
                        <div class="col-sm-10">
                            <form:select path="status" cssClass="input-large">
                                <form:options items="${statusItems}" itemLabel="text"/>
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label  no-padding-right">开始时间:</label>
                        <div class="col-sm-10 input-append date form_datetime_minute">
                            <form:input class="input-large required" path="startTime"/>
                            <span class="add-on"><i class="icon-remove"></i></span>
                            <span class="add-on"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label  no-padding-right">开始时间:</label>
                        <div class="col-sm-10 input-append date form_datetime_minute">
                            <form:input class="input-large required" path="endTime"/>
                            <span class="add-on"><i class="icon-remove"></i></span>
                            <span class="add-on"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="summary" class="col-sm-2 control-label no-padding-right">描述:</form:label>
                        <div class="col-sm-10">
                            <form:textarea class="col-sm-12" rows="5" path="summary"/>
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