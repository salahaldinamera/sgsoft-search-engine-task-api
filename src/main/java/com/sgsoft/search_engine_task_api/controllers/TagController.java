package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.models.Tag;
import com.sgsoft.search_engine_task_api.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Tag Controller
 * contains tag CRUD methods
 */

@RestController
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    /**
     * Create tag operation
     * @param tag The tag object to be added
     * @return Tag ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PostMapping("/create")
    public ResponseEntity<Tag> createTag(@Validated @RequestBody Tag tag){
        tagRepository.save(tag);
        return new ResponseEntity(tag, HttpStatus.OK);
    }

    /**
     * Get all tags operation
     * @return All tags
     */
    @GetMapping("/all")
    public ResponseEntity<Tag> getTags(){
        List<Tag> tags = tagRepository.findAll();
        return new ResponseEntity(tags, HttpStatus.OK);
    }

    /**
     * Get tag by id operation
     * @param id The id to be retrieved
     * @return Tag
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable int id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return new ResponseEntity(tag, HttpStatus.OK);
    }

    /**
     * Update tag by id operation
     * @param id The id of the tag to update
     * @param newTag The new tag data
     * @return Tag ResponceEntity with HttpStatus code (HttpStatus.OK , HttpStatus.NOT_FOUND)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable int id, @Validated @RequestBody Tag newTag){
        Optional<Tag> tagOptional = Optional.ofNullable(tagRepository.findById(id).map(tag -> {
            tag.setId(id);
            tag.setKeyword(newTag.getKeyword());
            tag.setResources(newTag.getResources());
            return tag;
        }).orElseGet(() -> {return null;}));

        if(tagOptional.isEmpty()){
            return new ResponseEntity(tagOptional, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(tagOptional, HttpStatus.OK);
        }
    }

    /**
     * Delete tag by id
     * @param id The tag id to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable int id){
        tagRepository.deleteById(id);
    }
}
