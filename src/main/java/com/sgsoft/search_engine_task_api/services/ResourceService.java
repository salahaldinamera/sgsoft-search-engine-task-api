package com.sgsoft.search_engine_task_api.services;

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
        return resourceRepository.getById(id);
    }

    /**
     * Update resource by id service
     * @param id The id of the resource to be updated
     * @param newResource The new data of the resource
     */
    public void updateResource(Integer id, Resource newResource){
        Optional<Resource> resourceOptional = Optional.ofNullable(resourceRepository.findById(id).map(resource -> {
            resource.setId(id);
            resource.setTitle(newResource.getTitle());
            resource.setLink(newResource.getLink());
            resource.setType(newResource.getType());
            resource.setTags(newResource.getTags());
            return resource;
        }).orElseGet(() -> {return null;}));
    }

    /**
     * Delete resource by id service
     * @param id The resource id to be deleted
     */
    public boolean deleteResource(Integer id){
        resourceRepository.deleteById(id);
        return true;
    }
}
