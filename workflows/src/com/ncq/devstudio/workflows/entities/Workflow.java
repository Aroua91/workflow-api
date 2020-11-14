package com.ncq.devstudio.workflows.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aroua Souabni
 */
@Entity
@Table(name = "ncq_workflow")
public class Workflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_workflow")
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

        @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "workflow_variants",
            joinColumns = {
                @JoinColumn(name = "id_variant")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_workflow")})
    private Set<Workflow> workflowVariants = new HashSet();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "workflow_variants",
            joinColumns = {
                @JoinColumn(name = "id_workflow")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_variant")})
    private Set<Workflow> principalWorkflows = new HashSet();


    @ManyToMany
    @JoinTable(name = "workflow_category_join",
            joinColumns = {
                @JoinColumn(name = "id_workflow")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_category")})
    private Set<WorkflowCategory> categories = new HashSet();

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

    public Set<Workflow> getPrincipalWorkflows() {
        return principalWorkflows;
    }

    public void setPrincipalWorkflows(Set<Workflow> principalWorkflows) {
        this.principalWorkflows = principalWorkflows;
    }

    public Set<Workflow> getWorkflowVariants() {
        return workflowVariants;
    }

    public void setWorkflowVariants(Set<Workflow> workflowVariants) {
        this.workflowVariants = workflowVariants;
    }

    public Set<WorkflowCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<WorkflowCategory> categories) {
        this.categories = categories;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
