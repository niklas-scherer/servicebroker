package com.sniperfuchs.servicebroker.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BindResponse {
    private Map<String, Object> credentials;
    private String syslog_drain_url = "Test";
    private String route_service_url;
    // TODO volume mounts and endpoints
}
