package com.sniperfuchs.servicebroker.service;

import com.sniperfuchs.servicebroker.controller.BindFetchResponse;
import com.sniperfuchs.servicebroker.controller.BindResponse;
import com.sniperfuchs.servicebroker.exception.ExistingServiceBindingAttributeMismatchException;
import com.sniperfuchs.servicebroker.exception.InvalidIdentifierException;
import com.sniperfuchs.servicebroker.exception.ServiceBindingGoneException;
import com.sniperfuchs.servicebroker.exception.ServiceBindingNotFoundException;
import com.sniperfuchs.servicebroker.model.BindResource;
import com.sniperfuchs.servicebroker.model.ServiceBinding;
import com.sniperfuchs.servicebroker.repository.ServiceBindingRepository;
import com.sniperfuchs.servicebroker.util.IdentifierValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceBindingService
{
    private final ServiceBindingRepository serviceBindingRepository;

    public ServiceBindingService(ServiceBindingRepository serviceBindingRepository)
    {
        this.serviceBindingRepository = serviceBindingRepository;
    }

    public ResponseEntity fetchBindingById(String binding_id)
    {
        if(serviceBindingRepository.findById(binding_id).isEmpty())
        {
            throw new ServiceBindingNotFoundException("Binding with id " + binding_id + " not found.");
        }

        BindFetchResponse response = BindFetchResponse.builder()
                .syslog_drain_url("TestURL")
                .build();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity createBinding(String instance_id,
                                        String binding_id,
                                        String service_id,
                                        String plan_id,
                                        String app_guid,
                                        BindResource bind_resource)
    {
        if(instance_id == null || instance_id.isEmpty() || !IdentifierValidator.validate(instance_id))
        {
            throw new InvalidIdentifierException("Identifier instance_id: " + instance_id + " is invalid.");
        }

        if(binding_id == null || binding_id.isEmpty() || !IdentifierValidator.validate(binding_id))
        {
            throw new InvalidIdentifierException("Identifier binding_id: " + binding_id + " is invalid.");
        }

        if(service_id == null || service_id.isEmpty() || !IdentifierValidator.validate(service_id))
        {
            throw new InvalidIdentifierException("Identifier service_id: " + service_id + " is invalid.");
        }

        if(plan_id == null || plan_id.isEmpty() || !IdentifierValidator.validate(plan_id))
        {
            throw new InvalidIdentifierException("Identifier plan_id: " + plan_id + " is invalid.");
        }

        ServiceBinding serviceBinding = ServiceBinding.builder()
                .id(binding_id)
                .app_guid(app_guid)
                .bind_resource(bind_resource)
                .plan_id(plan_id)
                .service_id(service_id)
                .build();


        if(serviceBindingRepository.findById(binding_id).isPresent())
        {
            ServiceBinding existingBinding = serviceBindingRepository.findById(binding_id).get();
            if(existingBinding.hasSameAttributes(serviceBinding))
            {
                return new ResponseEntity(new BindResponse(), HttpStatus.OK); //TODO Proper response
            }
            else
            {
                throw new ExistingServiceBindingAttributeMismatchException("A Service binding with binding_id " + binding_id + " already exists with different attributes.");
            }
        }

        serviceBindingRepository.save(serviceBinding);

        return new ResponseEntity(new BindResponse(), HttpStatus.CREATED);
    }

    public ResponseEntity deleteBinding(String instance_id,
                              String binding_id,
                              String service_id,
                              String plan_id)
    {
        if(instance_id == null || instance_id.isEmpty() || !IdentifierValidator.validate(instance_id))
        {
            throw new ServiceBindingGoneException("Identifier instance_id: " + instance_id + " is invalid.");
        }

        if(binding_id == null || binding_id.isEmpty() || !IdentifierValidator.validate(binding_id))
        {
            throw new ServiceBindingGoneException("Identifier binding_id: " + binding_id + " is invalid.");
        }

        if(service_id == null || service_id.isEmpty() || !IdentifierValidator.validate(service_id))
        {
            throw new ServiceBindingGoneException("Identifier service_id: " + service_id + " is invalid.");
        }

        if(plan_id == null || plan_id.isEmpty() || !IdentifierValidator.validate(plan_id))
        {
            throw new ServiceBindingGoneException("Identifier plan_id: " + plan_id + " is invalid.");
        }

        if(serviceBindingRepository.findById(binding_id).isEmpty())
        {
            throw new ServiceBindingGoneException("Binding with id " + binding_id + " not found.");
        }

        serviceBindingRepository.deleteById(binding_id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
