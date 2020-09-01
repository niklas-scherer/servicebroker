package com.sniperfuchs.servicebroker.service;

import com.sniperfuchs.servicebroker.model.Catalog;
import com.sniperfuchs.servicebroker.repository.ServiceOfferingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CatalogService
{

    private ServiceOfferingRepository serviceOfferingRepository;
    private Logger logger = LoggerFactory.getLogger(CatalogService.class);


    public CatalogService(ServiceOfferingRepository serviceOfferingRepository)
    {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public Catalog getAllServiceOfferings() {
        return new Catalog(serviceOfferingRepository.findAll());
    }
}
