package com.sniperfuchs.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Builder
public class ServiceInstance {
    @Id
    @JsonIgnore
    private Long id;

    private String service_id;
    private String plan_id;
    private Object context;
    private String organization_guid;
    private String space_guid;
    private Object parameters;
    private MaintenanceInfo maintenance_info; //TODO Maybe not necessary
    private String dashboard_url;
    //private String operation;
    //private ServiceInstanceMetadata metadata;

    public ServiceInstance() {}
}
