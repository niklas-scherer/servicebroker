package com.sniperfuchs.servicebroker.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sniperfuchs.servicebroker.model.BindingMetadata;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BindFetchResponse
{
    private BindingMetadata metadata;
    private Map<String, Object> credentials;
    private String syslog_drain_url;
    private String route_service_url;
    //TODO volume mounts, endpoints
    private Map<String, Object> parameters;
}
