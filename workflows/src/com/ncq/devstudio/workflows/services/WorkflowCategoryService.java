package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.repositories.WorkflowCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aroua Souabni
 */
public class WorkflowCategoryService {

    private final WorkflowCategoryRepository categoryRepo;

    @Autowired
    public WorkflowCategoryService(WorkflowCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
}
