package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.model.MaintenanceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProvisionRequest
{
    private String service_id;
    private  String plan_id;
    private Object context;
    private String organization_guid;
    private String space_guid;
    private Object parameters;
    private MaintenanceInfo maintenance_info;
}
