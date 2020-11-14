package com.ncq.devstudio.workflows.beans;

import java.util.List;

/**
 *
 * @author aroua
 */
public class NcqWorkflow {

    private String name;

    private String description;

    private int enabled;

    private List<NcqWorkflow> principalWorkflows;

    private List<NcqWorkflow> workflowVariants;
    
    private List<NcqCategory> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<NcqWorkflow> getPrincipalWorkflows() {
        return principalWorkflows;
    }

    public void setPrincipalWorkflows(List<NcqWorkflow> principalWorkflows) {
        this.principalWorkflows = principalWorkflows;
    }

    public List<NcqWorkflow> getWorkflowVariants() {
        return workflowVariants;
    }

    public void setWorkflowVariants(List<NcqWorkflow> workflowVariants) {
        this.workflowVariants = workflowVariants;
    }

    public List<NcqCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<NcqCategory> categories) {
        this.categories = categories;
    }
    
    
}
