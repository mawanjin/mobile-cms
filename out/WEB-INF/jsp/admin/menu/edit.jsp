<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="page-header">
    <h1>
        微信菜单管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑菜单
        </small>
    </h1>
</div>

<div class="content">
    <div class="row">
        <div class="col-xs-12">
            <%@include file="/WEB-INF/jsp/common/message.jsp" %>
            <form:form id="primary" action="${ctx}/menu/save.do" method="post" class="form-horizontal validate_frm">
                <form:hidden path="id"/>
                <form:hidden path="value"/>
                <form:hidden path="dlgResult"/>
                <c:if test="${not empty parentMenu}">
                    <div class="form-group">
                        <label class="col-sm-4 control-label  no-padding-right">父菜单:</label>

                        <div class="col-sm-8">
                            <form:select path="parentId">
                                <form:options items="${menuItems}" itemLabel="name" itemValue="id"/>
                            </form:select>
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-sm-4 control-label  no-padding-right">菜单名称:</label>

                    <div class="col-sm-8">
                        <form:input class="input-large required" path="name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label  no-padding-right">类型:</label>

                    <div class="col-sm-8">
                        <form:select path="type">
                            <form:options items="${menuTypes}" itemLabel="text"/>
                        </form:select>
                    </div>
                </div>

                <div class="form-group button_type" id="about_View">
                    <label class="control-label col-xs-12 col-sm-4 no-padding-right">链接:</label>

                    <div class="col-xs-12 col-sm-8">
                        <div class="clearfix">
                            <input class="input-xxlarge required" name="View" id="View"/>
                        </div>
                    </div>
                </div>
                <div class="form-group button_type" id="about_Click">
                    <label class="control-label col-xs-12 col-sm-4 no-padding-right">关键字:</label>

                    <div class="col-xs-12 col-sm-8">
                        <div class="clearfix">
                            <input class="input-large required" name="Click" id="Click"/>
                        </div>
                    </div>
                </div>

                <div class="form-group button_type" id="about_CMS">
                    <label class="control-label col-xs-12 col-sm-4 no-padding-right">内容:</label>

                    <div class="col-xs-12 col-sm-8">
                        <div class="clearfix">
                            <a class="ajax btn icon-hand-up"
                               href="${ctx}/cms/dialog.do?childAjax=false&multi=false&existIds=${command.dlgResult}">
                                选择
                            </a>
                            <span id="article_title">${command.articleTitle}</span>
                        </div>
                    </div>
                </div>

                <div class="clearfix form-actions">
                    <input type="submit" id="submit" style="display: none"/>

                    <div class="col-md-offset-9 col-md-3">
                        <a class="btn btn-primary submitbtn">
                            <i class="icon-save icon-large"></i>&nbsp;&nbsp;提交
                        </a>
                        <a class="btn history" type="button">
                            <i class="icon-arrow-left icon-large"></i>&nbsp;&nbsp;取消
                        </a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        function switch_select(select) {
            $("div[id^=about]").hide();
            $("#about_" + select).show();
            $("#" + select).val($("#value").val());
        }

        switch_select($("#type").val());
        $("#type").change(function () {
            switch_select($(this).val());
        });
        $("#primary").submit(function () {
            var val = $("#type").val();
            $("#value").val($("#" + val).val());
            return true;
        });
        dialog_success = function (article_list) {
            $("#article_title").html(article_list[0].title);
        };
    });
</script>
</body>
</html>