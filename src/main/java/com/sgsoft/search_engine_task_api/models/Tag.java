package com.sgsoft.search_engine_task_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String keyword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        return keyword != null ? keyword.equals(tag.keyword) : tag.keyword == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        return result;
    }
}
