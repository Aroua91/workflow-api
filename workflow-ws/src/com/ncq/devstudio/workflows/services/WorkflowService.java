package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.controllers.WorkflowController;
import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.errors.ApiException;
import com.ncq.devstudio.workflows.errors.ErrorCode;
import com.ncq.devstudio.workflows.repositories.WorkflowRepository;
import com.ncq.devstudio.workflows.specifications.WorkflowSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This is workflow service component which contains @Service annotation. This
 * class is used to write business logic related to {@link Workflow} separately.
 *
 * @author Aroua Souabni
 */
@Service
public class WorkflowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            WorkflowController.class);
    private final WorkflowRepository workflowRepo;
    private final WorkflowCategoryService categorySrv;

    /**
     * Construct {@link WorkflowService} and inject needed repositories and
     * services.
     *
     * @param workflowRepo workflow repository
     * @param categorySrv category service
     */
    @Autowired
    public WorkflowService(WorkflowRepository workflowRepo,
            WorkflowCategoryService categorySrv) {
        this.workflowRepo = workflowRepo;
        this.categorySrv = categorySrv;

    }

    /**
     * Add a new workflow.
     *
     * @param ncqWorkflow workflow information
     * @return saved workflow
     * @throws IOException
     */
    public Workflow addNewWorkflow(NcqWorkflow ncqWorkflow)
            throws IOException {
        Workflow workflow = new Workflow();
        if (ncqWorkflow.getCategories() != null && !ncqWorkflow.getCategories().isEmpty()) {
            Set<WorkflowCategory> categories = new HashSet();
            for (NcqCategory ncqCategory : ncqWorkflow.getCategories()) {
                WorkflowCategory category = categorySrv.getCategoryByUuid(ncqCategory.getUuid());
                if (category != null) {
                    categories.add(category);
                }
            }
            if (categories.isEmpty()) {
                LOGGER.error("Workflow must have at least one category!");
                throw new ApiException(ErrorCode.UNDEFINED_WF_CATEGORY_ERROR,
                        "An error has occurred because worklfow does not have valid categories.");

            }
            workflow.setCategories((Set<WorkflowCategory>) categories);
        }

        String uuid = UUID.randomUUID().toString();
        workflow.setUuid(uuid);
        workflow.setName(ncqWorkflow.getName());
        workflow.setDescription(ncqWorkflow.getDescription());
        workflow.setEnabled(ncqWorkflow.getEnabled());

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

    /**
     * Get workflows count.
     *
     * @return workflows number
     */
    public long getWorkflowsCount() {
        return workflowRepo.count();
    }

    /**
     * Filter workflows by mixed criteria.
     *
     * @param page page index
     * @param pageSize page size
     * @param name workflow name
     * @param enabled workflow status
     * @param category workflow categories UUIDs
     * @return list a workflows respecting this criteria if present or an empty
     * one
     */
    public List<Workflow> filter(Integer page, Integer pageSize, String name, int enabled, String[] category) {
        WorkflowSpecification workflowSpecification = new WorkflowSpecification(name, enabled, category);
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Workflow> workflows = workflowRepo.findAll(workflowSpecification, pageable).getContent();
        return workflows;
    }

    /**
     * Convert {@link Workflow} to {@link NcqWorkflow}.
     *
     * @param workflow workflow as saved in database
     * @return workflow 's information
     * @throws IOException when an error occurs while reading logo file
     */
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

    /**
     * Returns a Page of {@link Workflow} present in the specified page index
     *
     * @param page the page index
     * @param size the page size
     * @return a page of {@link Workflow} as specified in the parameters.
     */
    public List<Workflow> findPage(int page, int size) {
        Page<Workflow> wfPage = workflowRepo.findAll(
                PageRequest.of(page, size));
        return wfPage.getContent();
    }

}
