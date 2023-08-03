package com.pb.week11.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter("/*")
public class FilterGPT extends OncePerRequestFilter {

    private static final String X_XSRF_TOKEN_HEADER = "X-XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String xsrfToken = request.getParameter(X_XSRF_TOKEN_HEADER);
        if (xsrfToken != null) {
            request.setAttribute(CsrfToken.class.getName(), new CsrfToken() {
                @Override
                public String getToken() {
                    return xsrfToken;
                }

                @Override
                public String getHeaderName() {
                    return X_XSRF_TOKEN_HEADER;
                }

                @Override
                public String getParameterName() {
                    return X_XSRF_TOKEN_HEADER;
                }
            }
            );
        }
        filterChain.doFilter(request, response);
    }
}