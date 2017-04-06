$(function(){
    updateOrAddSubmit();
    add();
});
/*添加或者修改的form表单提交事件*/
function  updateOrAddSubmit(){
    $('#submitForm').bind('click', function () {
        $.ajax({
            type: "POST",
            data: $('#form_data').serialize(),//form表单参数
            url: '../resource/updateOrAddSubmit.action',
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                window.location.href = "../resource/resourcelist.action";
            }
        });
    });
}
function editInfo(id) {
    if (!id) {
        alert('id值不能为空，请检查');
        return false;
    }
    $.ajax({
        url: "../resource/updateresource.action",
        data: {"id": id},
        dataType: "text",
        async: false,
        success: function (data) {
            data = JSON.parse(decodeURIComponent(data));
            console.log(data);
            $('#resourcesList').empty();
            $("#resource_id").val(data.resource.id);
            $("#resource_resourcename").val(data.resource.resourcename);
            $('#resource_linkedaddress').val('').attr("readonly","readonly");
            $('#menuItem').empty();
            if(data.currentMenuItem!=null){
                $("#menuItem").append('<input type="checkbox" name="menuitem_id" value="' + data.currentMenuItem.menuitem_id + '" checked="checked" />' + data.currentMenuItem.name);
            }
            if(data.menuItems!=null){
                for(var index=0;index<data.menuItems.length;index++){
                    var menuitem=data.menuItems[index];
                    $("#menuItem").append('<input type="checkbox" name="menuitem_id" value="' + menuitem.menuitem_id + '" />' + menuitem.name);
                }
            }
            $("#resource_description").val(data.resource.description);
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
        url: '../resource/deleteresource.action',
        async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
        error: function (request) {
            alert("Connection error");
        },
        success: function (data) {
            window.location.href = "../resource/resourcelist.action";
        }
    });
}
function add(){
    $('#addResource').bind("click",function(){
        $.ajax({
            type: "POST",
            url: '../resource/add.action',
            dataType:"json",
            async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
            success: function (data) {
                $('#menuItem').empty();
                if(data.menuItems!=null){
                    for(var index=0;index<data.menuItems.length;index++){
                        var menuitem=data.menuItems[index];
                        $("#menuItem").append('<input type="checkbox" name="menuitem_id" value="' + menuitem.menuitem_id + '" />' + menuitem.name);
                    }
                }
            },error: function (request) {
                alert("Connection error");
            }
        });
    });
}