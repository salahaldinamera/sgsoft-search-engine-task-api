package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.models.Tag;
import com.sgsoft.search_engine_task_api.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tag Controller
 * contains tag CRUD methods
 */

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * Create tag operation API endpoint
     * @param tag The tag object to be added
     * @return Tag ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PostMapping("/create")
    public ResponseEntity<Tag> createTag(@Validated @RequestBody Tag tag){
        tag = tagService.saveTag(tag);
        return new ResponseEntity(tag, HttpStatus.OK);
    }

    /**
     * Create tags operation API endpoint
     * @param tags List of tags to be added
     * @return Tags ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PostMapping("/create/multi")
    public ResponseEntity<Tag> createTags(@Validated @RequestBody List<Tag> tags){
        tags = tagService.saveTags(tags);
        return new ResponseEntity(tags, HttpStatus.OK);
    }

    /**
     * Get all tags operation API endpoint
     * @return All tags
     */
    @GetMapping("/all")
    public ResponseEntity<Tag> getTags(){
        List<Tag> tags = tagService.getTags();
        return new ResponseEntity(tags, HttpStatus.OK);
    }

    /**
     * Get tag by id operation API endpoint
     * @param id The id to be retrieved
     * @return Tag
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable int id) {
        Tag tag = tagService.getTag(id);
        return new ResponseEntity(tag, HttpStatus.OK);
    }

    /**
     * Update tag by id operation API endpoint
     * @param id The id of the tag to update
     * @param newTag The new tag data
     * @return Tag ResponceEntity with HttpStatus code (HttpStatus.OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable int id, @Validated @RequestBody Tag newTag){
        tagService.updateTag(id,newTag);
        return new ResponseEntity(newTag, HttpStatus.OK);
    }

    /**
     * Delete tag by id API endpoint
     * @param id The tag id to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable int id){
        tagService.deleteTag(id);
    }
}
