package com.sgsoft.search_engine_task_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
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

    /**
     * Default no argument constructor
     */
    public Resource() {
    }

    /**
     * Resource constructor without tags
     * @param title The title of the resource
     * @param type The type of the resource
     * @param link The link of the resource
     */
    public Resource(String title, String type, String link) {
        this.title = title;
        this.type = type;
        this.link = link;
    }

    /**
     * Resource constructor which convert strings to tags if entered a string and pass the tags if entered as tag object
     * @param title The title of the resource
     * @param type The type of the resource
     * @param link The link of the resource
     * @param tags The tags of the resource
     */
    public Resource(String title, String type, String link, List<Object> tags) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.link = link;

        this.tags = new ArrayList<>();

        if(!tags.isEmpty()){
            if(tags.get(0) instanceof Tag){
                for(int i=0;i<tags.size();i++){
                    this.tags.add((Tag) tags.get(i));
                    System.out.println("Tags tag " + tags.toString());
                }
            }
            if (tags.get(0) instanceof String) {
                for(int i=0;i<tags.size();i++){
                    this.tags.add(new Tag((String) tags.get(i)));
                    System.out.println("Tags string " + tags.toString());
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

        Resource resource = (Resource) o;

        if (id != resource.id) return false;
        if (title != null ? !title.equals(resource.title) : resource.title != null) return false;
        if (type != null ? !type.equals(resource.type) : resource.type != null) return false;
        if (link != null ? !link.equals(resource.link) : resource.link != null) return false;
        return tags != null ? tags.equals(resource.tags) : resource.tags == null;
    }

    /**
     * Basic auto generated hash code
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    /**
     * Override to string
     * @return String representing the resource object
     */
    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", tags=" + tags +
                '}';
    }
}
