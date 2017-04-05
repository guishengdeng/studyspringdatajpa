
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>主菜单模态框</title>
</head>
<body>
<!-- 添加(修改)模态框示例实例 -->
<form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
    <div class="modal fade" id="addOrUpdateMainMenuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        主菜单信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="mainMenuForm">
                        <div class="form-group">
                            <%--for="user_id"的属性值对应 id="user_id"的属性 --%>
                            <label for="mainmenu_id" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="id" value="" id="mainmenu_id"
                                       placeholder="请输入id">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="mainmenu_code" class="col-sm-3 control-label">代码</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="code" value="" id="mainmenu_code"
                                       placeholder="请输入角色">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mainmenu_name" class="col-sm-3 control-label">名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" value="" id="mainmenu_name"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mainmenu_description" class="col-sm-3 control-label">描述</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="description" value="" id="mainmenu_description"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">子目录</label>
                            <div class="col-sm-9" id="menuItemList">

                            </div>
                        </div>
                        <input type="hidden" id="act" value="add" name="act"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="submitForm">
                        提交
                    </button><span id="tip"> </span>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
