package com.biz.manage.controller;

import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manage异常拦截Controller替换SpringBoot {@link BasicErrorController}
 *
 * Created by david-liu on 2017/04/20 10:12.
 */
@Controller
@RequestMapping
public class ManageController extends AbstractErrorController {

    private static final String PATH = "/error";

    public ManageController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping(value = PATH, produces = "text/html")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, isIncludeStackTrace(request)));
        response.setStatus(status.value());
        return new ModelAndView("error", model);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request the source request
     * @return if the stacktrace attribute should be included
     */
    private boolean isIncludeStackTrace(HttpServletRequest request) {
        return getTraceParameter(request);
    }

}
