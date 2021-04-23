package com.sniperfuchs.servicebroker.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Catalog {

    private List<ServiceOffering> services;
}
