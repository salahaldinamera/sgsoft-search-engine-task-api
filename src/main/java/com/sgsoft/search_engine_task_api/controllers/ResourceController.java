package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Resource Controller
 * contains resource CRUD methods
 */

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * Create resource operation API endpoint
     * @param resource The resource object to be added
     * @return Resource ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PostMapping("/create")
    public ResponseEntity<Resource> createResource(@Validated @RequestBody Resource resource){
        resourceService.saveResource(resource);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    /**
     * Get all resources operation API endpoint
     * @return All resources
     */
    @GetMapping("/all")
    public ResponseEntity<Resource> getResources(){
        List<Resource> resources = resourceService.getResources();
        return new ResponseEntity(resources, HttpStatus.OK);
    }

    /**
     * Get resource by id operation API endpoint
     * @param id The id to be retrieved
     * @return Resource
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable int id) {
        Resource resource = resourceService.getResource(id);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    /**
     * Update resource by id operation API endpoint
     * @param id The id of the resource to update
     * @param newResource The new resource data
     * @return Resource ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable int id, @Validated @RequestBody Resource newResource){
        resourceService.updateResource(id,newResource);
        return new ResponseEntity(newResource, HttpStatus.OK);
    }

    /**
     * Delete resource by id API endpoint
     * @param id The resource id to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable int id){
        resourceService.deleteResource(id);
    }
}
