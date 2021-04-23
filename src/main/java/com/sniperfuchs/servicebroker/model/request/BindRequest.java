package com.sniperfuchs.servicebroker.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sniperfuchs.servicebroker.model.BindResource;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BindRequest {
    private Object context;
    private String service_id;
    private String plan_id;
    private String app_guid;
    private BindResource bind_resource;
}
