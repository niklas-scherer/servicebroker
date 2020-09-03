package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.model.MaintenanceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ProvisionRequest
{
    private String service_id;
    private  String plan_id;
    private Map<String, String> context;
    private String organization_guid;
    private String space_guid;
    private Map<String, String> parameters;
    private MaintenanceInfo maintenance_info;
}
