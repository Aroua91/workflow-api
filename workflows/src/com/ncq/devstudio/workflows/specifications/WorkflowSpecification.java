package com.ncq.devstudio.workflows.specifications;

import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Aroua Souabni
 */
public class WorkflowSpecification implements Specification<Workflow> {

    private String name;
    private String[] categories;
    private Integer status;

    public WorkflowSpecification(String name, Integer status, String... categories) {
        super();
        this.name = name;
        this.categories = categories;
        this.status = status;
    }

    public Predicate toPredicate(Root<Workflow> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (name != null) {
            predicates.add(builder.equal(root.get("name"), name));
        }

        if (categories != null && categories.length != 0) {
            List<Predicate> catPredicates = new ArrayList<Predicate>();
            Join<Workflow, WorkflowCategory> workflowJoin = root.join("categories");
            for (String category : categories) {
                catPredicates.add(builder.equal(workflowJoin.get("uuid"), category));
            }
            predicates.add(builder.or(catPredicates.toArray(new Predicate[0])));
        }

        if (status != null) {
            predicates.add(builder.equal(root.get("enabled"), status));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
