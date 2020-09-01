package com.sniperfuchs.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ServiceInstanceMetadata {
    private Map<String, String> labels;
}
