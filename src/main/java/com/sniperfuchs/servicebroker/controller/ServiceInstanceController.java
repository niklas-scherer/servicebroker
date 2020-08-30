package com.sniperfuchs.servicebroker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceInstanceController {
    @GetMapping("/v2/service_instances/{instance_id}/last_operation")
    public ResponseEntity getLastOperation(@PathVariable String instance_id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity putServiceInstance(@PathVariable String instance_id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity getServiceInstance(@PathVariable String instance_id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity patchServiceInstance(@PathVariable String instance_id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/v2/service_instances/{instance_id}")
    public ResponseEntity deleteServiceInstance(@PathVariable String instance_id) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
