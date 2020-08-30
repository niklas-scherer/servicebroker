package com.sniperfuchs.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceMetadata {
    private String displayName;
    private String imageUrl;
    private String longDescription;
    private String providerDisplayName;
    private String documentationUrl;
    private String supportUrl;

    // Cloud Foundry specific metadata

    private boolean shareable;
}
