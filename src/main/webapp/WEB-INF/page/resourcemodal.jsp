<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/27
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资源模态框</title>
</head>
<body>
<!-- 添加(修改)模态框示例实例 -->
<form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
    <div class="modal fade" id="addOrUpdateRosourceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        资源信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="resourceForm">
                        <div class="form-group">

                            <label for="resource_id" class="col-sm-3 control-label">ID</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="id" value="" id="resource_id"
                                       placeholder="请输入id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="resource_resourcename" class="col-sm-3 control-label">资源名</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="resourcename" value="" id="resource_resourcename"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="resource_description" class="col-sm-3 control-label">描述</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="description" value="" id="resource_description"
                                       placeholder="">
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
