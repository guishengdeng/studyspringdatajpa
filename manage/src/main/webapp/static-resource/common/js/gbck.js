function setSessionCookie(name, value) {
    document.cookie = name + "=" + value + ";path=/";
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else {
        return null;
    }
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
$(function () {
    $('form').each(function () {
        $(this).validate();
    });
    $("a.history-back").click(function(e){
        e.preventDefault();
        window.history.back();
    });
    $('.chosen-select').chosen({allow_single_deselect: true, disable_search_threshold: 10});

    $('.show-details-btn').on('click', function (e) {
        e.preventDefault();
        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
    });
    $('.navable-a-tag').on('click', function(e){
        e.preventDefault();
        var $this = $(this);
        setSessionCookie("activeMenu", $this.data("main-menu") + "_" + $this.data("sub-menu"));
        window.location.href=$this.data('href');
    });

    $(".btn-delete-modal").on(ace.click_event, function () {
        var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        bootbox.dialog({
            message: "<span class='bigger-110'>" + localos_message.prompt_msg + "</span>", buttons: {
                "cancel": {
                    "label": "<i class='ace-icon fa fa-remove'></i> " + localos_message.prompt_cancel,
                    "className": "btn-sm btn-gray",
                }, "Confirm": {
                    "label": "<i class='ace-icon fa fa-check'></i> " + localos_message.prompt_confirm,
                    "className": "btn-sm btn-primary",
                    "callback": function () {
                        $.post(data_url, {
                            "id": data_id
                        }, function (result) {
                            if (result) {
                                $("#tr-" + data_id).remove();
                                $("#tr-detail-" + data_id).remove();
                            }
                        });

                    }
                }
            }
        });
    });

});