<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/4/1
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 添加(修改)模态框示例实例 -->
<form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
    <div class="modal fade" id="addOrUpdateMenuItemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        子菜单信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="menuItemForm">
                        <div class="form-group">
                            <label for="menuitem_id" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="menuitem_id" value="" id="menuitem_id"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="menuitem_name" class="col-sm-3 control-label">名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" value="" id="menuitem_name"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="menuitem_link" class="col-sm-3 control-label">链接</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="link" value="" id="menuitem_link"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="menuitem_description" class="col-sm-3 control-label">描述</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="description" value="" id="menuitem_description"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="menuitem_symbol" class="col-sm-3 control-label">符号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="symbol" value="" id="menuitem_symbol"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">父级目录</label>
                            <div class="col-sm-9" id="mainMeun">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色</label>
                            <div class="col-sm-9" id="roleList">

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
