package com.sgsoft.search_engine_task_api.services;

import com.sgsoft.search_engine_task_api.exceptions.TagNotFoundException;
import com.sgsoft.search_engine_task_api.models.Tag;
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
    public Tag saveTag(Tag tag){
        if(tag == null){
            throw new IllegalArgumentException("Invalid Tag details passed.");
        }
        return tagRepository.save(tag);
    }

    /**
     * Save tags service
     * @param tags The tags to be saved
     * @return
     */
    public List<Tag> saveTags(List<Tag> tags){
        if (tags == null) throw new IllegalArgumentException("Invalid tags entered.");
        for (int i = 0;i<tags.size();i++){
            if(tags.get(i) ==  null)  throw new IllegalArgumentException("One of the tags is invalid.");
            Tag tag = tagRepository.save(tags.get(i));
            tags.set(i,tag);
        }
        return tags;
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
        if(tagRepository.findById(id).isEmpty()) throw new TagNotFoundException("Tag id " + id + " Not Found");
        return tagRepository.findById(id).get();
    }

    /**
     * Update tag by id service
     * @param id The id of the tag to be updated
     * @param newTag The new data of the tag
     */
    public void updateTag(Integer id, Tag newTag){
        Optional<Tag> tagOptional = Optional.ofNullable(tagRepository.findById(id).map(tag -> {
            tag.setId(id);
            if(newTag.getKeyword()!=null) tag.setKeyword(newTag.getKeyword());
            return tag;
        }).orElseGet(() -> {return null;}));
    }

    /**
     * Delete tag by id service
     * @param id The tag id to be deleted
     */
    public boolean deleteTag(Integer id){
        if(tagRepository.findById(id).isEmpty()) throw new TagNotFoundException("Tag id " + id + " Not Found");
        tagRepository.deleteById(id);
        return true;
    }

}
