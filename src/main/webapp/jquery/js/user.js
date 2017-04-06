$(function () {
    //备注：注意js脚本代码的封装性，也就是说，代码过长时，应该将其封装成一个方法
    addUser();
    $('#submitForm').bind('click', function () {

        $.ajax({

            data: $('#form_data').serialize(),//form表单参数
            url: '../user/updateOrAddSubmit.action',
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                window.location.href = "../user/userlist.action";
            }
        });
    });
});

function addUser() {
    $('#addUser').bind('click', function () {
        $("#userForm").find("input[type=text]").val('');
        $("#userForm").find("input[type=password]").val('');
        $.ajax({
            type: "POST",
            dataType: "text",
            url: '../user/add.action',
            async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                data = JSON.parse(decodeURIComponent(data));

                $('#rolesList').empty();
                for (var index = 0; index < data.roles.length; index++) {
                    var role = data.roles[index];
                    $("#rolesList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '"/>' + role.name);
                }

            }
        });
    });
}
function editInfo(id) {
    if (!id) {
        alert('Error！');
        return false;
    }
    $.ajax({
        type: 'POST',
        url: "../user/update.action",
        data: {"id": id},
        dataType: "text",//html xml javascirpt text json jsonp
        async: false,
        success: function (data) {
            /*  data = JSON.parse(decodeURIComponent(data));
            alert(data);*/
            //console.log(data);
            data=JSON.parse(data);
            $("#user_id").val(data.user.id);
            $("#user_name").val(data.user.username);
            $("#user_age").val(data.user.age);
            $("#user_email").val(data.user.email);
            //$("#act").val("edit");
            $('#user_password').val(data.user.password);
            $('#user_sex').val(data.user.sex);
            // 将input元素设置为readonly
            //$('#user_id').attr("readonly","readonly");
            $("#rolesList").empty();
            //这是点击修改时，当前用户本身所具有的角色。
            /*for (var index = 0; index < data.currentRoles.length; index++) {
                var role = data.currentRoles[index];
                $("#rolesList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '" checked="checked" />' + role.name);
            }*/
            for(var index in data.currentRoles){
                console.log(data.currentRoles[index]);
                var role=data.currentRoles[index];
                $("#rolesList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '" checked="checked" />' + role.name);
            }
            for (var index = 0; index < data.roles.length; index++) {
                var role = data.roles[index];
                $("#rolesList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '"/>' + role.name);
            }
        }

    });
    return false;
}
function deleteInfo(id) {
    if (!id) {
        alert('id值不能为空。');
        return false;
    }
    $.ajax({
        type: "POST",
        data: {"id": id},
        url: '../user/delete.action',
        async: false, // 是否异步加载数据,为false时只有加载完成后才继续其他操作，类似于同步锁
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            window.location.href = "../user/userlist.action";
        }
    });
}

function testAjax(){
   $('#send').bind('click',function () {
       $.post('../user/userlist.action',
           {"usename":"张三","age":12},
           function(data,textStauts){

           },"json")

   })
}