package com.ncq.devstudio.workflows.repositories;

import com.ncq.devstudio.workflows.entities.Workflow;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository component of {@link  Workflow}. It encapsulates their storage,
 * retrieval, and search behaviour.
 *
 * @author Aroua Souabni
 */
@Repository
public interface WorkflowRepository extends PagingAndSortingRepository<Workflow, Integer>,
        JpaSpecificationExecutor<Workflow> {

    public Workflow findByUuid(String uuid);
}
