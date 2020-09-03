package com.sniperfuchs.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServicePlan
{
    @Id
    private String id;
    private String name;
    private String description;
    private PlanMetadata metadata;
    private boolean free;
    private boolean bindable;
    //private boolean binding_rotatable;
    private boolean plan_updateable;
    private Schemas schemas;
    private int maximum_polling_duration;
    private MaintenanceInfo maintenance_info;
}
