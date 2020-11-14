package com.ncq.devstudio.workflows.beans;

import java.util.Date;

/**
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentCategoryUuid() {
        return parentCategoryUuid;
    }

    public void setParentCategoryUuid(String parentCategoryUuid) {
        this.parentCategoryUuid = parentCategoryUuid;
    }

    

    public String getLogoExtension() {
        return logoExtension;
    }

    public void setLogoExtension(String logoExtension) {
        this.logoExtension = logoExtension;
    }

    
}
