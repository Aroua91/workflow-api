package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.beans.WorkflowsRequest;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.services.WorkflowCategoryService;
import com.ncq.devstudio.workflows.services.WorkflowService;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aroua Souabni
 */
@RestController
@RequestMapping("/workflow")
@EnableConfigurationProperties(ServerConfig.class)
public class WorkflowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            WorkflowController.class);

    private final WorkflowService workflowSrv;
    private final WorkflowCategoryService categorySrv;
    private final ServerConfig conf;

    @Autowired
    public WorkflowController(WorkflowService workflowSrv, WorkflowCategoryService categorySrv, ServerConfig conf) {
        this.workflowSrv = workflowSrv;
        this.categorySrv = categorySrv;
        this.conf = conf;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Response<NcqWorkflow>> createWorkflow(
            @RequestBody(required = true) NcqWorkflow ncqWorkflow) throws IOException {

        LOGGER.info("Adding new category;");
        Workflow workflow = workflowSrv.addNewWorkflow(ncqWorkflow);
        LOGGER.info("Category added successfully!");

        Response<NcqWorkflow> r = new Response();
        r.setObject(workflowSrv.toNcqWorkflow(workflow));
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    /**
     * This WS returns workflows total number.
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
     * This WS is needed to find the connected user's transactions.
     *
     * @param page the Transaction page
     * @return user's transactions
     */
    @RequestMapping(value = "/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<NcqWorkflow>>> getWorkflows(
            @PathVariable int page) {
        LOGGER.info("Getting workflow page number {}", page);

        if (page < 0) {
            // error
        }

        List<Workflow> workflowPage = workflowSrv.findPage(page, conf.getPageSize());
        List<NcqWorkflow> ncqWorkflows = new ArrayList();

        for (Workflow wf : workflowPage) {
            try {
                ncqWorkflows.add(workflowSrv.toNcqWorkflow(wf));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(WorkflowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Response<List<NcqWorkflow>> r = new Response<>();
        r.setObject(ncqWorkflows);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @RequestMapping(value = "/filter/{page}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<NcqWorkflow>>> filterWorkflows(@PathVariable int page,
            @RequestBody(required = true) WorkflowsRequest request) throws IOException {

        LOGGER.info("Filter workflows");
        List<Workflow> workflows = workflowSrv.filter(page, conf.getPageSize(), request.getName(), request.getEnabled(), request.getCategories());
        List<NcqWorkflow> ncqWorkflows = new ArrayList();

        for (Workflow wf : workflows) {
            try {
                ncqWorkflows.add(workflowSrv.toNcqWorkflow(wf));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(WorkflowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Response<List<NcqWorkflow>> r = new Response();
        r.setObject(ncqWorkflows);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
}
