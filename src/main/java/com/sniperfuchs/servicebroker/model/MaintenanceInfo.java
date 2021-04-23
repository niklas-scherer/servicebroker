package com.sniperfuchs.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceInfo {
    private String version;
    private String description;
}
