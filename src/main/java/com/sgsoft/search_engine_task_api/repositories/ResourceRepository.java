package com.sgsoft.search_engine_task_api.repositories;

import com.sgsoft.search_engine_task_api.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
//    @Query("SELECT r FROM Resource r WHERE r.id in (SELECT resourceId FROM ResourceTags rt WHERE rt.tagsId in (SELECT id FROM Tag t WHERE t.keyword LIKE :keyword))")
    @Query("select r from Resource r join r.tags t where t.keyword like :keyword")
    public List<Resource> getResourcesByKeyword(@Param("keyword") String keyword);

    @Query("select r from Resource r join r.tags t where t.keyword like :keyword and r.type=:type")
    public List<Resource> getResourcesByKeywordAndType(@Param("keyword") String keyword, @Param("type") String type);
}
