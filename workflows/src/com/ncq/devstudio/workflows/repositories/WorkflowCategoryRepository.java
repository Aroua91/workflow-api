package com.ncq.devstudio.workflows.repositories;

import com.ncq.devstudio.workflows.entities.WorkflowCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Aroua Souabni
 */
public interface WorkflowCategoryRepository extends
        PagingAndSortingRepository<WorkflowCategory, Integer> 
, JpaRepository<WorkflowCategory, Integer> {


}
