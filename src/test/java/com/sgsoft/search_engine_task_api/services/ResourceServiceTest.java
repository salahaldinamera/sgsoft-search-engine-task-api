package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.repositories.ResourceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    @Test
    void saveResource() {
        Resource resource = new Resource();
        Mockito.when(resourceRepository.save(ArgumentMatchers.any(Resource.class))).thenReturn(resource);
        Resource resource1 = resourceService.saveResource(resource);
        Assertions.assertEquals(resource,resource1);
        Mockito.verify(resourceRepository);
    }

    @Test
    void getResources() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource());
        BDDMockito.given(resourceRepository.findAll()).willReturn(resources);
        List<Resource> resources1 = resourceService.getResources();
        Assertions.assertEquals(resources,resources1);
        Mockito.verify(resourceRepository);
    }

    @Test
    void getResource() {
        Resource resource = new Resource();
        BDDMockito.given(resourceRepository.getById(resource.getId())).willReturn(resource);
        Resource resource1 = resourceService.getResource(resource.getId());
        Assertions.assertEquals(resource,resource1);
        Mockito.verify(resourceRepository);
    }

    @Test
    void updateResource() {
        Resource resource = new Resource();
        resource.setId(1);
        resource.setTitle("Test Title");

        Resource newResource = new Resource();
        resource.setTitle("New Test Title");

        BDDMockito.given(resourceRepository.findById(resource.getId())).willReturn(Optional.of(resource));
        resourceService.updateResource(resource.getId(), newResource);
    }

    @Test
    void deleteResource() {
        Resource resource = new Resource();
        resourceService.deleteResource(resource.getId());
        Mockito.verify(resourceRepository).deleteById(resource.getId());
    }
}