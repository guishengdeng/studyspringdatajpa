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

    $('.ajax-detail-btn').on('click', function (e) {
        e.preventDefault();

        var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');

        $("#td-detail-" + data_id).load(data_url, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")

                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('.ajax-contact-btn').on('click', function (e) {
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");

        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }

        if ($("#contact_div").length == 0) {
            $(document.body).append('<div id="contact_div"><div id="modal-table" class="modal fade modal " tabindex="-1" style="display: none;"> <div class="modal-dialog modal-lg" style=""> <div class="modal-content" > <div class="modal-header no-padding center bg-primary"> <div class="table-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> <span class="white">×</span> </button> <div class="table-header-title"></div> </div> </div> <div class="modal-body no-padding"> </div> </div> </div> </div> </div>');
        }
        $(".modal-body").load(data_url, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success") {
                $(".table-header-title").html(title)
                $("#modal-table").modal('show');
            }
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });


    });
    $('.ajax-map-btn').on('click', function (e) {
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");
        var lon_id = $(this).attr("data-lon-id");
        var lat_id = $(this).attr("data-lat-id");
        var address_id = $(this).attr("data-address-id");
        var address = $("#" + address_id).val();
        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }
        if (address) {
            data_url = data_url + "&address=" + encodeURI(address);
        }

        if ($("#google_map_div").length == 0) {
            $(document.body).append('<div id="google_map_div"><div id="modal-table" class="modal fade modal " tabindex="-1" style="display: none;"> <div class="modal-dialog modal-lg" style=""> <div class="modal-content" > <div class="modal-header no-padding center bg-primary"> <div class="table-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> <span class="white">×</span> </button> <div class="table-header-title"></div> </div> </div> <div class="modal-body no-padding"> </div> </div> </div> </div> </div>');

            $(".modal-body").load(data_url, function (responseTxt, statusTxt, xhr) {
                if (statusTxt == "success") {
                    $(".table-header-title").html(title)
                    $("#modal-table").modal('show');
                }
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
            });
        } else {
            lat = $("#" + lat_id).val();
            lon = $("#" + lon_id).val();

            if (lon == '' || lon == undefined) {
                lon = 8.683;
            }
            if (lat == '' || lat == undefined) {
                lat = 50.117;
            }
            $("#longitude1").val(lon);
            $("#latitude1").val(lat);
            $(".table-header-title").html(title)
            $("#address1").val(address)
            $("#modal-table").modal('show');
            latlng = new google.maps.LatLng(lat, lon);
            map.setCenter(latlng)
            marker.setPosition(latlng);
        }

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