package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.models.Tag;
import com.sgsoft.search_engine_task_api.repositories.TagRepository;
import com.sgsoft.search_engine_task_api.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    
    /**
     * Save tag service
     * @param tag The tag to be saved
     */
    public void saveResource(Tag tag){
        if(tag == null){
            throw new IllegalArgumentException("Invalid Tag details passed.");
        }
        tagRepository.save(tag);
    }

    /**
     * Get all tags service
     * @return Returns all tags
     */
    public List<Tag> getTags(){
        return tagRepository.findAll();
    }

    /**
     * Get tag by id service
     * @param id The service id to be found
     * @returnm Returns the tag
     */
    public Tag getTag(Integer id){
        return tagRepository.getById(id);
    }

    /**
     * Update tag by id service
     * @param id The id of the tag to be updated
     * @param newTag The new data of the tag
     */
    public void updateTag(Integer id, Tag newTag){
        Optional<Tag> tagOptional = Optional.ofNullable(tagRepository.findById(id).map(tag -> {
            tag.setId(id);
            tag.setKeyword(newTag.getKeyword());
            tag.setResources(newTag.getResources());
            return tag;
        }).orElseGet(() -> {return null;}));
    }

    /**
     * Delete tag by id service
     * @param id The tag id to be deleted
     */
    public void deleteTag(Integer id){
        tagRepository.deleteById(id);
    }

}
