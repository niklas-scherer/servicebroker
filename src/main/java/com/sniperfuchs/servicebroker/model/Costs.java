package com.sniperfuchs.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class Costs
{
    private Map<String, Double> amount;
    private String unit;
}
