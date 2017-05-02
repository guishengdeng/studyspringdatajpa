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

});