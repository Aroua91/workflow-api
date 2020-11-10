package com.ncq.devstudio.workflows.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Aroua Souabni
 */
@Entity
@Table(name = "ncq-workflow")
public class Workflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_workflow")
    private Integer id;

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


    
    

}
