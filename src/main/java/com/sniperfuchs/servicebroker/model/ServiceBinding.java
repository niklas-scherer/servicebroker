package com.sniperfuchs.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ServiceBinding {
    @Id
    private String id;
    private BindingMetadata metadata;
    private Map<String, Object> credentials;
    private String syslog_drain_url;
    private String route_service_url;
    //TODO volume mounts
    private Map<String, Object> parameters = new HashMap<>();
    //TODO endpoints

    public boolean hasSameAttributes(ServiceBinding other)
    {
        if(id.equals(other.id) && parameters.equals(other.parameters))
        {
            return true;
        }
        return false; //TODO fix this
    }
}
