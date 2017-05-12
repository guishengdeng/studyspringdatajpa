package com.biz.soa.base;

import com.biz.core.exceptions.BusinessException;
import com.biz.core.exceptions.FunctionExceptions;
import com.biz.gbck.vo.soa.MicroServiceResult;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static com.biz.gbck.vo.soa.MicroServiceResult.INTERNAL_ERROR_STATUS;
import static com.biz.gbck.vo.soa.MicroServiceResult.SUCCESS_STATUS;

/**
 * SOA Base Controller
 *
 * Created by david-liu on 2017/04/27 15:48.
 */
@RestController
public abstract class SoaBaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    protected MicroServiceResult<String> exceptionHandler(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        if (ex instanceof BusinessException) {
            logger.info("business-exception:{}", ex);
            return this.render500(ex.getMessage());
        } else {
            logger.error("system-exception:{}", ex);
            return this.renderError(FunctionExceptions.System.SYS_EXCEPTION.getCode(), ex.getMessage());
        }
    }

    protected MicroServiceResult<String> renderError(Integer status, String message) {
        MicroServiceResult<String> renderResult = new MicroServiceResult<>();
        renderResult.setStatus(status);
        renderResult.setMsg(message);
        return renderResult;
    }

    protected <T> MicroServiceResult<T> render500(String message) {
        MicroServiceResult<T> renderResult = new MicroServiceResult<>();
        renderResult.setStatus(INTERNAL_ERROR_STATUS);
        renderResult.setMsg(message);
        return renderResult;
    }

    protected <T> MicroServiceResult<T> render500(Exception e) {
        MicroServiceResult<T> renderResult = new MicroServiceResult<>();
        renderResult.setStatus(INTERNAL_ERROR_STATUS);
        renderResult.setException(e);
        renderResult.setMsg(e.getMessage());
        return renderResult;
    }

    protected <T> MicroServiceResult<T> render200(T data) {
        MicroServiceResult<T> renderResult = new MicroServiceResult<>();
        renderResult.setStatus(SUCCESS_STATUS);
        renderResult.setData(data);
        return renderResult;
    }

}
