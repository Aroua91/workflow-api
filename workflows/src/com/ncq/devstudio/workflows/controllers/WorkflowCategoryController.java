package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.services.WorkflowCategoryService;
import java.io.IOException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aroua Souabni
 */
@RestController
@RequestMapping("/category")
@EnableConfigurationProperties(ServerConfig.class)
public class WorkflowCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            WorkflowCategoryController.class);

    private final WorkflowCategoryService categorySrv;

    @Autowired
    public WorkflowCategoryController(WorkflowCategoryService categorySrv) {
        this.categorySrv = categorySrv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Response<NcqCategory>> createTransaction(
            String email, @RequestBody(required = true) NcqCategory ncqCategory) throws IOException {

        LOGGER.info("Adding new category;");
        WorkflowCategory category = categorySrv.addNewCategory(ncqCategory);
        LOGGER.info("Category added successfully!");
        
        Response<NcqCategory> r = new Response();
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
}
