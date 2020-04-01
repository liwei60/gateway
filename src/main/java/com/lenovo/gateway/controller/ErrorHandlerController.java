package com.lenovo.gateway.controller;

import com.lenovo.gateway.filter.ErrorFilter;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @Author: liwei60
 * @CreateDate: 2020-04-01 16:05:25
 * @UpdateUser:
 * @UpdateDate: 2020-04-01 16:05:25
 * @UpdateRemark: TODO
 * @Version: 1.0
 */
@RestController
public class ErrorHandlerController extends AbstractErrorController {

    public ErrorHandlerController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping(value = "/error")
    public ResponseEntity<ErrorBean> error(HttpServletRequest request) {
        String message = request.getAttribute("javax.servlet.error.message").toString();

        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");

        ErrorBean errorBean = new ErrorBean();
        errorBean.setMessage(message);
        errorBean.setCode(code);
        return new ResponseEntity<>(errorBean, HttpStatus.BAD_GATEWAY);
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }



    private static class ErrorBean {

        private int code;

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
