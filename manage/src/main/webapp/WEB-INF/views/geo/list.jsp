<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<manage:page title="geo信息维护">
     <jsp:attribute name="script">
            <script type="text/javascript">
                var global = "global";
                var region = "region";
                var province = "province";
                var city = "city";
                var district = "district";
                var business = "business";
                var tree = $('#jsTree').jstree({
                    core: {
                        check_callback: true,
                        data: [
                            {
                                "id": "1_global",
                                "text": "全国",
                                "parent": "#"
                            },
                            <c:forEach var="region" items="${regions}">
                            {
                                "id": "${region.id}_region",
                                "parent": "1_global",
                                "text": "${region.text}"
                            },
                            </c:forEach>
                            <c:forEach var="province" items="${provinces}">
                            {
                                "id": "${province.id}_province",
                                "parent": "${province.parent}_region",
                                "text": "${province.text}"
                            },
                            </c:forEach>
                            <c:forEach var="city" items="${cities}">
                            {
                                "id": "${city.id}_city",
                                "parent": "${city.parent}_province",
                                "text": "${city.text}"
                            },
                            </c:forEach>
                            <c:forEach var="district" items="${districts}">
                            {
                                "id": "${district.id}_district",
                                "parent": "${district.parent}_city",
                                "text": "${district.text}"
                            },
                            </c:forEach>
                            <c:forEach var="business" items="${businesses}">
                            {
                                "id": "${business.id}_business",
                                "parent": "${business.parent}_district",
                                "text": "${business.text}"
                            },
                            </c:forEach>
                        ]
                    },
                    plugins: ["wholerow", "contextmenu"],
                    "contextmenu": {
                        "items": {
                            "create": null,
                            "rename": null,
                            "remove": null,
                            "ccp": null,
                        }
                    }
                }).on("ready.jstree", function () {
                    $(".btn-newNode").hide();
                    $(".deletNode").hide();
                }).bind('click.jstree', function (event) {
                    var eventNodeName = event.target.nodeName;
                    if (eventNodeName == 'INS') {
                        ;
                    } else if (eventNodeName == 'A') {
                        getInfo($(event.target).parents('li').attr('id'));
                    }
                });
                function getInfo(idString) {
                    var id = idString.split("_")[0];
                    var type = idString.split("_")[1];
                    $.post("geo/getChildInfoByIdandType.do?id=" + id + "&type=" + type, function (result) {
                        var str = "<tbody class='geoInfo'>";
                        $.each(result, function (i, item) {
                            str += "<tr>";
                            str += "<td>" + item.name + "</td>";
                            if (item.status == 10) {

                                str += "<td>正常</td>";
                            } else {
                                str += "<td>已删除</td>";
                            }
                            str += "<td><button class='button' onclick='deleteGeo(" + item.id + "," + item.voType + ")'>删除</button></td>";
                            str += "</tr>";
                        });
                        str += "</tbody>";
                        $(".geoInfo").remove();
                        $("#table").append(str);
                    });
                    $.post("geo/getInfoByIdandType.do?id=" + id + "&type=" + type, function (result) {
                        $.each(result, function (i, item) {
                            $("#geoInfoId").val(item.id);
                            $("#geoInfoType").val(item.voType);
                            $("#geoInfoCode").val(item.code);
                            $("#geoInfoIdx").val(item.idx);
                            $("#geoInfoWeight").val(item.weight);
                            $("#geoInfoName").val(item.name);
                            $("#geoInfoBDName").val(item.baiduName);
                            $("#geoInfoDesc").val(item.description);
                            $("#geoInfoParentName").val(item.parentName);
                            $("#geoInfoStatus").val(item.status);
                            showbtnupdate(item.voType);
                        });
                    });
                }
                function showbtnupdate(type) {
                    $(".deletNode").show();
                    $(".btnUpdate").show();
                    if (type.toString() == city || type.toString() == district) {
                        $(".btn-newNode").show();
                    } else {
                        $(".btn-newNode").hide();
                    }
                }
                function deleteGeo(id, type) {
                    if (confirm("您确认要删除本节点吗？")) {
                        $.post("geo/deleteGeoInfo.do?id=" + id + "&type=" + type,
                                function (result) {
                                    if (result) {
                                        alert("删除成功！");
                                        window.location.href = "geo.do";
                                    } else {
                                        alert("删除失败请重试！")
                                    }
                                });
                    } else {
                        return;
                    }
                }
                function addChildGeo(id, code, idx, weight, name, bdName, parentId, type) {
                    $.post("geo/addChildGeo.do", {
                        id: id,
                        code: code,
                        idx: idx,
                        weight: weight,
                        name: name,
                        baiduName: bdName,
                        parentId: parentId,
                        voType: type
                    }, function (result) {
                        if (result) {
                            alert("创建成功！");
                            window.location.href = "geo.do";
                        } else {
                            alert("创建失败请重试！")
                        }
                    });
                }
                function addChild() {
                    var id = $("#geoInfoId").val();
                    var type = $("#geoInfoType").val();
                    if (id === "") {
                        return;
                    }
                    $("#addChildParentType").val(type);
                    $("#addChildParentId").val(id);
                    $(".add-child").modal("show");
                }
                $(".deletNode").bind("click", function () {
                    var id = $("#geoInfoId").val();
                    var type = $("#geoInfoType").val();
                    deleteGeo(id, type);
                });
                $(".confirm-add-Child").bind("click", function () {
                    var id = $("#addChildId").val();
                    var code = $("#addChildCode").val();
                    var idx = $("#addChildIdx").val();
                    var weight = $("#addChildWeight").val();
                    var name = $("#addChildName").val();
                    var bdName = $("#addChildBDName").val();
                    var parentId = $("#addChildParentId").val();
                    var type = $("#addChildParentType").val();
                    addChildGeo(id, code, idx, weight, name, bdName, parentId, type);
                });
            </script>
    </jsp:attribute>
    <jsp:body>
        <div id="jsTree" style="float: left"></div>
        <div class="ui container" style="left: 300px;top: 50px;width: 800px">
            <div class="ui segment">
                <a class="ui teal ribbon label">geo详细信息</a>
                <form id="form" class="ui form update" action="/geo/updateGeo.do" method="post">
                    <input type="hidden" name="voType" id="geoInfoType"/>
                    <div class="three fields">
                        <div class="field">
                            <label>ID序号</label>
                            <input id="geoInfoId" name="id" readonly="readonly" type="text"/>
                        </div>
                        <div class="field">
                            <label>Code编码</label>
                            <input id="geoInfoCode" name="code" type="text"/>
                        </div>
                        <div class="field">
                            <label>排序</label>
                            <input id="geoInfoIdx" name="idx" type="text"/>
                        </div>
                    </div>
                    <div class="three fields">
                        <div class="field">
                            <label>权重</label>
                            <input id="geoInfoWeight" name="weight" type="text"/>
                        </div>
                        <div class="field">
                            <label>名称</label>
                            <input id="geoInfoName" name="name" type="text"/>
                        </div>
                        <div class="field">
                            <label>百度名称</label>
                            <input id="geoInfoBDName" name="baiduName" type="text"/>
                        </div>
                    </div>
                    <div class="three fields">
                        <div class="field">
                            <label>描述</label>
                            <input id="geoInfoDesc" name="description" type="text"/>
                        </div>
                        <div class="field">
                            <label>父级</label>
                            <input id="geoInfoParentName" name="parentName" type="text"
                                   readonly="readonly"/>
                        </div>
                        <div class="field">
                            <label>状态</label>
                            <select id="geoInfoStatus" name="status">
                                <c:forEach items="${geoStatus}" var="geoStatu">
                                    <option value="${geoStatu.value}">${geoStatu.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="ui button primary btnUpdate">提交更新</button>
                        <button class="ui button primary pull-right deletNode" type="button">删除
                        </button>
                        <button class="ui button primary pull-right btn-newNode"
                                onclick="addChild()"
                                type="button">新增下级节点
                        </button>
                    </div>
                </form>
            </div>
            <div class="ui segment">
                <a class="ui teal ribbon label">geo区域</a>
                <table id="table" class="ui striped table">
                    <thead>
                    <tr>
                        <th>
                            <h4>名称</h4>
                        </th>
                        <th>
                            <h4>状态</h4>
                        </th>
                        <th>
                            <h4>操作</h4>
                        </th>
                    </tr>
                </table>
            </div>
                <%--新增子节点--%>
            <div class="ui modal add-child">
                <div class="header">新增节点</div>
                <div class="content">
                    <input id="addChildParentType" type="hidden"/>
                    <input id="addChildParentId" type="hidden"/>
                    <div class="ui raised segment form">
                        <div class="three fields">
                            <div class="field">
                                <label>ID序号</label>
                                <input id="addChildId" type="text"/>
                            </div>
                            <div class="field">
                                <label>Code编码</label>
                                <input id="addChildCode" type="text"/>
                            </div>
                            <div class="field">
                                <label>排序</label>
                                <input id="addChildIdx" type="text"/>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>权重</label>
                                <input id="addChildWeight" type="text"/>
                            </div>
                            <div class="field">
                                <label>名称</label>
                                <input id="addChildName" type="text"/>
                            </div>
                            <div class="field">
                                <label>百度名称</label>
                                <input id="addChildBDName" type="text"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative button">
                        取消
                    </div>
                    <div class="ui positive right labeled icon button confirm-add-Child">
                        确认
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</manage:page>
