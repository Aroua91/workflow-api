package com.ncq.devstudio.workflows.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Aroua Souabni
 */
@Entity
@Table(name = "ncq_workflow_category")
public class WorkflowCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_category")
    private Integer id;
    
        @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = true)
    private String name;

    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "enabled")
    private int enabled;

    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "logo", nullable = true)
    private String logo;

    @Basic(optional = true)
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Basic(optional = true)
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkflowCategory parentCategory;
    
    @OneToMany(mappedBy = "parentCategory")
    private Set<WorkflowCategory> subCategories = new HashSet<>();
    
    @ManyToMany(mappedBy="categories")
    private Set<Workflow> workflows = new HashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public WorkflowCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(WorkflowCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<WorkflowCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<WorkflowCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Set<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(Set<Workflow> workflows) {
        this.workflows = workflows;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
 
    
    
}
