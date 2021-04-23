package com.sniperfuchs.servicebroker.repository;

import com.sniperfuchs.servicebroker.model.ServiceInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceInstanceRepository extends MongoRepository<ServiceInstance, String> {
}
