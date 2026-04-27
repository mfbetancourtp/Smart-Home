package com.smarthome.backend.web;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TraceFilter extends OncePerRequestFilter {

    public static final String TRACE_HEADER = "X-Trace-ID";
    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    public static String get() { return TRACE_ID.get(); }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            String id = Optional.ofNullable(req.getHeader(TRACE_HEADER))
                                .orElse(UUID.randomUUID().toString());
            TRACE_ID.set(id);
            res.setHeader(TRACE_HEADER, id);     // el front también lo recibe
            chain.doFilter(req, res);
        } finally {
            TRACE_ID.remove();
        }
    }
}