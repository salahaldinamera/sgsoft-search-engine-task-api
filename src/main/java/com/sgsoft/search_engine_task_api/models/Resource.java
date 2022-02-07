package com.sgsoft.search_engine_task_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String title;
    private String type;
    private String link;

    @ManyToMany
    private List<Tag> tags;
}
