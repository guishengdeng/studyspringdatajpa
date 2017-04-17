package com.biz.core.exceptions;

/**
 * 枚举异常常量
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月30日
 * @reviewer
 */
public interface FunctionExceptions {

    int SERVER_ERROR_CODE = 600;

    enum System implements ExceptionType {
        OBJ_ISNULL(SERVER_ERROR_CODE + 1, "对象不能为NULL"),
        ID_NOT_EXIST(SERVER_ERROR_CODE + 2, "id不存在"),
        PARAMETER_ERROR(SERVER_ERROR_CODE + 3, "参数异常"),
        MISSING_REQUIRED_PARAMS(SERVER_ERROR_CODE + 4, "缺少必要参数"),
        TYPE_ISERR(SERVER_ERROR_CODE + 5, "类型匹配错误"),
        ILLEGAL_OPERATION(SERVER_ERROR_CODE + 6, "非法操作"),
        SYS_EXCEPTION(SERVER_ERROR_CODE + 304, "")//直接提示给客户端的文字

        ;

        private int code;
        private String description;

        System(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }
}
