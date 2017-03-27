$(function(){
    addRoleModal();
    updateOrAddSubmit();
});
function addRoleModal() {
    $('#addRole').bind('click', function () {

        $("#roleForm").find("input[type=text]").val('');
        $.ajax({
            type: "POST",
            dataType: "text",
            url: '../role/add.action',
            async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                data = JSON.parse(decodeURIComponent(data));
                console.log(data);
                $('#resourcesList').empty();
                for (var index = 0; index < data.resources.length; index++) {
                    var resource = data.resources[index];
                    $("#resourcesList").append('<input type="checkbox" name="id" value="' + resource.id + '"/>' + resource.resourcename);
                }

            }
        });
    });
}
function deleteInfo(id) {
    if (!id) {
        alert('id值不能为空。');
        return false;
    }
    $.ajax({
        type: "POST",
        data: {"id": id},
        url: '../role/deleterole.action',
        async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            window.location.href = "../role/rolelist.action";
        }
    });
}
function editInfo(id){

    if (!id) {
        alert('id值不能为空，请检查');
        return false;
    }
    $.ajax({
        url: "../role/updaterole.action",
        data: {"id": id},
        dataType: "text",
        async: false,
        success: function (data) {
            data = JSON.parse(decodeURIComponent(data));
            console.log(data);
            $("#role_id").val(data.role.role_id);
            $("#role_name").val(data.role.name);
            $("#role_description").val(data.role.description);
            $('#resourcesList').empty();
           ///这是点击修改时，之前选择的角色。
            for (var index = 0; index < data.role.resources.length; index++) {
                var resource = data.role.resources[index];
                $("#resourcesList").append('<input type="checkbox" name="id" value="' + resource.id + '" checked="checked" />' + resource.resourcename);
            }
            for (var index = 0; index < data.resources.length; index++) {
                var resource = data.resources[index];
                $("#resourcesList").append('<input type="checkbox" name="id" value="' + resource.id + '"/>' + resource.resourcename);
            }
        }

    });
    return false;
}
/*添加或者修改的form表单提交事件*/
function  updateOrAddSubmit(){
    $('#submitForm').bind('click', function () {

        $.ajax({
            type: "POST",
            data: $('#form_data').serialize(),//form表单参数
            url: '../role/updateOrAddSubmit.action',
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                window.location.href = "../role/rolelist.action";
            }
        });
    });
}