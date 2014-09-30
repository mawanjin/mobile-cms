<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/cms.css"/>
    <script src="${ctxRoot}/static/tinymce/tinymce.min.js"></script>
    <script>
        $(function () {
            $("#file").change(function () {
                var objUrl = getObjectURL(this.files[0]);
                if (objUrl) {
                    $("#img0").attr("src", objUrl);
                    $("#pic").html('<img src="' + objUrl + '" id="img0" class=" img-responsive img-thumbnail">');
                }
            });
        });

        function getObjectURL(file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }
    </script>
</head>
<body>
<div class="page-header">
    <h1>
        广告管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑广告内容
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-md-3" id="pic">
                <c:choose>
                    <c:when test="${not empty command.pic}">
                        <img src="${ctxPic}/${command.pic}" id="img0" class=" img-responsive img-thumbnail">
                    </c:when>
                    <c:otherwise>
                        <div class="center"><h2>请选择图片</h2></div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="col-md-9">
                <form:form action="${ctx}/ad/save.do" method="post" class="form-horizontal validate_frm"
                           enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">图片</form:label>
                        <div class="col-sm-10">
                            <input type="file" id="file" class="col-sm-12
                            <c:if test="${empty command.pic}"> required</c:if>" name="file"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">标题:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12 required" path="title" placeholder="标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="picUrl" class="col-sm-2 control-label no-padding-right">图片链接地址:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12 required" path="picUrl" placeholder="http://www.baidu.com"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="description" class="col-sm-2 control-label no-padding-right">描述:</form:label>
                        <div class="col-sm-10">
                            <form:textarea class="col-sm-12" rows="5" path="description"/>
                        </div>
                    </div>

                    <div class="clearfix ">
                        <input type="submit" id="submit" style="display: none"/>

                        <div class=" col-md-9">
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
