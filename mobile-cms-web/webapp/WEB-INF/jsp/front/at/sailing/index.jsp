<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <%--<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no"/>--%>
    <title>hello</title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/saling.css"/>
    <script type="text/javascript" src="${ctxRoot}/static/js/mSelect.js"></script>
</head>
<body>
<script>
    var _type = "${type}";
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

        if(_type==1){
            $("#result").show();
            $("#typeContainer").hide();
            $("#content").show();
            $("#c1").show();
            $("#c2").hide();
            $("#c3").hide();
        }else if(_type==2){
            $("#result").show();
            $("#typeContainer").hide();
            $("#content").show();$("#c2").show();
            $("#c1").hide();
            $("#c3").hide();
        }else if(_type==3){
            $("#result").show();
            $("#typeContainer").hide();
            $("#content").show();$("#c3").show();
            $("#c1").hide();
            $("#c2").hide();
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

        //发起查询请求
        $("#select_one").bind("click", function () {
            if (($("#sport").val() == "" && $("#port").val() == "")) {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findPortScope.do?sport=" + $("#sport").val() + "&port=" + $("#port").val() + "";
        });
        $("#select_two").bind("click", function () {
            if ($("#mort").val() == "") {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findPort.do?mort=" + $("#mort").val();
        });
        $("#select_three").bind("click", function () {
            if (($("#fight").val() == "" && $("#ship").val() == "")) {
                return;
            }
            location.href = "${ctxRoot}/front/at/sailing/findShips.do?fight=" + $("#fight").val() + "&ship=" + $("#ship").val();
        });

        //类型选择
        $("#type1").bind("click", function () {
            $("#typeContainer").hide();
            $("#content").show();
            $("#c1").show();
            $("#c2").hide();
            $("#c3").hide();
        });

        $("#type2").bind("click", function () {
            $("#typeContainer").hide();
            $("#content").show();
            $("#c1").hide();
            $("#c2").show();
            $("#c3").hide();
        });

        $("#type3").bind("click", function () {
            $("#typeContainer").hide();
            $("#content").show();
            $("#c1").hide();
            $("#c2").hide();
            $("#c3").show();
        });

        $("#list").bind("click", function () {
            $("#typeContainer").show();
            $("#content").hide();
            $("#c1").hide();
            $("#c2").hide();
            $("#c3").hide();
            $("#result").hide();
        });



    });



</script>


<div id="mytable"
     style="width: 100%;height: 100%;background: #ffffff;font-family: 'trebuchet MS', 'Lucida sans', Arial;font-size: 12px;color: #444;">
    <div style="background:#6699cc;color:#ffffff;padding-top:10px;padding-bottom: 10px;padding-left: 5px;margin-bottom: 10px;">
        <table width="100%"><tr><td width="50%"><h2>船期查询</h2></td><td align="right"><h4 id="list">返回列表&nbsp;&nbsp;&nbsp;</h4></td></tr></table>
    </div>



    <!--类型选择-->
    <div id="typeContainer">
        <div style="margin-bottom: 5px;margin-top: 30px;">
            <div id="type1" style="width: 100%;text-align: center;margin-bottom: 10px;"><input type="button" class="btnTypeOne" value="始发港至目的港查询" /></div>
            <div id="type2" style="width: 100%;text-align: center;margin-bottom: 10px;"><input type="button" class="btnTypeTwo" value="港口查询" /></div>
            <div id="type3" style="width: 100%;text-align: center;"><input type="button" class="btnTypeThree" value="船名-航次查询" /></div>
        </div>
    </div>


    <div id="content" style="margin-bottom: 5px;margin-top:40px;display: none;padding-left: 10px;">
        <div id="c1" style="margin-bottom: 5px;">
            <div ><input type="radio" value="1" id="one"
                         <c:if test="${type == 1}">checked="checked"</c:if> onclick="radio(this)" disabled="disabled" style="margin-right: 10px;margin-top: 5px;margin-bottom: 5px;">始发港至目的港查询
            </div>
            <div style="margin-left: 50px;">
                始发港:&nbsp;
                <%--<input id="sport" type="text" value="${sport}"--%>
                                 <%--style="width: 190px;border:1px solid #000000;height: 25px;margin-bottom: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>

                <select id="sport" style="width: 190px;" >
                    <c:forEach var="agent" items="${agents}">

                        <option value="${agent.agentName}" <c:if test="${agent.agentName eq sport}">selected="selected"</c:if>>${agent.agentName}</option>
                    </c:forEach>
                </select>

                <br><br>
                目的港:&nbsp;
                <%--<input id="port" type="text" value="${port}"--%>
                                 <%--style="width: 190px;border:1px solid #000000;height: 25px;margin-bottom: 10px;">--%>
                <select id="port" style="width: 190px;" >
                    <c:forEach var="agent" items="${agents}">
                        <option value="${agent.agentName}" <c:if test="${agent.agentName eq port}">selected="selected"</c:if> >${agent.agentName}</option>
                    </c:forEach>
                </select>
                <br>          <br>
                <div  id="select_one" style="width: 80px;"
                   class="button white bigrounded">查询</div>
                <br clear="all"/>
            </div>
        </div>
        <div id="c2" style="margin-bottom: 5px;">
            <div><input type="radio" value="2"
                        <c:if test="${type eq 2}">checked="checked" </c:if> onclick="radio(this)" disabled="disabled" style="margin-right: 10px;margin-top: 5px;margin-bottom: 5px;">港口查询
            </div>
            <div style="margin-left: 62px;">
                港口:&nbsp;

                <select id="mort" style="width: 190px;" >
                <c:forEach var="agent" items="${agents}">
                    <option value="${agent.agentName}" <c:if test="${agent.agentName eq mport}">selected="selected"</c:if> >${agent.agentName}</option>
                </c:forEach>
                </select>
                <br><br>
                <div  id="select_two" style="width: 80px;"
                   class="button white bigrounded">查询</div>
                <br clear="all"/>
            </div>
        </div>
        <div id="c3">
            <div><input type="radio" value="3"
                        <c:if test="${type eq 3}">checked="checked" </c:if> onclick="radio(this)" disabled="disabled" style="margin-right: 10px;">船名-航次查询
            </div>
            <div style="margin-left: 62px;">
                航名:<input id="ship" type="text" value="${ship}"
                          style="width: 190px;border:1px solid #000000;height: 25px;margin-bottom: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <br>
                船次:<input id="fight" type="text" value="${fight}"
                          style="width: 190px;border:1px solid #000000;height: 25px;margin-bottom: 10px;">
                </select>
                <br>
                <div  id="select_three" style="width: 80px;"
                   class="button white bigrounded">查询</div>
                <br clear="all"/>
            </div>
        </div>
    </div>
    <br>
    <div id="result">

    <c:if test="${not empty hint}">
        <div>未找到符合条件的记录</div>
    </c:if>

    <c:if test="${not empty sailings}">
        <div>*长江片区货物统一由上海中转</div>
    </c:if>

    <c:forEach var="sailing" items="${sailings}">
        <ul>
            <li class="red">船名:&nbsp;&nbsp;${sailing.ship}</li>
            <li class="row">航次:&nbsp;&nbsp;${sailing.fight}</li>
            <li class="row">始发港:&nbsp;&nbsp;${sailing.start}</li>
            <li class="row">离港时间:&nbsp;&nbsp;${sailing.stime}</li>
            <li class="row">目的港:&nbsp;&nbsp;${sailing.end}</li>
            <li class="row">抵港时间:&nbsp;&nbsp;${sailing.etime}</li>
        </ul>
    </c:forEach>
    </div>

    <%--<table id="result" class="bordered" style="display: none;">--%>
    <%--<caption></caption>--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th scope="col">航次</th>--%>
    <%--<th scope="col">船名</th>--%>
    <%--<th scope="col">始发</th>--%>
    <%--<th scope="col">离港</th>--%>
    <%--<th scope="col">目的</th>--%>
    <%--<th scope="col">抵港</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>

    <%--&lt;%&ndash;<c:forEach var="sailing" items="${sailings}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.fight}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.ship}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.start}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.stime}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.end}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="row">${sailing.etime}</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>



    <%--</tbody>--%>
    <%--</table>--%>
</div>
</body>
</html>
