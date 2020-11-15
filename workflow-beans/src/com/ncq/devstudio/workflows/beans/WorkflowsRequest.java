package com.ncq.devstudio.workflows.beans;

/**
 * Represents a workflows filtering request. It encapsulates the following
 * information:
 * <ul>
 * <li>The name: workflow hole name or null</li>
 * <li>The status: a value between 1 and 5 , or null</li>
 * <li>The categories UUIDs. </li>
 * </ul>
 *
 * @author Aroua Souabni
 */
public class WorkflowsRequest {

    private String name;
    private int enabled;
    private String[] categories;

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
     * Returns categories UUIDs.
     *
     * @return categories UUIDs.
     */
    public String[] getCategories() {
        return categories;
    }

    /**
     * Sets categories UUIDs.
     *
     * @param categories categories UUIDs.
     */
    public void setCategories(String[] categories) {
        this.categories = categories;
    }

}
