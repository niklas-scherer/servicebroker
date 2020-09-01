package com.sniperfuchs.servicebroker.service;

import com.sniperfuchs.servicebroker.exception.ServiceInstanceNotFoundException;
import com.sniperfuchs.servicebroker.model.ServiceInstance;
import com.sniperfuchs.servicebroker.repository.ServiceInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceInstanceService
{

    private ServiceInstanceRepository serviceInstanceRepository;

    public ServiceInstanceService(ServiceInstanceRepository serviceInstanceRepository)
    {
        this.serviceInstanceRepository = serviceInstanceRepository;
    }

    public ServiceInstance fetchInstanceById(String instance_id) throws ServiceInstanceNotFoundException
    {
        if(serviceInstanceRepository.findById(instance_id).isEmpty())
        {
            throw new ServiceInstanceNotFoundException("Service instance with id " + instance_id + " does not exist.");
        }
        return serviceInstanceRepository.findById(instance_id).get();
    }

    public ServiceInstance createInstance(String service_id,
                                          String plan_id,
                                          String organization_guid,
                                          String space_guid,
                                          Object parameters) {
        ServiceInstance serviceInstance = ServiceInstance.builder()
                .service_id(service_id)
                .plan_id(plan_id)
                .organization_guid(organization_guid)
                .space_guid(space_guid)
                .parameters(parameters)
                .build();

        //TODO Populate instance with parameters
        //TODO Deploy service on kubernetes cluster with HELM, maybe in different service
        return serviceInstanceRepository.save(serviceInstance);
    }

    public ServiceInstance updateInstanceById(String instance_id) {
        if(serviceInstanceRepository.findById(instance_id).isEmpty())
        {
            throw new ServiceInstanceNotFoundException("Service instance with id " + instance_id + " does not exist.");
        }
        return serviceInstanceRepository.findById(instance_id).get();
    }
}
