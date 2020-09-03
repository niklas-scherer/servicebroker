package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.model.ServiceInstanceMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProvisionResponse {
    private String dashboard_url;
    private String operation;
    private ServiceInstanceMetadata metadata;
}
