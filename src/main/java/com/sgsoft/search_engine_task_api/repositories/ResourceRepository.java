package com.sgsoft.search_engine_task_api.repositories;

import com.sgsoft.search_engine_task_api.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
