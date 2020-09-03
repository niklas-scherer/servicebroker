package com.sniperfuchs.servicebroker.config;

import com.sniperfuchs.servicebroker.exception.ApiVersionMissingException;
import com.sniperfuchs.servicebroker.exception.UnsupportedApiVersionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomHandlerInterceptor extends HandlerInterceptorAdapter
{

    private Logger logger = LoggerFactory.getLogger(CustomHandlerInterceptor.class);

    @Value("${SUPPORTED_API_VERSION}")
    private String supportedApiVersion = "2.15"; //TODO fix this so it reads input from config file

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception
    {
        if(request.getHeader("X-Broker-API-Version") == null)
        {
            logger.error("Request is missing \"X-Broker-API-Version\" field.");
            throw new ApiVersionMissingException("Request is missing \"X-Broker-API-Version\" field.");
        }

        if(!request.getHeader("X-Broker-API-Version").equals(supportedApiVersion))
        {
            logger.error("API Version" + request.getHeader("X-Broker-API-Version") + " in request header. Expected " + supportedApiVersion);
            throw new UnsupportedApiVersionException("This Service Broker only accepts the API version 2.16");
        }

        return true;
    }
}
