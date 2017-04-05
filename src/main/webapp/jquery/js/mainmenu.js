$(function(){
    addMainMenu();
    addOrUpdateSubmit();
});
function editInfo(id) {
    if (!id) {
        alert("id值不能为空,请检查是否传入id");
        return false;
    }
    $.ajax({
        type: 'POST',
        url: "../mainmenu/update.action",
        data: {"id": id},
        dataType: "text",//html xml javascirpt text json jsonp
        async: false,//是否异步，为false时，只有加载完成后，才可以进行其他操作
        success: function (data) {
            data=JSON.parse(data);
            alert(data);
            console.log(data);
            $("#mainmenu_id").val(data.mainMenu.id);
            $("#mainmenu_code").val(data.mainMenu.code);
            $("#mainmenu_name").val(data.mainMenu.name);
            $("#mainmenu_description").val(data.mainMenu.description);
            //$("#act").val("edit");
            // 将input元素设置为readonly
            //$('#user_id').attr("readonly","readonly");
            $("#menuItemList").empty();
            //这是点击修改时,本身所具有的子目录
            for (var index = 0; index < data.currentMenuItem.length; index++) {
                var menuItem = data.currentMenuItem[index];
                $("#menuItemList").append('<input type="checkbox" name="menuitem_id" value="' + menuItem.menuitem_id + '" checked="checked" />' + menuItem.name);
            }
            for (var index = 0; index < data.menuItems.length; index++) {
                var menuItem = data.menuItems[index];
                $("#menuItemList").append('<input type="checkbox" name="menuitem_id" value="' + menuItem.menuitem_id + '"/>' + menuItem.name);
            }
        }

    });
    return false;
 }
 //添加主菜单目录
 function addMainMenu() {
      $('#addMainMenu').bind('click',function () {
          $('#mainMenuForm').find("input[type=text]").val('');
          $.ajax({
              type: "POST",
              dataType: "text",
              url: '../mainmenu/add.action',
              async: false,
              success: function (data) {
                  data=JSON.parse(data);
                  $('#menuItemList').empty();
                  for (var index = 0; index < data.menuItems.length; index++) {
                      var menuItem = data.menuItems[index];
                      $("#menuItemList").append('<input type="checkbox" name="menuitem_id" value="' + menuItem.menuitem_id + '"/>' + menuItem.name);
                  }

              },error: function () {
                  alert("Connection error");
              }
          });
      });
 }
 function addOrUpdateSubmit(){
     $('#submitForm').bind('click', function () {
         $.ajax({
             data: $('#form_data').serialize(),//form表单参数
             url: '../mainmenu/updateOrAddSubmit.action',
             success: function (data) {

                 window.location.href = "../mainmenu/mainmenulist.action";
             },error: function (request) {
                 alert("Connection error");
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
        url: '../mainmenu/delete.action',
        async: false, // 是否异步加载数据， 为false时只有加载完成后才继续其他操作，类似于同步锁
        success: function (data) {
            window.location.href = "../mainmenu/mainmenulist.action";
        }, error: function (request) {
            alert("Connection error");
        }
    });
}