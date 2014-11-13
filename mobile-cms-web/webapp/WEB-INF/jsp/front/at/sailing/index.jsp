<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>hello</title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/agent.css"/>
    <script type="text/javascript" src="${ctxRoot}/static/js/mSelect.js"></script>
</head>
<body>
<script>
    var mySelect = new mSelect('mySelect', '${ctxRoot}/static/css/mSelect.css');
    window.onload = function () {
        var aS = document.getElementsByTagName('select');
        for (var i = 0; i < aS.length; i++) {
            switch (aS[i].getAttribute('mSty')) {
                case 'redLine':
                    mySelect.Create(aS[i], 'redLine');
                    break;
                case 'blueCircle':
                    mySelect.Create(aS[i], 'blueCircle', true);
                    break;
                case 'orangeHeart':
                    mySelect.Create(aS[i], 'orangeHeart', true);
                    break;
            }
        }
    }
    function radio(obj) {
        $("input[type='radio']").each(function (i, value) {
            if ($(obj).val() != $(value).val()) {
                $(value).removeAttr("checked");
            } else {
                $(value).attr("checked", "checked");
            }
        });
    }
    $(function () {
        $("#select_one").bind("click", function () {
            if ($("input:radio[checked]").val() != 1 || ($("#sport").val() == "" && $("#port").val() == "")) {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findPortScope.do?sport=" + $("#sport").val() + "&port=" + $("#port").val() + "";
        });
        $("#select_two").bind("click", function () {
            if ($("input:radio[checked]").val() != 2 || $("#mort").val() == "") {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findPort.do?mort=" + $("#mort").val();
        });
        $("#select_three").bind("click", function () {
            if ($("input:radio[checked]").val() != 3 || ($("#fight").val() == "" && $("#ship").val() == "")) {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findShips.do?fight=" + $("#fight").val() + "&ship=" + $("#ship").val();
        });
    });
</script>
<div id="mytable"
     style="width: 100%;height: 100%;background: #ffffff;font-family: 'trebuchet MS', 'Lucida sans', Arial;font-size: 12px;color: #444;">
    <h2 style="background:#6699cc;color:#ffffff;">船期查询</h2>

    <div style="margin-bottom: 5px;">
        <div style="margin-bottom: 5px;">
            <div><input type="radio" value="1" id="one"
                        <c:if test="${type == 1}">checked="checked"</c:if> onclick="radio(this)">始发港至目的港查询
            </div>
            <div style="margin-left: 50px;">
                始发港:<input id="sport" type="text" value="${sport}"
                           style="width: 190px;border:1px solid #000000;height: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                目的港:<input id="port" type="text" value="${port}"
                           style="width: 190px;border:1px solid #000000;height: 20px;">
                <a href="#" id="select_one" style="width: 80px;float: right"
                   class="button white bigrounded">查询</a>
                <br clear="all"/>
            </div>
        </div>
        <div style="margin-bottom: 5px;">
            <div><input type="radio" value="2"
                        <c:if test="${type eq 2}">checked="checked" </c:if> onclick="radio(this)">港口查询
            </div>
            <div style="margin-left: 62px;">
                港口:<input id="mort" type="text" value="${mort}"
                          style="width: 190px;border:1px solid #000000;height: 20px;">
                <a href="#" id="select_two" style="width: 80px;float: right;"
                   class="button white bigrounded">查询</a>
                <br clear="all"/>
            </div>
        </div>
        <div>
            <div><input type="radio" value="3"
                        <c:if test="${type eq 3}">checked="checked" </c:if> onclick="radio(this)">船名,船名航次查询
            </div>
            <div style="margin-left: 62px;">
                船名:<input id="fight" type="text" value="${fight}"
                          style="width: 190px;border:1px solid #000000;height: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                航次:<input id="ship" type="text" value="${ship}"
                          style="width: 190px;border:1px solid #000000;height: 20px;">
                </select>
                <a href="#" id="select_three" style="width: 80px;float: right;"
                   class="button white bigrounded">查询</a>
                <br clear="all"/>
            </div>
        </div>
    </div>
    <table class="bordered">
        <caption></caption>
        <thead>
        <tr>
            <th scope="col">船名</th>
            <th scope="col">航次</th>
            <th scope="col">始发港</th>
            <th scope="col">始发港离港时间</th>
            <th scope="col">目的港</th>
            <th scope="col">目的港抵港时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sailing" items="${sailings}">
            <tr>
                <td class="row">${sailing.fight}</td>
                <td class="row">${sailing.ship}</td>
                <td class="row">${sailing.start}</td>
                <td class="row">${sailing.stime}</td>
                <td class="row">${sailing.end}</td>
                <td class="row">${sailing.etime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
