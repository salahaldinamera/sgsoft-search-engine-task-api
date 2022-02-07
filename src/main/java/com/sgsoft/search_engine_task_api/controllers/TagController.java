package com.sgsoft.search_engine_task_api.controllers;

import com.sgsoft.search_engine_task_api.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private TagRepository tagRepository;
}
