<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script>
        $(function () {
            $("form").submit(function (event) {
                var title = $("#title").val();
                var description = $("#description").val();
                var filterKeys = /[javascript|script]/;
                if (filterKeys.test(title)) {
                    alert("标题含有特殊字符");
                    event.preventDefault();
                }
                if (filterKeys.test(description)) {
                    alert("描述中含有特殊字符");
                    event.preventDefault();
                }
                return true;
            });
        });
    </script>
</head>
<body>
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑商品
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-md-3" id="preview_picFile">
                <c:choose>
                    <c:when test="${not empty command.picFilePath}">
                        <img src="${vfsRoot}/${command.picFilePath}" id="img0" class=" img-responsive img-thumbnail">
                    </c:when>
                    <c:otherwise>
                        <div class="center"><h2>请选择图片</h2></div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="col-md-9">
                <form:form action="${ctx}/product/save.do" method="post" class="form-horizontal validate_frm"
                           enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">图片</form:label>
                        <div class="col-sm-10">
                            <form:input type="file" path="picFile" accept="image/*" class="col-sm-12"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">名称:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12 required" id="title" path="title" placeholder="名称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="price" class="col-sm-2 control-label no-padding-right">价格:</form:label>
                        <div class="col-sm-10">
                            <form:input class="col-sm-12 required" path="price" placeholder="2"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="title" class="col-sm-2 control-label no-padding-right">状态:</form:label>
                        <div class="col-sm-10">
                            <form:select path="status" class="col-sm-12 required" items="${productStatus}"
                                         itemLabel="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="description"
                                    class="col-sm-2 control-label no-padding-right">描述:</form:label>
                        <div class="col-sm-10">
                            <form:textarea class="col-sm-12" id="description" rows="5" path="description"/>
                        </div>
                    </div>

                    <div class="clearfix ">
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