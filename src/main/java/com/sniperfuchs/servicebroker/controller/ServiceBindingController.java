package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.service.ServiceBindingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceBindingController
{
    private final ServiceBindingService serviceBindingService;

    public ServiceBindingController(ServiceBindingService serviceBindingService)
    {
        this.serviceBindingService = serviceBindingService;
    }

    @GetMapping("/v2/service_instances/{instance_id}/service_bindings/{binding_id}/last_operation")
    public ResponseEntity getLastOperation(@PathVariable String instance_id,
                                           @PathVariable String binding_id,
                                           @RequestParam String service_id,
                                           @RequestParam String plan_id,
                                           @RequestParam String operation)
    {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/v2/service_instances/{instance_id}/service_bindings/{binding_id}")
    public ResponseEntity putServiceBinding(@PathVariable String instance_id,
                                            @PathVariable String binding_id,
                                            @RequestBody BindRequest request,
                                            @RequestParam(required = false) boolean accepts_incomplete)
    {
        return serviceBindingService.createBinding(instance_id, binding_id, request.getService_id(), request.getPlan_id(), request.getApp_guid(), request.getBind_resource());
    }

    @GetMapping("/v2/service_instances/{instance_id}/service_bindings/{binding_id}")
    public ResponseEntity getServiceBinding(@PathVariable String instance_id,
                                            @PathVariable String binding_id)
    {
        return serviceBindingService.fetchBindingById(binding_id);
    }

    @DeleteMapping("/v2/service_instances/{instance_id}/service_bindings/{binding_id}")
    public ResponseEntity deleteServiceBinding(@PathVariable String instance_id,
                                               @PathVariable String binding_id,
                                               @RequestParam String service_id,
                                               @RequestParam String plan_id,
                                               @RequestParam(required = false) boolean accepts_incomplete)
    {
        return serviceBindingService.deleteBinding(instance_id, binding_id, service_id, plan_id);
    }
}
