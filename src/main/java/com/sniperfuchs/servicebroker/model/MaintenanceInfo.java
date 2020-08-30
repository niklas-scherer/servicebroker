package com.sniperfuchs.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MaintenanceInfo {
    private String version;
    private String description;
}
