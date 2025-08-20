package com.quantilearn.api_gateway.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    //overriding the message content
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String,Object> errorMap=new HashMap<>();
        Throwable error=getError(request);
        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        errorMap.put("message",error.getMessage());
        errorMap.put("timestamp",timestamp);
        return errorMap;
    }

}