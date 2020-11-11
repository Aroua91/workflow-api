package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.repositories.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Aroua Souabni
 */
@Service
public class WorkflowService {
    
    private final WorkflowRepository workflowRepo;

    @Autowired
    public WorkflowService(WorkflowRepository workflowRepo) {
        this.workflowRepo = workflowRepo;
    }
    
    
    
}
