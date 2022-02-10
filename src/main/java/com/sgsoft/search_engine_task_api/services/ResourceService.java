package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.exceptions.ResourceNotFoundException;
import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * Save resource service
     * @param resource The resource to be saved
     */
    public Resource saveResource(Resource resource){
        if(resource == null){
            throw new IllegalArgumentException("Invalid Resource details passed.");
        }
        return resourceRepository.save(resource);
    }

    /**
     * Get all resources service
     * @return Returns all resources
     */
    public List<Resource> getResources(){
        return resourceRepository.findAll();
    }

    /**
     * Get resource by id service
     * @param id The service id to be found
     * @returnm Returns the resource
     */
    public Resource getResource(Integer id){
        if(resourceRepository.findById(id).isEmpty()) throw new ResourceNotFoundException("Resource id " + id + " Not Found");
        return resourceRepository.findById(id).get();
    }

    /**
     * Update resource by id service
     * @param id The id of the resource to be updated
     * @param newResource The new data of the resource
     */
    public Resource updateResource(Integer id, Resource newResource){
        Optional<Resource> resourceOptional = Optional.ofNullable(resourceRepository.findById(id).map(resource -> {
            resource.setId(id);
            if(newResource.getTitle()!=null) resource.setTitle(newResource.getTitle());
            if(newResource.getLink()!=null) resource.setLink(newResource.getLink());
            if(newResource.getType()!=null) resource.setType(newResource.getType());
            if(newResource.getTags()!=null) resource.setTags(newResource.getTags());
            return resource;
        }).orElseGet(() -> {return null;}));
        return resourceRepository.save(resourceOptional.get());
    }

    /**
     * Delete resource by id service
     * @param id The resource id to be deleted
     */
    public boolean deleteResource(Integer id){
        if(resourceRepository.findById(id).isEmpty()) throw new ResourceNotFoundException("Resource id " + id + " Not Found");
        resourceRepository.deleteById(id);
        return true;
    }
}
