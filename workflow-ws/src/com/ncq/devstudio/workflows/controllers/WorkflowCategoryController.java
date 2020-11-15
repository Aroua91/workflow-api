package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.errors.ApiException;
import com.ncq.devstudio.workflows.errors.ErrorCode;
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
 * This endpoints represents workflow categories management REST web
 * services.<br>
 * <ul>
 * <li>Add a new {@link WorkflowCategory}</li>
 * <li>Get all categories</li>
 * <ul>
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

    /**
     * Construct {@link WorkflowCategoryController} and inject needed services.
     *
     * @param categorySrv category service
     */
    @Autowired
    public WorkflowCategoryController(WorkflowCategoryService categorySrv) {
        this.categorySrv = categorySrv;
    }

    /**
     * This endpoint permit to add a new category. While this WS invocation,
     * these information must be mentioned: <br>
     * <ul>
     * <li> name</li>
     * <li>description</li>
     * <li>status</li>
     * <li>logo file base 64 encoded</li>
     * <li>logo file extension</li>
     * <ul>
     *
     * @param ncqCategory category information.
     * @return added category information with its generated UUID
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Response<NcqCategory>> createCategory(@RequestBody(required = true) NcqCategory ncqCategory) {

        LOGGER.info("Adding new category;");
        verifyNcqCategory(ncqCategory);
        // TODO we can also verify it is a valid extension

        if (ncqCategory.getLogoExtension() == null || ncqCategory.getLogoExtension().isEmpty()) {
            LOGGER.error("Empty category exptension!");
            throw new ApiException(ErrorCode.EMPTY_EXT_ERROR,
                    "An error has occurred because category 's extension is empty.");
        }

        WorkflowCategory category;
        try {
            category = categorySrv.addNewCategory(ncqCategory);
        } catch (IOException ex) {
            LOGGER.error("An error has occured while saving logo file!");
            throw new ApiException(ErrorCode.SAVE_LOGO_ERROR,
                    "An error has occurred while saving file.");
        }
        LOGGER.info("Category added successfully!");
        try {
            ncqCategory = categorySrv.toNcqCategory(category);
        } catch (IOException ex) {
            LOGGER.error("An error has occured while reading logo file!");
            throw new ApiException(ErrorCode.READ_LOGO_ERROR,
                    "An error has occurred while saving file.");
        }
        Response<NcqCategory> r = new Response();
        r.setObject(ncqCategory);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    /**
     * This endpoint permit to get all categories. Since categories number is
     * around 20.
     *
     * @return list of categories
     */
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

    private void verifyNcqCategory(NcqCategory ncqCategory) {
        if (ncqCategory.getName() == null || ncqCategory.getName().isEmpty()) {
            LOGGER.error("Empty category name!");
            throw new ApiException(ErrorCode.EMPTY_NAME_ERROR,
                    "An error has occurred because category 's name is empty.");
        }

        if (ncqCategory.getDescription() == null || ncqCategory.getDescription().isEmpty()) {
            LOGGER.error("Empty category description!");
            throw new ApiException(ErrorCode.EMPTY_DESCREPTION_ERROR,
                    "An error has occurred because category 's description is empty.");
        }

        if (ncqCategory.getLogo() == null || ncqCategory.getLogo().isEmpty()) {
            LOGGER.error("Empty category description!");
            throw new ApiException(ErrorCode.EMPTY_LOGO_ERROR,
                    "An error has occurred because category 's description is empty.");
        }
        int enabled = ncqCategory.getEnabled();

        if (ncqCategory.getEnabled() > 5 || ncqCategory.getEnabled() < 0) {
            LOGGER.error("Status must be between 1 and 5!");
            throw new ApiException(ErrorCode.STATUS_OUT_OF_BOUND_ERROR,
                    "An error has occurred because category 's status must be between 1 and 5");
        }

    }

}
