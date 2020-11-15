package com.ncq.devstudio.workflows.repositories;

import com.ncq.devstudio.workflows.entities.WorkflowCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A repository component of {@link  WorkflowCategory}. It encapsulates their
 * storage, retrieval, and search behaviour.
 *
 * @author Aroua Souabni
 */
public interface WorkflowCategoryRepository extends
        PagingAndSortingRepository<WorkflowCategory, Integer>,
         JpaRepository<WorkflowCategory, Integer> {

    public WorkflowCategory findByUuid(String uuid);

}
