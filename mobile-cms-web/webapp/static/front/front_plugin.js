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

window.x_init_hook_util = function () {
    $(".validate_frm").validate();
    $(".validateForm").validate();
};

window.x_init_hook_button = function () {
    $(".btn_a").click(function () {
        window.location.href = $(this).attr("href");
    });
    $(".weixin_close").click(function () {
        WeixinJSBridge.invoke('closeWindow', {}, function (res) {
        });
    });
    //发送验证码
    $(".send").on("click", function () {
        var mobilePhone = $("#mobilePhone").val();
        if (checkPhone(mobilePhone)) {
            $(this).css({display: "none"});
            var url = $(this).attr("href");
            var second = 60;
            $(".rest_second").html(second + "秒后重发");
            $(".idcode").css({display: "block"});
            var restSecond = setInterval(function () {
                if (second == 0) {
                    clearInterval(restSecond);
                    $(".send").css({display: "block"});
                    $(".idcode").css({display: "none"});
                    return;
                }
                second = second - 1;
                $(".rest_second").html(second + "秒后重发");
            }, 1000);
            X.get(url, {"mobilePhone": mobilePhone}, function (data) {

                console.info(data);
            });
            return;
        }
        $.blockUI({ message: $('.exc_inforbox') });
        event.preventDefault();
    });
    //页面验证
    $(".login_form").submit(function (event) {
        if (checkPhone($("#mobilePhone").val())) {
            if (checkPassword($("#password").val())) {
                return true;
            }
        }

        $.blockUI({ message: $('.exc_inforbox') });
        event.preventDefault();
    });
    $(".reg_form").submit(function (event) {
        if (checkPhone($("#mobilePhone").val())) {
            if (checkPassword($("#password").val())) {
                if (checkRepassword($("#password").val(), $("#repassword").val())) {
                    if (checkVerifyCode($("#verifyCode").val())) {
                        var agreeReg = $("#agreeReg:checked").length;
                        if (1 == agreeReg) {
                            return true;
                        }
                        $("#info_message").html("请同意《签个到用户注册协议》");
                    }
                }
            }
        }

        $.blockUI({ message: $('.exc_inforbox') });
        event.preventDefault();
    });
    //流量申请验证
    $(".apply_form").submit(function () {
        var availableTraffic = $("#availableTraffic").val();
        var applyTraffic = $("#text_box").val();
        if (applyTraffic <= availableTraffic) {
            return true;
        }
        $("#info_message").html("可申请流量不足");
        $.blockUI({ message: $('.exc_inforbox') });
        event.preventDefault();
    });

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


jQuery(document).ready(X.hook);

function checkPhone(mobilePhone) {
    var info_message = "";
    if (!mobilePhone) {
        info_message = "请输入手机号";
    } else if (!mobilePhone.match(/1\d{10}/g)) {
        info_message = "手机号有误";
    }
    if (info_message === "") {
        return true;
    }
    $("#info_message").html(info_message);
    return false;
}
function checkPassword(password) {
    var info_message = "";
    var reg = /^[0-9a-zA-Z]{6,}$/;
    if (!password) {
        info_message = "请输入密码";
    } else if (password.search(reg) == "-1") {
        info_message = "密码只能是字母数字（至少6位）";
    }
    if (info_message === "") {
        return true;
    }
    $("#info_message").html(info_message);
    return false;
}
function checkRepassword(password, repassword) {
    var info_message = "";
    if (password == repassword) {
        return true;
    }
    info_message = "两次密码不一致"
    $("#info_message").html(info_message);
    return false;
}
function checkVerifyCode(code) {
    var info_message = "";
    if (code) {
        return true;
    }
    info_message = "请输入验证码";
    $("#info_message").html(info_message);
}