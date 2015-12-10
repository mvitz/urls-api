package com.innoq.urls.api.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

@Component
public final class RequestDebugFilter implements Filter {

    private static final String DEBUG_HEADER = "x-debug-enabled";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            final Boolean debugHeaderEnabled = Boolean.valueOf(
                    ((HttpServletRequest) request).getHeader(DEBUG_HEADER));
            if (debugHeaderEnabled) {
                ThreadContext.put(DEBUG_HEADER, debugHeaderEnabled.toString());
            }
            chain.doFilter(request, response);
        } finally {
            ThreadContext.remove(DEBUG_HEADER);
        }
    }

    @Override
    public void destroy() {
    }

}
