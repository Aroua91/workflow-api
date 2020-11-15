package com.ncq.devstudio.workflows.beans;

import java.util.List;

/**
 * This class is an implementation of Data transfer protocol needed to transfer
 * workflow information.
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

    /**
     * Returns workflow 's name.
     *
     * @return workflow 's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets workflow 's name value.
     *
     * @param name workflow 's name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns workflow 's short description.
     *
     * @return workflow 's short description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets workflow 's short description.
     *
     * @param description workflow 's short description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns workflow 's status which is between 1..5.
     *
     * @return workflow 's status
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * Sets workflow 's status.
     *
     * @param enabled status which is between 1..5.
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns principal workflows UUIDs.
     *
     * @return list of principal workflows UUIDs.
     */
    public List<String> getPrincipalWorkflows() {
        return principalWorkflows;
    }

    /**
     * Sets principal workflows UUIDs.
     *
     * @param principalWorkflows principal workflows UUIDs.
     */
    public void setPrincipalWorkflows(List<String> principalWorkflows) {
        this.principalWorkflows = principalWorkflows;
    }

    /**
     * Returns workflow 's variants.
     *
     * @return workflow 's variants.
     */
    public List<String> getWorkflowVariants() {
        return workflowVariants;
    }

    /**
     * Returns workflow 's variants.
     *
     * @param workflowVariants workflow 's variants.
     */
    public void setWorkflowVariants(List<String> workflowVariants) {
        this.workflowVariants = workflowVariants;
    }

    /**
     * Returns workflow categories.
     *
     * @return workflow categories.
     */
    public List<NcqCategory> getCategories() {
        return categories;
    }

    /**
     * Sets workflow 's categories.
     *
     * @param categories workflows categories.
     */
    public void setCategories(List<NcqCategory> categories) {
        this.categories = categories;
    }

    /**
     * Gets workflow 's UUID.
     *
     * @return workflow 's categories.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets workflow 's UUID.
     *
     * @param uuid workflow 's UUID.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    

}
