package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.services.WorkflowCategoryService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
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
@RequestMapping("/wfapi/v1/category")
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
    public ResponseEntity<Response<NcqCategory>> createCategory(@RequestBody(required = true) NcqCategory ncqCategory) throws IOException {

        LOGGER.info("Adding new category;");
        WorkflowCategory category = categorySrv.addNewCategory(ncqCategory);
        LOGGER.info("Category added successfully!");

        Response<NcqCategory> r = new Response();
        r.setObject(categorySrv.toNcqCategory(category));
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<NcqCategory>>> getAllCategories() {
        LOGGER.info("Getting all categories ...");

        Response r = new Response();
        try {
            r.setObject(categorySrv.getAllCategories());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(WorkflowCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

}
