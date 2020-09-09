package com.sniperfuchs.servicebroker.repository;

import com.sniperfuchs.servicebroker.model.ServiceBinding;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceBindingRepository extends MongoRepository<ServiceBinding, String> {
}
