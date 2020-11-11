package com.ncq.devstudio.workflows.repositories;

import com.ncq.devstudio.workflows.entities.Workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aroua Souabni
 */
@Repository
public interface WorkflowRepository extends
        PagingAndSortingRepository<Workflow, Integer>, JpaRepository<Workflow, Integer> {

}
