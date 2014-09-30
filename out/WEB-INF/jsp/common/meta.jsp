
<!-- basic styles -->
<link href="${ctxRoot}/static/framework/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/jstree/style.css" />
<style type="text/css">
    /*fix bootstarp*/
    body.modal-open{
        margin: 0px;
    }
</style>
<!--[if IE 7]>
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/font-awesome-ie7.min.css" />
<![endif]-->
<!-- fonts -->
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/ace-fonts.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/ace.min.css" />
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/ace-rtl.min.css" />
<!--[if lte IE 8]>
<link rel="stylesheet" href="${ctxRoot}/static/framework/css/ace-ie.min.css" />
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${ctxRoot}/static/framework/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${ctxRoot}/static/framework/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${ctxRoot}/static/framework/js/bootstrap.min.js"></script>
<script src="${ctxRoot}/static/framework/js/typeahead-bs2.min.js"></script>
<script src="${ctxRoot}/static/framework/js/date-time/bootstrap-datetimepicker.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${ctxRoot}/static/framework/js/excanvas.min.js"></script>
<![endif]-->
<script src="${ctxRoot}/static/framework/js/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctxRoot}/static/framework/js/jquery-validation/localization/messages_zh.js"></script>

<!-- ace scripts -->
<script src="${ctxRoot}/static/framework/js/ace.min.js"></script>
<script src="${ctxRoot}/static/framework/js/ace-elements.min.js"></script>
<script src="${ctxRoot}/static/framework/js/ace-extra.min.js"></script>
<script src="${ctxRoot}/static/js/plugin.js"></script>
<script src="${ctxRoot}/static/framework/js/jstree.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="${ctxRoot}/static/framework/js/html5shiv.js"></script>
<script src="${ctxRoot}/static/framework/js/respond.min.js"></script>
<![endif]-->
<script src="${ctxRoot}/static/ckeditor/ckeditor.js"></script>
<script src="${ctxRoot}/static/ckeditor/adapters/jquery.js"></script>
<script src="${ctxRoot}/static/ckfinder/ckfinder.js"></script>
<script>
    var CTX = "${ctx}";
    var CTX_ROOT = "${ctxRoot}";
</script>