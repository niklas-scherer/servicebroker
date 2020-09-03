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
    private String id;

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

    public boolean hasSameAttributes(ServiceInstance other)
    {
        if(this == other || (service_id.equals(other.service_id) && plan_id.equals(other.plan_id) && organization_guid.equals(other.organization_guid) && space_guid.equals(other.space_guid)))
        {
            return true;
        }
        //TODO: Fit context and parameters into this (maybe also metadata?)
        return false;
    }
}
