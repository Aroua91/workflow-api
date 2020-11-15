package com.ncq.devstudio.workflows.beans;

import java.util.List;

/**
 *
 * @author Aroua Souabni
 */
public class NcqWorkflow {
    
    private String uuid;

    private String name;

    private String description;

    private int enabled;

    private List<String> principalWorkflows;

    private List<String> workflowVariants;
    
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

    public List<String> getPrincipalWorkflows() {
        return principalWorkflows;
    }

    public void setPrincipalWorkflows(List<String> principalWorkflows) {
        this.principalWorkflows = principalWorkflows;
    }

    public List<String> getWorkflowVariants() {
        return workflowVariants;
    }

    public void setWorkflowVariants(List<String> workflowVariants) {
        this.workflowVariants = workflowVariants;
    }
    
    public List<NcqCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<NcqCategory> categories) {
        this.categories = categories;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
}
