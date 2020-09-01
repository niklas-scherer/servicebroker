package com.sniperfuchs.servicebroker.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceOffering
{
    private String name;

    @Id
    private String id;
    private String description;

    private List<String> tags;
    private List<String> requires;
    private boolean bindable;
    private boolean instances_retrievable;
    private boolean bindings_retrievable;
    private boolean allow_context_updates;

    private Map<String, Object> metadata;

    //private DashboardClient dashboard_client;
    private boolean plan_updateable;
    private List<ServicePlan> plans;

}
