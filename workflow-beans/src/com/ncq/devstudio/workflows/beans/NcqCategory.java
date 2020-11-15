package com.ncq.devstudio.workflows.beans;

import java.util.Date;

/**
 * This class is an implementation of Data transfer protocol needed to transfer
 * category information.
 *
 * @author Aroua Souabni
 */
public class NcqCategory {

    private String uuid;

    private String name;

    private String description;

    private int enabled;

    private String logo;

    private String logoExtension;

    private Date creationDate;

    private Date updateDate;

    private String parentCategoryUuid;

    /**
     * Returns category 's name.
     *
     * @return category 's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets category 's name value.
     *
     * @param name category 's name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns category 's short description.
     *
     * @return category 's short description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets category 's short description.
     *
     * @param description category 's short description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns category 's status which is between 1..5.
     *
     * @return category 's status
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * Sets category 's status.
     *
     * @param enabled status which is between 1..5.
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns logo file base64 encoded.
     *
     * @return logo file base64 encoded.
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo file base64 encoded.
     *
     * @param logo logo file base64 encoded.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets category 's creation date.  May be {@code null}
     *
     * @return category 's creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets category 's creation date.  May be {@code null}
     *
     * @param creationDate category 's creation date.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets category 's update date.  May be {@code null}
     *
     * @return category 's update date.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets category 's update date.
     *
     * @param updateDate category 's update date.
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Returns category's generated unique identifier. May be {@code null}
     *
     * @return category's generated unique identifier.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets category's generated unique identifier. May be {@code null}
     *
     * @param uuid category's generated unique identifier.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns category 's parent unique identifier.  May be {@code null}
     *
     * @return category 's parent unique identifier.
     */
    public String getParentCategoryUuid() {
        return parentCategoryUuid;
    }

    /**
     * Sets category 's parent unique identifier.  May be {@code null}
     *
     * @param parentCategoryUuid category 's parent unique identifier.
     */
    public void setParentCategoryUuid(String parentCategoryUuid) {
        this.parentCategoryUuid = parentCategoryUuid;
    }

    /**
     * Returns logo file extension.
     *
     * @return logo file extension.
     */
    public String getLogoExtension() {
        return logoExtension;
    }

    /**
     * Sets logo file extension.
     *
     * @param logoExtension logo file extension.
     */
    public void setLogoExtension(String logoExtension) {
        this.logoExtension = logoExtension;
    }

    @Override
    public String toString() {
        return "NcqCategory{" + "uuid=" + uuid + ", name=" + name 
                + ", description=" + description + ", enabled=" + enabled 
                + ", logo=" + logo + ", logoExtension=" + logoExtension 
                + ", creationDate=" + creationDate + ", updateDate=" 
                + updateDate + ", parentCategoryUuid=" + parentCategoryUuid 
                + '}';
    }

}
