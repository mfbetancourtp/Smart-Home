package com.smarthome.backend.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TraceMdcInterceptor implements HandlerInterceptor {
    
    @Override 
    public boolean preHandle(HttpServletRequest r, HttpServletResponse h, Object o){
        String traceId = TraceFilter.get();
        
        // Protegemos el MDC contra nulos
        if (traceId != null) {
            MDC.put("traceId", traceId);
        } else {
            // Si no viene, le ponemos uno por defecto para que no explote
            MDC.put("traceId", "SIN-TRACE-" + UUID.randomUUID().toString().substring(0,8));
        }
        
        return true;
    }
    
    @Override 
    public void afterCompletion(HttpServletRequest r, HttpServletResponse h, Object o, Exception ex){
        MDC.clear();
    }
}