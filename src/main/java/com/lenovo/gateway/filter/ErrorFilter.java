package com.lenovo.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

/**
 * @Description: TODO
 * @Author: liwei60
 * @CreateDate: 2020-04-01 16:12:41
 * @UpdateUser:
 * @UpdateDate: 2020-04-01 16:12:41
 * @UpdateRemark: TODO
 * @Version: 1.0
 */
public class ErrorFilter extends ZuulFilter {

    private static final Log log = LogFactory.getLog(ErrorFilter.class);
    protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";
    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getThrowable() != null
                && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
    }

    @Override
    public Object run() {

        log.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }
        return null;

    }
    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}
