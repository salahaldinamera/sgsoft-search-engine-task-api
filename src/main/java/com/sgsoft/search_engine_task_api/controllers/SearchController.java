package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.models.Resource;
import com.sgsoft.search_engine_task_api.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * Search in all resources operation API endpoint
     * @param keyword The keyword to be searched
     * @return Returns A List of Resources ResponceEntity with (HttpStatus.OK)
     */
    @GetMapping("/resources")
    public ResponseEntity<List<Resource>> searchResources(@RequestParam("keyword") String keyword){
        return new ResponseEntity(searchService.search(keyword, "resource"), HttpStatus.OK);
    }

    /**
     * Search pages in all resources operation API endpoint
     * @param keyword The keyword to be searched
     * @return Returns A List of Resources with type html_page ResponceEntity with (HttpStatus.OK)
     */
    @GetMapping("/pages")
    public ResponseEntity<List<Resource>> searchPages(@RequestParam("keyword") String keyword){
        return new ResponseEntity(searchService.search(keyword,"html_page"), HttpStatus.OK);
    }

    /**
     * Search images in all resources operation API endpoint
     * @param keyword The keyword to be searched
     * @return Returns A List of Resources with type image ResponceEntity with (HttpStatus.OK)
     */
    @GetMapping("/images")
    public ResponseEntity<List<Resource>> searchImages(@RequestParam("keyword") String keyword){
        return new ResponseEntity(searchService.search(keyword,"image"), HttpStatus.OK);
    }
}
