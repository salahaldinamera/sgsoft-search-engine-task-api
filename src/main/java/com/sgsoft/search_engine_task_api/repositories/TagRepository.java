package com.sgsoft.search_engine_task_api.repositories;

import com.sgsoft.search_engine_task_api.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t from Tag t where t.keyword=:keyword")
    Optional<Tag> findTagByKeyword(@Param("keyword") String keyword);
}
