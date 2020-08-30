package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.model.Catalog;
import com.sniperfuchs.servicebroker.model.ServiceOffering;
import com.sniperfuchs.servicebroker.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/v2/catalog")
    public ResponseEntity<Catalog> getCatalog() {
        return new ResponseEntity<>(catalogService.getAllServiceOfferings(), HttpStatus.OK);
    }

}
