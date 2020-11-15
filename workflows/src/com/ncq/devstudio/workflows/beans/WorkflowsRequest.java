package com.ncq.devstudio.workflows.beans;

/**
 *
 * @author Aroua Souabni
 */
public class WorkflowsRequest {
    private String name;
    private int enabled;
    private String[] categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
    
    
}
