package com.sniperfuchs.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanMetadata
{
    private List<String> bullets;
    private List<Costs> costs;
    private String displayName;
}
