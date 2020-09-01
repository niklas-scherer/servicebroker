package com.sniperfuchs.servicebroker.repository;

import com.sniperfuchs.servicebroker.model.ServiceOffering;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceOfferingRepository extends MongoRepository<ServiceOffering, String>
{
}
