package com.sniperfuchs.servicebroker.controller;

import com.sniperfuchs.servicebroker.service.ServiceInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceInstanceController
{

    private ServiceInstanceService serviceInstanceService;

    public ServiceInstanceController(ServiceInstanceService serviceInstanceService)
    {
        this.serviceInstanceService = serviceInstanceService;
    }

    @GetMapping("/v2/service_instances/{instance_id}/last_operation")
    public ResponseEntity getLastOperation(@PathVariable String instance_id,
                                           @RequestParam String service_id,
                                           @RequestParam String plan_id,
                                           @RequestParam String operation)
    {
        //TODO implement method
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity putServiceInstance(@PathVariable String instance_id,
                                             @RequestBody ProvisionRequest provisionRequest,
                                             @RequestParam(required = false) boolean accepts_incomplete)
    {


        return serviceInstanceService.createInstance(
                instance_id,
                provisionRequest.getService_id(),
                provisionRequest.getPlan_id(),
                provisionRequest.getOrganization_guid(),
                provisionRequest.getSpace_guid(),
                provisionRequest.getParameters(),
                provisionRequest.getMaintenance_info());
    }

    @GetMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity getServiceInstance(@PathVariable String instance_id)
    {
        return new ResponseEntity(serviceInstanceService.fetchInstanceById(instance_id), HttpStatus.OK);
    }

    @PatchMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity patchServiceInstance(@PathVariable String instance_id)
    {
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity deleteServiceInstance(@PathVariable String instance_id)
    {
        return new ResponseEntity(HttpStatus.OK);
    }

}
