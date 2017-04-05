$(function () {
    addMenuItem();
    addOrUpdate();
});
function addMenuItem() {
    $('#addMenuItem').bind('click', function () {
        $("#menuItemForm").find("input[type=text]").val('');
        $.ajax({
            type: "POST",
            dataType: "text",
            url: '../menuitem/add.action',
            async: false,
            success: function (data) {
                data = JSON.parse(data);
                $('#mainMeun').empty();
                $('#roleList').empty();
                for (var index = 0; index < data.mainMenus.length; index++) {
                     var mainMenu = data.mainMenus[index];
                    $("#mainMeun").append('<input type="checkbox" name="id" value="' + mainMenu.id + '"/>' + mainMenu.name);
                }
                for (var index = 0; index < data.roles.length; index++) {
                    var role = data.roles[index];
                    $("#roleList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '"/>' + role.name);
                }

            }, error: function (request) {
                alert("Connection error");
            }
        });
    });
}
function editInfo(id){
    if(!id){
        alert("id值不能为空");
        return false;
    }
    $.ajax({
        type: 'POST',
        url: "../menuitem/update.action",
        data: {"menuitem_id": id},
        dataType: "text",//html xml javascirpt text json jsonp
        async: false,
        global:false,
        success: function (data) {
            data=JSON.parse(data);
            console.log(data);
            $("#menuitem_id").val(data.menuItem.menuitem_id);
            $("#menuitem_link").val(data.menuItem.link);
            $("#menuitem_name").val(data.menuItem.name);
            $("#menuitem_description").val(data.menuItem.description);
            //$("#act").val("edit");
            $('#menuitem_symbol').val(data.menuItem.symbol);
            $("#mainMeun").empty();
            if(data.currentMainMenu.id!=null){
                $("#mainMeun").append('<input type="checkbox" name="id" value="' + data.currentMainMenu.id + '" checked="checked" />' + data.currentMainMenu.name);
            }
            for(var index=0;index < data.mainMenus.length;index++){
                var mainMenu=data.mainMenus[index];
                $("#mainMeun").append('<input type="checkbox" name="id" value="' + mainMenu.id + '" />' + mainMenu.name);
            }
            $("#roleList").empty();
            for (var index = 0; index < data.currentRoles.length; index++) {
                var role = data.currentRoles[index];
                $("#roleList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '" checked="checked" />' + role.name);
            }
            for (var index = 0; index < data.roles.length; index++) {
                var role = data.roles[index];
                $("#roleList").append('<input type="checkbox" name="role_id" value="' + role.role_id + '"/>' + role.name);
            }
        }

    });
    return false;
}
function addOrUpdate(){
    $('#submitForm').bind('click', function () {
        $.ajax({
            data: $('#form_data').serialize(),//form表单参数
            url: '../menuitem/updateOrAddSubmit.action',
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                window.location.href = "../menuitem/menuitemlist.action";
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
        data: {"menuitem_id": id},
        url: '../menuitem/delete.action',
        async: false,
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            window.location.href = "../menuitem/menuitemlist.action";
        }
    });
}