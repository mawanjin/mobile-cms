String.prototype.format = function () {
    var s = this,
        i = arguments.length;

    while (i--) {
        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
    }
    return s;
};

/**********************************************************************************************************************
 *
 *                                            o(╯□╰)o华丽的分割线o(╯□╰)o X插件
 *
 *********************************************************************************************************************/
var X = {};
X.G = {};
X.hook = function () {
    var pre_init_str = 'x_init_hook_';
    for (var h in window) {
        if (0 != h.indexOf(pre_init_str))
            continue;
        var func = window[h];
        if (typeof func == 'function') {
            try {
                func();
            } catch (e) {
            }
        }
    }
};

X.ajaxForm = function (ajaxFrm) {
    var param = {};
    var frmArray = ajaxFrm.serializeArray();
    jQuery.each(frmArray, function (index, li) {
        param[li.name] = li.value;
    });
    jQuery.ajax({
        url: ajaxFrm.attr("action"),
        cache: false,
        data: param,
        type: "POST",
        dataType: "json",
        success: X.json
    });
    return false;
};

X.get = function (u, data, callback) {
    return X.ajax(u, 'GET', data, callback);
};
X.post = function (u, data, callback) {
    return X.ajax(u, 'POST', data, callback);
};
X.ajax = function (u, method, data, callback) {
    jQuery.ajax({
        url: u,
        cache: false,
        type: method,
        dataType: "json",
        data: data,
        success: callback || X.json
    });
    return false;
};

X.json = function (r) {
    var type = r['type'];
    var data = r['data'];
    if (X.G.task_dialog && type != "task_dialog") {
        window.clearInterval(X.G.task_dialog);
        X.G.task_dialog = undefined;
    }
    if (type == "alert") {
        X.showDialog(data.title, data.message, data.type);
        //重新加载当前页面
        $(".modal").on('hidden', function () {
            window.location.reload();
        });
    } else if (type == 'refresh') {
        if (data) {
            alert(data);
        }
        window.location.reload();
    } else if (type == 'updater') {
        var id = data['id'];
        var inner = data['html'];
        jQuery('#' + id).html(inner);
    } else if (type == 'dialog') {
        var width = data['width'] || 0;
        var height = data['height'] || 0;
        var html = data['html'] || "";
        X.boxShow(html, height, width);
    } else if (type == 'redirect') {
        window.location.href = data;
    } else if (type == 'data') {
        if (data) {
            eval(data);
        }
    } else if (!X.G.task_dialog && type == "task_dialog") {
        X.showDialog("正在操作", "正在进行操作请稍后...", 'wait');
        var task_id = data["id"];
        var times = data["times"] || 2000;
        X.G.task_dialog = window.setInterval(function () {
            X.post(CTX_ROOT + "/admin/task/status.do", {"id": task_id});
        }, times);
    }
};

X.boxShow = function (innerHTML, height, width) {

    var dialog = jQuery("#append_parent_child");
    if (dialog.length == 0) {
        //移除上个对话框的遮罩层 bootstrap bug
        $(".modal-backdrop").remove();
        dialog = jQuery("#append_parent");
    }
    dialog.html(innerHTML);
    $(".modal .modal-dialog").css('margin-top', '100px');
    if (width) {
        $(".modal").css("width", width);
        $(".modal").css("margin-left", function () {
            return -($(this).width() / 2);
        });
    }
    if (height) {
        $(".modal-body").css("max-height", height);

    }
    $(".modal").modal({show: true});
    $(".modal").on("hidden.bs.modal", function (e) {
        if (dialog.length != 0) {
            jQuery("#append_parent_child").remove();
        }
    });
    X.hook();
    return true;
};

X.showDialog = function (title, content, type) {
    var dialog_header = '<button type="button" class="close" data-dismiss="modal">&times;</button>';
    var dialog_html = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">{0}<h4 class="modal-title blue bigger">{1}</h4></div>' +
        '<div class="modal-body" style="font-size:14px;">{2}</div>{3}</div></div></div>';
    if (type.trim() == "wait") {
        dialog_html = dialog_html.format("", title,
            '<i class="icon-spinner icon-spin green bigger-125"></i>' + content, "");
    } else if (type.trim() == "error") {
        dialog_html = dialog_html.format(dialog_header, title,
            '<i class="icon-bolt red bigger-125"></i>&nbsp;&nbsp;' + content, "");
    } else {
        var footer = '<div class="modal-footer">' +
            '<button type="button" class="btn btn-primary" onclick="window.location.reload();">确定</button></div>';
        dialog_html = dialog_html.format(dialog_header, title, '<i class="icon-ok green bigger-125"></i>' + content, footer);
    }
    X.boxShow(dialog_html);
};

X.loadEditor = function () {
    $(".rich_article").ckeditor({
        fullPage: true,
        filebrowserBrowseUrl :  CTX_ROOT+'/static/ckfinder/ckfinder.html',
        filebrowserImageBrowseUrl : CTX_ROOT+'/static/ckfinder/ckfinder.html?type=Images',
        filebrowserFlashBrowseUrl :CTX_ROOT+ '/static/ckfinder/ckfinder.html?type=Flash',
        filebrowserUploadUrl : CTX_ROOT+'/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
        filebrowserImageUploadUrl :CTX_ROOT+ '/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
        filebrowserFlashUploadUrl :CTX_ROOT+ '/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'

    });
    $(".rich_simple").ckeditor({
        toolbar: [
            { name: 'document', items: [ 'Source'] },
            { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
            '/',
            { name: 'links', items: [ 'Link', 'Unlink'] }

        ],
        forcePasteAsPlainText: true,
        enterMode: CKEDITOR.ENTER_BR});
    var editor = $('.rich_article').ckeditor().editor;
    CKFinder.setupCKEditor(editor, CTX_ROOT + "/static/ckfinder/");
};

window.x_init_hook_click = function () {
    jQuery('a.delete').unbind('click').click(function () {
        var u = jQuery(this).attr('href');
        if (confirm('该操作无法恢复，您确定要这么做吗？')) {
            X.get(u);
            return false;
        } else {
            return false;
        }
    });

    jQuery('a.ajax').unbind('click').click(function () {
        var ask = jQuery(this).attr('ask');
        if (ask && !confirm(ask)) {
            return false;
        }
        var is_modal = jQuery(this).hasClass("show_modal");
        if (is_modal) {
            X.showDialog("正在操作", "正在进行操作请稍后...", 'wait');
        }
        X.get(jQuery(this).attr('href'));
        return false;
    });

    $('table th input:checkbox').on('click', function () {
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });

    });

    $(".form_datetime_minute").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        startDate: new Date(),
        minuteStep: 10,
        pickerPosition: "bottom-left"
    });

    $(".nav-tabs li a.nav_tab_filter").unbind('click').click(function () {
        var targetInput = $(this).closest("ul.nav-tabs").attr("_target_input");
        var targetInputValue = $(this).attr('href');
        var form = $(this).closest("form");
        form.children("input[name='" + targetInput + "']").val(targetInputValue);
        form.submit();
        return false;
    });
};

window.x_init_hook_util = function () {
    $(".validate_frm").validate();
    $(".validateForm").validate();
    X.loadEditor();
};

window.x_init_hook_button = function () {
    jQuery('a.submitbtn').unbind('click').click(function () {
        var form = jQuery(this).parents('form:first');
        if (form.hasClass("ajaxForm")) {
            return X.ajaxForm(form);
        }
        var submit = jQuery("input[type=submit]", form);
        //找到提交按钮则触发提交按钮的click否则则直接form.submit
        if (submit.length != 0) {
            submit.trigger("click");
        } else {
            form.submit();
        }
        return false;
    });

    jQuery("a.history").unbind("click").click(function () {
        history.back();
    });
    jQuery(".jstree").jstree().bind("select_node.jstree", function (event, data) {
        window.location.href = data.node.a_attr.href
    });

    $("input:file").change(function () {
        var objUrl = null;
        var file = this.files[0];
        if (window.createObjectURL != undefined) {
            objUrl = window.createObjectURL(file);
        } else if (window.URL != undefined) {
            objUrl = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            objUrl = window.webkitURL.createObjectURL(file);
        }
        if (objUrl) {
            var width = $(this).attr("width");
            var height = $(this).attr("height");
            $("#preview_" + $(this).attr("id")).html('<img src="' + objUrl
                + '" id="img0" width="' + width + '" height="' + height + '">');
        }
    });
};

jQuery(document).ready(X.hook);