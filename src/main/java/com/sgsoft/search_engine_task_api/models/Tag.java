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

    /**
     * Default no argument constructor
     */
    public Tag() {
    }

    /**
     * Constructor that takes keyword as parameter
     * @param keyword The keyword of the tag
     */
    public Tag(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Constructor that takes id and keyword as parameter
     * @param id The id of the tag
     * @param keyword The keyword of the tag
     */
    public Tag(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

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

    /**
     * Override equals method used in comparing tags
     * @param o The object to compare to
     * @return True if equals or False if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        return keyword != null ? keyword.equals(tag.keyword) : tag.keyword == null;
    }

    /**
     * Basic auto generated hash code
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        return result;
    }

    /**
     * Override to string
     * @return String representing the tag object
     */
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
