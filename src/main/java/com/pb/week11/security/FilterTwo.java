package com.pb.week11.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebFilter
public class FilterTwo extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String xsrfToken = (String) request.getAttribute("X-XSRF-TOKEN");
        if (xsrfToken != null) {
            StringBuilder requestBody = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            }
            // Modify the request body to include the CSRF token
            String modifiedRequestBody = requestBody.toString() + "&_csrf=" + xsrfToken;

            RequestWrapper requestWrapper = new RequestWrapper(request);
            requestWrapper.setBody(modifiedRequestBody.getBytes());

            // Pass the modified request to the next filter in the chain
            chain.doFilter(requestWrapper, response);
        } else {
            // If CSRF token is not available, proceed with the original request
            chain.doFilter(request, response);
        }
    }
}
