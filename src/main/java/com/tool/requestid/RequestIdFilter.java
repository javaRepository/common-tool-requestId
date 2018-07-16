package com.tool.requestid;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Order(Integer.MAX_VALUE)
//spring boot 会按照order值的大小，从小到大的顺序来依次过滤。
@WebFilter(filterName = "requestIdFilter", urlPatterns = "/*")
public class RequestIdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String logId = req.getHeader(RequestIdHelper.HEADER_REQUEST_ID);
        if(logId == null) {
            logId = UUID.randomUUID().toString();
        }

        RequestIdHelper.setRequestId(logId);

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader(RequestIdHelper.HEADER_REQUEST_ID, logId);

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            RequestIdHelper.removeRequestId();
        }

    }

    @Override
    public void destroy() {

    }

}
