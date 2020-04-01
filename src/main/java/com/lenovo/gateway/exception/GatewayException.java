package com.lenovo.gateway.exception;

/**
 * @Description: TODO
 * @Author: liwei60
 * @CreateDate: 2020-04-01 16:11:14
 * @UpdateUser:
 * @UpdateDate: 2020-04-01 16:11:14
 * @UpdateRemark: TODO
 * @Version: 1.0
 */
public class GatewayException extends RuntimeException{

    private int code = 1;

    public GatewayException(int code) {
        this.code = code;
    }

    public GatewayException(String message, int code) {
        super(message);
        this.code = code;
    }

    public GatewayException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public GatewayException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public GatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
