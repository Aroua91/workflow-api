package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.repositories.WorkflowRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aroua Souabni
 */
@Service
public class WorkflowService {

    private final WorkflowRepository workflowRepo;
    private final WorkflowCategoryService categorySrv;

    @Autowired
    public WorkflowService(WorkflowRepository workflowRepo,
            WorkflowCategoryService categorySrv) {
        this.workflowRepo = workflowRepo;
        this.categorySrv = categorySrv;

    }

    public Workflow addNewWorkflow(NcqWorkflow ncqWorkflow)
            throws IOException {
        Workflow workflow = new Workflow();
        String uuid = UUID.randomUUID().toString();
        workflow.setUuid(uuid);
        workflow.setName(ncqWorkflow.getName());
        workflow.setDescription(ncqWorkflow.getDescription());
        workflow.setEnabled(ncqWorkflow.getEnabled());

        if (ncqWorkflow.getCategories() != null && !ncqWorkflow.getCategories().isEmpty()) {
            Set<WorkflowCategory> categories = new HashSet();
            for (NcqCategory ncqCategory : ncqWorkflow.getCategories()) {
                WorkflowCategory category = categorySrv.getCategoryByUuid(ncqCategory.getUuid());
                categories.add(category);
            }
            System.out.println("**** " + categories.size());
            workflow.setCategories((Set<WorkflowCategory>) categories);
        }

        if (ncqWorkflow.getPrincipalWorkflows() != null && !ncqWorkflow.getPrincipalWorkflows().isEmpty()) {
            Set<Workflow> workflows = new HashSet();
            for (String wfUuid : ncqWorkflow.getPrincipalWorkflows()) {
                Workflow principalWorkflow = workflowRepo.findByUuid(wfUuid);
                workflows.add(principalWorkflow);
            }
            workflow.setPrincipalWorkflows((Set<Workflow>) workflows);
        }
        if (ncqWorkflow.getWorkflowVariants() != null && !ncqWorkflow.getWorkflowVariants().isEmpty()) {
            Set<Workflow> workflows = new HashSet();
            for (String wfUuid : ncqWorkflow.getWorkflowVariants()) {
                Workflow variantrkflow = workflowRepo.findByUuid(wfUuid);
                workflows.add(variantrkflow);
            }
            workflow.setWorkflowVariants((Set<Workflow>) workflows);
        }
        workflowRepo.save(workflow);
        return workflow;
    }

    public static NcqWorkflow toNcqWorkflow(Workflow workflow) throws IOException {
        NcqWorkflow ncqWorkflow = new NcqWorkflow();
        ncqWorkflow.setName(workflow.getName());
        ncqWorkflow.setDescription(workflow.getDescription());
        ncqWorkflow.setEnabled(workflow.getEnabled());
        ncqWorkflow.setUuid(workflow.getUuid());

        if (workflow.getCategories() != null && !workflow.getCategories().isEmpty()) {
            List<NcqCategory> ncqCategories = new ArrayList();
            for (WorkflowCategory category : workflow.getCategories()) {
                ncqCategories.add(WorkflowCategoryService.toNcqCategory(category));
            }
            ncqWorkflow.setCategories(ncqCategories);
        }

        if (workflow.getPrincipalWorkflows() != null && !workflow.getPrincipalWorkflows().isEmpty()) {
            List<String> princpalWorkflows = new ArrayList();
            for (Workflow wf : workflow.getPrincipalWorkflows()) {
                princpalWorkflows.add(wf.getUuid());
            }
            ncqWorkflow.setPrincipalWorkflows(princpalWorkflows);
        }

        if (workflow.getWorkflowVariants() != null && !workflow.getWorkflowVariants().isEmpty()) {
            List<String> workflowVariants = new ArrayList();
            for (Workflow wf : workflow.getWorkflowVariants()) {
                workflowVariants.add(wf.getUuid());
            }
            ncqWorkflow.setWorkflowVariants(workflowVariants);
        }

        return ncqWorkflow;
    }

}
