var Common = {
    Btn: {
        BTN_FLAG: true,
        enable: function() {
            Common.Btn.BTN_FLAG = true;
        },
        disable: function() {
            Common.Btn.BTN_FLAG = false;
        }
    },
    validIsNumber: function(value) {
        return !isNaN(value);
    },
    checkPhone: function (phone) {
        if (!(/^1[34578]\d{9}$/.test(phone))) {
            layer.msg("手机号码有误，请重填");
            return false;
        }
        return true;
    }
};