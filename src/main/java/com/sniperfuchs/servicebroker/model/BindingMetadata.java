package com.sniperfuchs.servicebroker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BindingMetadata {
    private String expires_at; //TODO check for ISO 8601 and yyyy-mm-ddThh:mm:ss.ssZ format
}
