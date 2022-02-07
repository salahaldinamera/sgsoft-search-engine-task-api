package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Resource Controller
 * contains resource CRUD methods
 */

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * Create resource operation
     * @param resource The resource object to be added
     * @return Resource ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PostMapping("/create")
    public ResponseEntity<Resource> createResource(@Validated @RequestBody Resource resource){
        resourceRepository.save(resource);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    /**
     * Get all resources operation
     * @return All resources
     */
    @GetMapping("/all")
    public ResponseEntity<Resource> getResources(){
        List<Resource> resources = resourceRepository.findAll();
        return new ResponseEntity(resources, HttpStatus.OK);
    }

    /**
     * Get resource by id operation
     * @param id The id to be retrieved
     * @return Resource
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable int id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        return new ResponseEntity(resource, HttpStatus.OK);
    }

    /**
     * Update resource by id operation
     * @param id The id of the resource to update
     * @param newResource The new resource data
     * @return Resource ResponceEntity with HttpStatus code (HttpStatus.OK , HttpStatus.NOT_FOUND)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable int id, @Validated @RequestBody Resource newResource){
        Optional<Resource> resourceOptional = Optional.ofNullable(resourceRepository.findById(id).map(resource -> {
            resource.setId(id);
            resource.setTitle(newResource.getTitle());
            resource.setLink(newResource.getLink());
            resource.setType(newResource.getType());
            resource.setTags(newResource.getTags());
            return resource;
        }).orElseGet(() -> {return null;}));

        if(resourceOptional.isEmpty()){
            return new ResponseEntity(resourceOptional, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(resourceOptional, HttpStatus.OK);
        }
    }

    /**
     * Delete resource by id
     * @param id The resource id to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable int id){
        resourceRepository.deleteById(id);
    }
}
