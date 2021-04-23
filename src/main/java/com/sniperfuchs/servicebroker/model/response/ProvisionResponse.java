package com.sniperfuchs.servicebroker.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sniperfuchs.servicebroker.model.ServiceInstanceMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProvisionResponse {
    private String dashboard_url;
    private String operation;
    private ServiceInstanceMetadata metadata;
}
