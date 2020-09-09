package com.sniperfuchs.servicebroker.service;

import com.sniperfuchs.servicebroker.controller.ProvisionResponse;
import com.sniperfuchs.servicebroker.exception.*;
import com.sniperfuchs.servicebroker.model.MaintenanceInfo;
import com.sniperfuchs.servicebroker.model.ServiceInstance;
import com.sniperfuchs.servicebroker.model.ServicePlan;
import com.sniperfuchs.servicebroker.repository.ServiceInstanceRepository;
import com.sniperfuchs.servicebroker.repository.ServiceOfferingRepository;
import com.sniperfuchs.servicebroker.util.IdentifierValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceInstanceService
{

    private final ServiceInstanceRepository serviceInstanceRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceInstanceService(ServiceInstanceRepository serviceInstanceRepository, ServiceOfferingRepository serviceOfferingRepository)
    {
        this.serviceInstanceRepository = serviceInstanceRepository;
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public ServiceInstance fetchInstanceById(String instance_id) throws ServiceInstanceNotFoundException
    {
        if(serviceInstanceRepository.findById(instance_id).isEmpty())
        {
            throw new ServiceInstanceNotFoundException("Service instance with id " + instance_id + " does not exist.");
        }
        return serviceInstanceRepository.findById(instance_id).get();
    }

    public ResponseEntity<ProvisionResponse> createInstance(String instance_id,
                                                            String service_id,
                                                            String plan_id,
                                                            String organization_guid,
                                                            String space_guid,
                                                            Object parameters,
                                                            MaintenanceInfo maintenance_info)
    {
        if(!IdentifierValidator.validate(instance_id))
        {
            throw new InvalidIdentifierException("Identifier instance_id " + instance_id + " is null or contains reserved characters (RFC3986).");
        }

        if(!IdentifierValidator.validate(service_id))
        {
            throw new InvalidIdentifierException("Identifier service_id " + service_id + " is null or contains reserved characters (RFC3986).");
        }

        if(!IdentifierValidator.validate(plan_id))
        {
            throw new InvalidIdentifierException("Identifier plan_id " + plan_id + " is null or contains reserved characters (RFC3986).");
        }

        if(!IdentifierValidator.validate(organization_guid))
        {
            throw new InvalidIdentifierException("Identifier organization_guid " + organization_guid + " is null or contains reserved characters (RFC3986).");
        }

        if(!IdentifierValidator.validate(space_guid))
        {
            throw new InvalidIdentifierException("Identifier space_guid " + space_guid + " is null or contains reserved characters (RFC3986).");
        }

        if(!serviceOfferingRepository.existsById(service_id))
        {
            throw new InvalidIdentifierException("Identifier service_id " + service_id + " is invalid and was not found in the catalog.");
        }
        else if(serviceOfferingRepository.findById(service_id).get().getPlans().stream().noneMatch(servicePlan -> servicePlan.getId().equals(plan_id)))
        {
            throw new InvalidIdentifierException("Identifier plan_id " + plan_id + " is invalid and was not found in the catalog.");
        }

        MaintenanceInfo savedMaintenanceInfo = serviceOfferingRepository.findById(service_id).get().getPlans().stream().filter(servicePlan -> servicePlan.getId().equals(plan_id)).findFirst().get().getMaintenance_info();
        if(maintenance_info != null && savedMaintenanceInfo != null && !savedMaintenanceInfo.getVersion().equals(maintenance_info.getVersion()))
        {
            throw new MaintenanceInfoConflictException("The provided maintenance_info.version " + maintenance_info.getVersion() + "does not match the one given by the catalog.");
        }



        ServiceInstance serviceInstance = ServiceInstance.builder()
                .id(instance_id)
                .service_id(service_id)
                .plan_id(plan_id)
                .organization_guid(organization_guid)
                .space_guid(space_guid)
                .parameters(parameters)
                .build();

        //TODO remove dummy data
        ProvisionResponse provisionResponse = new ProvisionResponse("http://example-dashboard.example.com/9189kdfsk0vfnku", "task_10", null);

        Optional<ServiceInstance> optionalServiceInstance = serviceInstanceRepository.findById(instance_id);

        //TODO: maybe rewrite with existsById
        if(optionalServiceInstance.isPresent())
        {
            ServiceInstance existingServiceInstance = optionalServiceInstance.get();
            if(existingServiceInstance.hasSameAttributes(serviceInstance))
            {
                return new ResponseEntity<>(provisionResponse, HttpStatus.OK);
            }
            else
            {
                throw new ExistingServiceInstanceAttributeMismatchException("A service instance with instance_id " + instance_id + " already exists with different attributes.");
            }
        }



        //TODO Populate instance with parameters
        //TODO Deploy service on kubernetes cluster with HELM, maybe in different service


        serviceInstanceRepository.save(serviceInstance);



        return new ResponseEntity<>(provisionResponse, HttpStatus.CREATED);
    }

    public void deleteInstance(String instance_id,
                               String service_id,
                               String plan_id)
    {
        if(instance_id == null || instance_id.isEmpty()
                || service_id == null || service_id.isEmpty()
                || plan_id == null || plan_id.isEmpty()
                || !IdentifierValidator.validate(instance_id)
                || !IdentifierValidator.validate(service_id)
                || !IdentifierValidator.validate(plan_id))
        {
            throw new InvalidIdentifierException(("Identifiers not valid."));
            //TODO: Split into 3 for each identifier
        }

        if(serviceInstanceRepository.findById(instance_id).isEmpty())
        {
            throw new ServiceInstanceGoneException("Service instance with id " + instance_id + " is gone.");
        }

        serviceInstanceRepository.deleteById(instance_id);
        //TODO return something to check if delete was successful?
    }

    public ServiceInstance updateInstanceById(String instance_id)
    {
        if(serviceInstanceRepository.findById(instance_id).isEmpty())
        {
            throw new ServiceInstanceNotFoundException("Service instance with id " + instance_id + " does not exist.");
        }
        return serviceInstanceRepository.findById(instance_id).get();
    }
}
