package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.beans.WorkflowsRequest;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.errors.ApiException;
import com.ncq.devstudio.workflows.errors.ErrorCode;
import com.ncq.devstudio.workflows.services.WorkflowCategoryService;
import com.ncq.devstudio.workflows.services.WorkflowService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This endpoints represents workflow management REST web services.<br>
 * <ul>
 * <li>Add a new {@link Workflow}</li>
 * <li>Get a {@link Workflow} page</li>
 * <li>Filter {@link Workflow} by name, status or one {@link WorkflowCategory}
 * or more</li>
 * <ul>
 *
 * @author Aroua Souabni
 */
@RestController
@RequestMapping("/wfapi/v1/workflow")
@EnableConfigurationProperties(ServerConfig.class)
public class WorkflowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            WorkflowService.class);

    private final WorkflowService workflowSrv;
    private final WorkflowCategoryService categorySrv;
    private final ServerConfig conf;

    /**
     * Construct {@link WorkflowController} and inject needed services.
     *
     * @param workflowSrv workflow service
     * @param categorySrv category service
     * @param conf server configuration service
     */
    @Autowired
    public WorkflowController(WorkflowService workflowSrv, WorkflowCategoryService categorySrv, ServerConfig conf) {
        this.workflowSrv = workflowSrv;
        this.categorySrv = categorySrv;
        this.conf = conf;
    }

    /**
     * This endpoint permit to add a new category. While this WS invocation,
     * these information need to be mentioned: <br>
     * <ul>
     * <li> name</li>
     * <li>description</li>
     * <li>status</li>
     * <li>one or more categories UUIDs</li>
     * <li>principal workflows (optional)</li>
     * <li>variant workflows (optional</li>
     * <ul>
     *
     * @param ncqWorkflow workflow information
     * @return added workflow information with generated UUID
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Response<NcqWorkflow>> createWorkflow(
            @RequestBody(required = true) NcqWorkflow ncqWorkflow) throws IOException {

        LOGGER.info("Adding new workflow.");
        verifyNcqWorkflow(ncqWorkflow);
        Workflow workflow = workflowSrv.addNewWorkflow(ncqWorkflow);
        LOGGER.info("Workflow added successfully!");

        Response<NcqWorkflow> r = new Response();
        r.setObject(workflowSrv.toNcqWorkflow(workflow));
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    /**
     * This endpoint permit to return workflows total number.
     *
     * @return workflows count
     */
    @RequestMapping(value = "/count",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Long>> getWorkflowsCount() {
        LOGGER.info("Returning the total number of workflows");
        Response<Long> r = new Response<>();
        r.setObject(workflowSrv.getWorkflowsCount());
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    /**
     * This endpoint is needed to get workflows page.
     *
     * @param page the page index
     * @return workflows present in the designed page
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<NcqWorkflow>>> getWorkflows(
            @PathVariable int page) throws IOException {
        LOGGER.info("Getting workflow page number {}", page);

        if (page < 0) {
            LOGGER.error("Invalid workflow page!");
            throw new ApiException(ErrorCode.INVALID_WF_PAGE_ERROR,
                    "Invalid workflow page!");
        }

        List<Workflow> workflowPage = workflowSrv.findPage(page, conf.getPageSize());
        List<NcqWorkflow> ncqWorkflows = new ArrayList();

        for (Workflow wf : workflowPage) {
            ncqWorkflows.add(workflowSrv.toNcqWorkflow(wf));

        }

        Response<List<NcqWorkflow>> r = new Response<>();
        r.setObject(ncqWorkflows);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    /**
     * Filter workflows using combined criteria:
     * <ul>
     * <li>name</li>
     * <li>status</li>
     * <li>one category or more</li>
     * </ul>
     *
     * @param page page index
     * @param request filter criteria request
     * @return workflows corresponding to this criteria in the designed page
     * index
     * @throws IOException input or output exception
     */
    @RequestMapping(value = "/filter/{page}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<NcqWorkflow>>> filterWorkflows(@PathVariable int page,
            @RequestBody(required = true) WorkflowsRequest request) throws IOException {

        LOGGER.info("Filter workflows");
        List<Workflow> workflows = workflowSrv.filter(page, conf.getPageSize(), request.getName(), request.getEnabled(), request.getCategories());
        List<NcqWorkflow> ncqWorkflows = new ArrayList();

        for (Workflow wf : workflows) {
            ncqWorkflows.add(workflowSrv.toNcqWorkflow(wf));
        }
        Response<List<NcqWorkflow>> r = new Response();
        r.setObject(ncqWorkflows);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    private void verifyNcqWorkflow(NcqWorkflow ncqWorkflow) {
        if (ncqWorkflow.getName() == null || ncqWorkflow.getName().isEmpty()) {
            LOGGER.error("Empty workflow name!");
            throw new ApiException(ErrorCode.EMPTY_WF_NAME_ERROR,
                    "An error has occurred because workflow 's name is empty.");
        }

        if (ncqWorkflow.getDescription() == null || ncqWorkflow.getDescription().isEmpty()) {
            LOGGER.error("Empty workflow description!");
            throw new ApiException(ErrorCode.EMPTY_WF_DESCREPTION_ERROR,
                    "An error has occurred because worklfow 's description is empty.");
        }

        int enabled = ncqWorkflow.getEnabled();

        if (enabled > 5 || enabled < 0) {
            LOGGER.error("Status must be between 1 and 5!");
            throw new ApiException(ErrorCode.WF_STATUS_OUT_OF_BOUND_ERROR,
                    "An error has occurred because workflow 's status must be between 1 and 5");
        }

        if (ncqWorkflow.getCategories() == null || ncqWorkflow.getCategories().isEmpty()) {
            LOGGER.error("Empty workflow description!");
            throw new ApiException(ErrorCode.INVALID_WF_CATEGORY_ERROR,
                    "An error has occurred because worklfow 's description is empty.");
        }
    }

}
